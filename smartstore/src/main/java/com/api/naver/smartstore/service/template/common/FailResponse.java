package com.api.naver.smartstore.service.template.common;

import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FailResponse {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("traceId")
    private String traceId;
}
