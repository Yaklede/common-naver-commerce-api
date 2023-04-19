package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;

/**
 * 주어진 요청 객체의 필드를 검증하기 위한 인터페이스
 */
public interface NaverRequestVerify {
    /**
     * 주어진 요청 객체의 필드를 검증합니다.
     *
     * @param request 검증할 요청 객체
     * @param <T> 요청 객체의 타입 파라미터
     * @throws RequestVerifyException 검증 실패 시 발생하는 예외
     */
    <T extends NaverCommonRequest<?>> void verifyRequest(T request);
}
