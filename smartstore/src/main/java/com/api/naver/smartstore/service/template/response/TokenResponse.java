package com.api.naver.smartstore.service.template.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Integer expiredIn;
    @JsonProperty("token_type")
    private String tokenType;
}
