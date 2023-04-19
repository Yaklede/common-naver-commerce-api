package com.api.naver.smartstore.service.template;

import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import com.api.naver.smartstore.service.template.common.interfaces.NaverCommerceTemplate;
import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonResponse;
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

/**
 * Naver API 호출을 위한 템플릿 클래스
 */
@Component
public class NaverTemplateImpl implements NaverCommerceTemplate {
    private final RestTemplate restTemplate;

    public NaverTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final List<NaverRequestVerify> verifies = new ArrayList<>();

    /**
     * Naver API 호출 메소드
     * @param token NaverToken 객체
     * @param request API 호출에 필요한 정보를 담고 있는 Request 객체
     * @param response API 호출 결과를 담고 있는 Response 객체
     * @param <T> Request 객체 타입
     * @param <R> Response 객체 타입
     * @return Naver API의 응답 객체
     * @throws FailResponseException API 호출이 실패한 경우 발생하는 예외
     */
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("naver api 호출 도중 알 수 없는 에러가 발생했습니다.");
        }
    }
    /**
     * NaverRequestVerify를 구현한 객체들의 verifyRequest 메소드를 호출하여 검증하는 메소드
     * @param request 검증할 NaverCommonRequest 인터페이스를 구현한 객체
     * @param response 검증할 NaverCommonResponse 인터페이스를 구현한 객체
     * @param <T> NaverCommonRequest를 상속받은 객체의 타입
     * @param <R> NaverCommonResponse를 상속받은 객체의 타입
     * @throws RequestVerifyException 검증이 실패한 경우 발생하는 예외
     */
    private <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> void validation(T request, Class<R> response) {
        verifies.forEach(naverRequestVerify -> naverRequestVerify.verifyRequest(request));
        verifyServiceType(request, response);
    }

    /**
     * 요청 객체와 응답 객체의 서비스 타입이 일치하는지 검증합니다.
     * @param request 서비스 타입을 검증할 요청 객체
     * @param response 서비스 타입을 검증할 응답 객체의 클래스
     * @throws IllegalArgumentException 요청 객체와 응답 객체의 서비스 타입이 일치하지 않을 경우 발생합니다.
     * @throws RuntimeException 서비스 타입 검증 중 알 수 없는 예외 발생 시 발생합니다.
     */
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

    /**
     * Naver Token을 이용하여 인증 정보를 만들어 HttpEntity를 생성합니다.
     *
     * @param token NaverToken 객체
     * @param request Http 요청에 필요한 객체
     * @param <T> NaverCommonRequest를 상속받는 제네릭 타입
     * @return HttpEntity 객체
     */
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

    /**
     * NaverToken을 이용하여 API 호출에 필요한 토큰을 생성합니다.
     *
     * @param token NaverToken 객체
     * @return API 호출에 필요한 토큰
     * @throws FailResponseException API 호출이 실패했을 때 예외 발생
     */
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

/**
 * 토큰 정보에서 Access Token을 추출하여 반환합니다.
 *
 * @param exchange API 호출 결과로 받은 ResponseEntity 객체
 * @return Access Token
 * @throws IllegalArgumentException Access Token이 비어있을 때 예외발생
 */
 private String verifyTokenKey(ResponseEntity<TokenResponse> exchange) {
        String accessToken = Objects.requireNonNull(exchange.getBody()).getAccessToken();
        if (accessToken.isEmpty())
            throw new IllegalArgumentException("토큰값이 비었습니다. 해당 몰의 clientId 또는 clientSecret 확인해주세요");
        return accessToken;
    }

    /**
     * 요청 검증 객체를 추가합니다.
     * @param request 추가할 요청 검증 객체
     * @param <T> NaverRequestVerify를 구현한 객체여야 합니다.
     */
    @Override
    public <T extends NaverRequestVerify> NaverCommerceTemplate addVerify(T request) {
        this.verifies.add(request);
        return this;
    }
}
