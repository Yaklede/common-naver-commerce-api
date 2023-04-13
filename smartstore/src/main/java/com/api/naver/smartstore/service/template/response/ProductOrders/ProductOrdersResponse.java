package com.api.naver.smartstore.service.template.response.ProductOrders;

import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductOrdersResponse implements NaverCommonResponse {

    @Override
    public ServiceType findServiceType() {
        return ServiceType.PRODUCT_ORDERS;
    }

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("data")
    private List<ProductOrderResponse> data;
}
