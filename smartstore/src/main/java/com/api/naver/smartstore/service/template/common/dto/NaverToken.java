package com.api.naver.smartstore.service.template.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NaverToken {
    private String clientId;
    private String clientSecret;
}
