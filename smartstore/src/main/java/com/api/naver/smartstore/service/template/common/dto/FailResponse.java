package com.api.naver.smartstore.service.template.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 네이버 커머스 API에서 공통으로 내려주는 에러 Response 객체입니다.
 */
@Data
@Builder
public class FailResponse {
    /**
     * 에러 코드를 담는 필드입니다.
     */
    @JsonProperty("code")
    private String code;
    /**
     * 에러 메시지를 담는 필드입니다.
     */
    @JsonProperty("message")
    private String message;
    /**
     * API 요청이 실패한 시간을 담는 필드입니다.
     */
    @JsonProperty("timestamp")
    private String timestamp;
    /**
     * API 요청의 고유한 추적 ID를 담는 필드입니다.
     */
    @JsonProperty("traceId")
    private String traceId;
}
