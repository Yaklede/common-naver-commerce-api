package com.api.naver.smartstore.service.template.request;

import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.api.naver.smartstore.service.template.common.NaverCommonRequest;
import com.api.naver.smartstore.service.template.response.productOrderIds.ProductOrderIdsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderIdsRequest implements NaverCommonRequest {

    @Override
    public ServiceType findServiceType() {
        return ServiceType.PRODUCT_ORDER_IDS;
    }

    @Override
    public HttpMethod findHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String findUrl() {
        return "https://api.commerce.naver.com/external/v1/pay-order/seller/orders/"+orderId+"/product-order-ids";
    }
    private String orderId;
}
