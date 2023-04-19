package com.api.naver.smartstore.service.template.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * API 요청의 결과를 나타내는 객체입니다.
 */
@Data
@Builder
public class ApplicationResult {
    /**
     * API 요청 결과의 상태 코드를 담는 필드입니다.
     */
    private Integer code;
    /**
     * API 요청 결과의 상태 메시지를 담는 필드입니다.
     */
    private String message;
}
