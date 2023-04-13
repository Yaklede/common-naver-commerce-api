package com.api.naver.smartstore.controller;


import com.api.naver.smartstore.crypto.CryptoUtils;
import com.api.naver.smartstore.dto.OrderDetailRequest;
import com.api.naver.smartstore.dto.TokenResponse;
import com.api.naver.smartstore.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    public static Map<String, String> map = new HashMap<>();
    private final MainService mainService;
    private final ObjectMapper objectMapper;

    public MainController(MainService mainService, ObjectMapper objectMapper) {
        this.mainService = mainService;
        this.objectMapper = objectMapper;
    }

    /**
     * clientId와 ClientSecret키는 추후에 등록시 DB넣는 형식으로 해서 가져와야 할 것 같음
     */
    @GetMapping("/token")
    public String getToken() throws JsonProcessingException {
        String clientId = "2NcVTI6acZnnWZcjbZNsD4";
        String clientSecret = "$2a$04$69VomQ67OctjHL1LuJGBPO";

        String jsonToken = mainService.getToken(clientId, clientSecret);
        TokenResponse tokenResponse = objectMapper.readValue(jsonToken, TokenResponse.class);

        map.put("token",tokenResponse.getAccess_token());
        return jsonToken;
    }

    @GetMapping("/order/{orderId}")
    public String getOrderInfo(
            @PathVariable("orderId") String orderId
    ) {
        return mainService.getOrderInfo(map.get("token"), orderId);
    }

    @PostMapping("/orderDetail")
    public String getOrderDetail(
            @RequestBody OrderDetailRequest orderDetailRequest
    ) {
        try {
            return mainService.getOrderInfoDetail(map.get("token"), orderDetailRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "알 수 없는 에러 발생";
        }
    }
}
