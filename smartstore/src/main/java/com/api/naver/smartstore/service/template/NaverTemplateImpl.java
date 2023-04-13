package com.api.naver.smartstore.service.template;

import com.api.naver.smartstore.crypto.CryptoUtils;
import com.api.naver.smartstore.service.template.common.*;
import com.api.naver.smartstore.service.template.response.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class NaverTemplateImpl implements NaverCommerceTemplate {

    private final String clientId = "2NcVTI6acZnnWZcjbZNsD4";
    private final String clientSecret = "$2a$04$69VomQ67OctjHL1LuJGBPO";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    public NaverTemplateImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private final Map<String, NaverToken> store = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends NaverCommonRequest> NaverCommonResponse execute(String shoppingMallName, T request) throws JsonProcessingException {
        try {
            HttpEntity<T> entity = makeHttpEntity(shoppingMallName, request);
            return (NaverCommonResponse) restTemplate.exchange(request.findUrl(), request.findHttpMethod(), entity, request.findResponseType()).getBody();
        } catch (HttpClientErrorException e) {
            return objectMapper.readValue(e.getResponseBodyAsString(), FailResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private <T extends NaverCommonRequest<T>> HttpEntity<?> makeHttpEntity(String shoppingMallName, T request) {
        HttpHeaders headers = makeBaseHeader(getToken(shoppingMallName));
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

    private String getToken(String shoppingMallName) {
        store.put("mall", new NaverToken(clientId, clientSecret));
        NaverToken naverToken = store.get(shoppingMallName);

        Long timestamp = System.currentTimeMillis();
        String sign = CryptoUtils.issueClientSecretSign(naverToken, timestamp);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String getTokenUrl = "https://api.commerce.naver.com/external/v1/oauth2/token";

        UriComponents build = UriComponentsBuilder.fromHttpUrl(getTokenUrl)
                .queryParam("client_id", naverToken.getClientId())
                .queryParam("timestamp", timestamp)
                .queryParam("client_secret_sign", sign)
                .queryParam("grant_type", "client_credentials")
                .queryParam("type", "SELF")
                .build();
        try {
            ResponseEntity<TokenResponse> exchange = restTemplate.exchange(build.toString(), HttpMethod.POST, entity, TokenResponse.class);
            return exchange.getBody().getAccessToken();
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
