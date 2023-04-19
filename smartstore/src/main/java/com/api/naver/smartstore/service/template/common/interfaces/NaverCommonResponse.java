package com.api.naver.smartstore.service.template.common.interfaces;

import com.api.naver.smartstore.service.template.enumeration.ServiceType;

/**
 * 네이버 커머스 API에서 사용되는 모든 응답 객체가 구현해야 하는 인터페이스입니다.
 */
public interface NaverCommonResponse {
    /**
     * 해당 응답 객체가 사용되는 서비스의 유형을 반환합니다.
     *
     * @return 해당 응답 객체가 사용되는 서비스의 유형
     */
    ServiceType findServiceType();
}
