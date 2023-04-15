package com.api.naver.smartstore.service.template.builder;

import com.api.naver.smartstore.service.template.common.dto.FailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class FailResponseBuilder {
    private final ObjectMapper objectMapper;

    public FailResponseBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public FailResponse build(String responseMessage) {
        try {
            return objectMapper.readValue(responseMessage, FailResponse.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("실패 response를 만드는데 실패했습니다.");
        }
    }
}
