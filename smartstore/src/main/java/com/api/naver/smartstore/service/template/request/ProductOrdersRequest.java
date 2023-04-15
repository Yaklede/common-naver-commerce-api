package com.api.naver.smartstore.service.template.request;

import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private String[] productOrderIds;
}
