package com.api.naver.smartstore.controller;


import com.api.naver.smartstore.service.NaverApiService;
import com.api.naver.smartstore.service.template.common.dto.ApplicationResponse;
import com.api.naver.smartstore.service.template.common.dto.ApplicationResult;
import com.api.naver.smartstore.service.template.common.dto.FailResponse;
import com.api.naver.smartstore.service.template.exception.FailResponseException;
import com.api.naver.smartstore.service.template.request.ProductOrderIdsRequest;
import com.api.naver.smartstore.service.template.request.ProductOrdersRequest;
import com.api.naver.smartstore.service.template.response.ProductOrders.ProductOrdersResponse;
import com.api.naver.smartstore.service.template.response.productOrderIds.ProductOrderIdsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final NaverApiService naverApiService;

    public MainController(NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }
    @GetMapping("/order/{shoppingMall}")
    public ApplicationResponse<ProductOrderIdsResponse> getOrderInfo(
            @PathVariable("shoppingMall") String shoppingMall,
            @ModelAttribute ProductOrderIdsRequest productOrderIdsRequest
    ) {
        ProductOrderIdsResponse productOrderIds = naverApiService.getProductOrderIds(shoppingMall, productOrderIdsRequest);
        return ApplicationResponse.<ProductOrderIdsResponse>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.OK.value())
                                .message("주문 정보 조회에 성공했습니다.")
                                .build()
                )
                .payload(
                    productOrderIds
                )
                .build();
    }

    @PostMapping("/orderDetail/{shoppingMall}")
    public ApplicationResponse<ProductOrdersResponse> getOrderDetail(
            @PathVariable("shoppingMall") String shoppingMall,
            @RequestBody ProductOrdersRequest productOrdersRequest
    ) {
        ProductOrdersResponse productOrder = naverApiService.getProductOrder(shoppingMall, productOrdersRequest);
        return ApplicationResponse.<ProductOrdersResponse>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.OK.value())
                                .message("주문 정보 조회에 성공했습니다.")
                                .build()
                )
                .payload(
                        productOrder
                )
                .build();
    }
    @ExceptionHandler(FailResponseException.class)
    public ApplicationResponse<FailResponse> failResponseExceptionHandler(FailResponseException exception) {
        return ApplicationResponse.<FailResponse>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message("API로부터 다음과 같은 실패 응답을 받았습니다.")
                                .build()
                )
                .payload(
                        naverApiService.createFailResponse(exception.getMessage())
                )
                .build();
    }
}
