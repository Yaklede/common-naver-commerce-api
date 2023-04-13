package com.api.naver.smartstore.service.template.common;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface NaverCommerceTemplate {
    <T extends NaverCommonRequest> NaverCommonResponse execute(String shoppingMallName, T reqeust) throws JsonProcessingException;
}
