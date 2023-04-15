package com.api.naver.smartstore.service;

import com.api.naver.smartstore.service.template.builder.FailResponseBuilder;
import com.api.naver.smartstore.service.template.common.FailResponse;
import com.api.naver.smartstore.service.template.common.NaverCommerceTemplate;
import com.api.naver.smartstore.service.template.common.NaverToken;
import com.api.naver.smartstore.service.template.request.ProductOrderIdsRequest;
import com.api.naver.smartstore.service.template.request.ProductOrdersRequest;
import com.api.naver.smartstore.service.template.response.ProductOrders.ProductOrdersResponse;
import com.api.naver.smartstore.service.template.response.productOrderIds.ProductOrderIdsResponse;
import com.api.naver.smartstore.service.template.verify.ArrayStringVerify;
import com.api.naver.smartstore.service.template.verify.StringVerify;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NaverApiService {
    private final NaverCommerceTemplate naverCommerceTemplate;
    private final FailResponseBuilder failResponseBuilder;
    private final String clientId = "2NcVTI6acZnnWZcjbZNsD4";
    private final String clientSecret = "$2a$04$69VomQ67OctjHL1LuJGBPO";
    private static Map<String, NaverToken> testDatabase = new ConcurrentHashMap<>();
    public NaverApiService(NaverCommerceTemplate naverCommerceTemplate, FailResponseBuilder failResponseBuilder) {
        this.naverCommerceTemplate = naverCommerceTemplate;
        this.failResponseBuilder = failResponseBuilder;
    }

    public ProductOrderIdsResponse getProductOrderIds(String shoppingMall, ProductOrderIdsRequest productOrderIdsRequest) {
        naverCommerceTemplate.addVerify(new StringVerify());
        return naverCommerceTemplate.execute(getNaverToken(shoppingMall), productOrderIdsRequest, ProductOrderIdsResponse.class);
    }

    public ProductOrdersResponse getProductOrder(String shoppingMall, ProductOrdersRequest productOrdersRequest) {
        naverCommerceTemplate.addVerify(new ArrayStringVerify());
        return naverCommerceTemplate.execute(getNaverToken(shoppingMall), productOrdersRequest, ProductOrdersResponse.class);
    }

    /**
     * 상용에서는 해당 데이터가 저장된 DB에서 꺼내야함
     */
    private NaverToken getNaverToken(String shoppingMall) {
        testDatabase.put("testMall",new NaverToken(clientId,clientSecret));
        return testDatabase.get(shoppingMall);
    }

    public FailResponse createFailResponse(String responseMessage) {
        return failResponseBuilder.build(responseMessage);
    }
}
