package com.api.naver.smartstore.service.template.request;

import com.api.naver.smartstore.service.template.common.NaverCommonRequest;
import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.api.naver.smartstore.service.template.response.ProductOrders.ProductOrdersResponse;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class ProductOrdersRequest implements NaverCommonRequest<String[]> {
    @Override
    public ServiceType findServiceType() {
        return ServiceType.PRODUCT_ORDERS;
    }
    @Override
    public HttpMethod findHttpMethod() {
        return HttpMethod.POST;
    }
    @Override
    public String findUrl() {
        return "https://api.commerce.naver.com/external/v1/pay-order/seller/product-orders/query";
    }

    @Override
    public String[] findBody() {
        return this.productOrderIds;
    }

    @Override
    public Class<? extends NaverCommonResponse> findResponseType() {
        return ProductOrdersResponse.class;
    }

    private String[] productOrderIds;
}
