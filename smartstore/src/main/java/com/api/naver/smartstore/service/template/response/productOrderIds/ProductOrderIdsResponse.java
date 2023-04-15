package com.api.naver.smartstore.service.template.response.productOrderIds;

import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductOrderIdsResponse implements NaverCommonResponse {
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("data")
    private String[] productOrderIds;
    @Override
    public ServiceType findServiceType() {
        return ServiceType.PRODUCT_ORDER_IDS;
    }

}
