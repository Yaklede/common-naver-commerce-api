package com.api.naver.smartstore.service.template.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 공통된 리스폰스를 만들어주는 객체입니다.
 * 제네릭 타입 T를 사용하여 payload 필드에 어떠한 객체도 담을 수 있습니다.
 */
@Data
@Builder
public class ApplicationResponse<T> {
    /**
     * API 요청의 결과를 담는 필드입니다.
     */
    private ApplicationResult result;
    /**
     * API 요청의 결과로 반환될 객체를 담는 필드입니다.
     */
    private T payload;
}
