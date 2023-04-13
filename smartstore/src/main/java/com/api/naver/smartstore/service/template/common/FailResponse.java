package com.api.naver.smartstore.service.template.common;

import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FailResponse implements NaverCommonResponse {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("traceId")
    private String traceId;

    @Override
    public ServiceType findServiceType() {
        return ServiceType.FAIL_RESPONSE;
    }
}
