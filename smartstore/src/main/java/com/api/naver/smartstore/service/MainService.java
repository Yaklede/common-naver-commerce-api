package com.api.naver.smartstore.service;

import com.api.naver.smartstore.crypto.CryptoUtils;
import com.api.naver.smartstore.dto.OrderDetailRequest;
import com.api.naver.smartstore.dto.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MainService {
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public MainService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getToken(String clientId, String clientSecret) {
        Long timestamp = System.currentTimeMillis();
        String sign = CryptoUtils.issueClientSecretSign(clientId, clientSecret, timestamp);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.commerce.naver.com/external/v1/oauth2/token";

        UriComponents build = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("client_id", clientId)
                .queryParam("timestamp", timestamp)
                .queryParam("client_secret_sign", sign)
                .queryParam("grant_type", "client_credentials")
                .queryParam("type", "SELF")
                .build();
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(build.toString(), HttpMethod.POST, entity, String.class);
            return exchange.getBody();
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getOrderInfo(String token, String orderId) {
        HttpHeaders headers = makeBaseHeader(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.commerce.naver.com/external/v1/pay-order/seller/orders/" + orderId +"/product-order-ids";

        try {
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return exchange.getBody();
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getOrderInfoDetail(String token, OrderDetailRequest request) throws JsonProcessingException {
        HttpHeaders headers = makeBaseHeader(token);
        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest,headers);

        String url = "https://api.commerce.naver.com/external/v1/pay-order/seller/product-orders/query";

        try {
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return exchange.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return e.getResponseBodyAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private HttpHeaders makeBaseHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        //공백까지 넣어야 함
        headers.add("Authorization","Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
