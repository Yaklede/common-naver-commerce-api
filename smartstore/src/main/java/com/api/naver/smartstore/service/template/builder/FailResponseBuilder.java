package com.api.naver.smartstore.service.template.builder;

import com.api.naver.smartstore.service.template.common.dto.FailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * 공통된 에러 response를 만들어주는 클래스입니다.
 */
@Component
public class FailResponseBuilder {
    private final ObjectMapper objectMapper;

    /**
     * ObjectMapper 객체를 주입받는 생성자입니다.
     * @param objectMapper JSON 문자열을 객체로 변환할 때 사용할 ObjectMapper 객체입니다.
     */
    public FailResponseBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 주어진 JSON 문자열을 FailResponse 객체로 변환하여 반환합니다.
     * @param responseMessage 변환할 JSON 문자열입니다.
     * @return FailResponse 객체입니다.
     * @throws IllegalArgumentException JSON 문자열을 객체로 변환할 수 없는 경우 발생합니다.
     */
    public FailResponse build(String responseMessage) {
        try {
            return objectMapper.readValue(responseMessage, FailResponse.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("에러 response를 만드는데 실패했습니다.");
        }
    }
}
