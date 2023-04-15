package com.api.naver.smartstore.service.template;

import com.api.naver.smartstore.service.template.common.interfaces.NaverCommerceTemplate;
import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonResponse;
import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import com.api.naver.smartstore.service.template.crypto.CryptoUtils;
import com.api.naver.smartstore.service.template.exception.FailResponseException;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;
import com.api.naver.smartstore.service.template.response.TokenResponse;
import com.api.naver.smartstore.service.template.verify.NaverRequestVerify;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class NaverTemplateImpl implements NaverCommerceTemplate {
    private final RestTemplate restTemplate;
    public NaverTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final List<NaverRequestVerify> verifies = new ArrayList<>();

    @Override
    public <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> R execute(NaverToken token, T request, Class<R> response) {
        try {
            validation(request, response);
            HttpEntity<T> entity = makeHttpEntity(token, request);
            return restTemplate.exchange(request.findUrl(), request.findHttpMethod(), entity, response).getBody();
        } catch (HttpClientErrorException e) {
            throw new FailResponseException(e.getResponseBodyAsString());
        } catch (RequestVerifyException verifyException) {
            throw verifyException;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("naver api 호출 도중 알 수 없는 에러가 발생했습니다.");
        }
    }

    private <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> void validation(T request, Class<R> response) {
        verifies.forEach(naverRequestVerify -> naverRequestVerify.verifyRequest(request));
        verifyServiceType(request, response);
    }

    private <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> void verifyServiceType(T request, Class<R> response) {
        try {
            if (request.findServiceType() != response.getDeclaredConstructor().newInstance().findServiceType()) {
                throw new IllegalArgumentException("서비스 타입이 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("알 수 없는 에러 발생");
        }
    }

    private <T extends NaverCommonRequest<?>> HttpEntity<T> makeHttpEntity(NaverToken token, T request) {
        HttpHeaders headers = makeBaseHeader(getToken(token));
        if (request.findBody() != null) {
            return new HttpEntity<>(request, headers);
        } else {
            return new HttpEntity<>(headers);
        }
    }

    private HttpHeaders makeBaseHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getToken(NaverToken token) {
        Long timestamp = System.currentTimeMillis();
        String sign = CryptoUtils.issueClientSecretSign(token, timestamp);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String getTokenUrl = "https://api.commerce.naver.com/external/v1/oauth2/token";

        UriComponents build = UriComponentsBuilder.fromHttpUrl(getTokenUrl)
                .queryParam("client_id", token.getClientId())
                .queryParam("timestamp", timestamp)
                .queryParam("client_secret_sign", sign)
                .queryParam("grant_type", "client_credentials")
                .queryParam("type", "SELF")
                .build();
        try {
            ResponseEntity<TokenResponse> exchange = restTemplate.exchange(build.toString(), HttpMethod.POST, entity, TokenResponse.class);
            return verifyTokenKey(exchange);
        } catch (HttpClientErrorException e) {
            throw new FailResponseException(e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String verifyTokenKey(ResponseEntity<TokenResponse> exchange) {
        String accessToken = Objects.requireNonNull(exchange.getBody()).getAccessToken();
        if (accessToken.isEmpty()) throw new IllegalArgumentException("토큰값이 비었습니다. 해당 몰의 clientId 또는 clientSecret 확인해주세요");
        return accessToken;
    }

    @Override
    public <T extends NaverRequestVerify> void addVerify(T request) {
        this.verifies.add(request);
    }
}
