package com.api.naver.smartstore.controller;


import com.api.naver.smartstore.service.template.common.ApplicationResponse;
import com.api.naver.smartstore.service.template.common.ApplicationResult;
import com.api.naver.smartstore.service.template.common.NaverCommerceTemplate;
import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.request.ProductOrderIdsRequest;
import com.api.naver.smartstore.service.template.request.ProductOrdersRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final NaverCommerceTemplate naverCommerceTemplate;

    public MainController(NaverCommerceTemplate naverCommerceTemplate) {
        this.naverCommerceTemplate = naverCommerceTemplate;
    }
    @GetMapping("/order/{shoppingMall}")
    public ApplicationResponse<NaverCommonResponse> getOrderInfo(
            @PathVariable("shoppingMall") String shoppingMall,
            @RequestParam("orderId") String orderId
    ) throws JsonProcessingException {
        return ApplicationResponse.<NaverCommonResponse>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.OK.value())
                                .message("주문 정보 조회에 성공했습니다.")
                                .build()
                )
                .payload(
                        naverCommerceTemplate.execute(shoppingMall, new ProductOrderIdsRequest(orderId))
                )
                .build();
    }

    @PostMapping("/orderDetail/{shoppingMall}")
    public ApplicationResponse<NaverCommonResponse> getOrderDetail(
            @PathVariable("shoppingMall") String shoppingMall,
            @RequestBody ProductOrdersRequest productOrdersRequest
    ) throws JsonProcessingException {
        return ApplicationResponse.<NaverCommonResponse>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.OK.value())
                                .message("주문 정보 조회에 성공했습니다.")
                                .build()
                )
                .payload(
                        naverCommerceTemplate.execute(shoppingMall, productOrdersRequest)
                )
                .build();
    }
}
