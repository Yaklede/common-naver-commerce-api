package com.api.naver.smartstore.service.template.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 네이버 API 인증 정보를 담는 공통 토큰 클래스입니다.
 */
@Getter
@AllArgsConstructor
public class NaverToken {
    /**
     * 네이버 API 클라이언트 ID를 담는 필드입니다.
     */
    private String clientId;
    /**
     * 네이버 API 클라이언트 secret을 담는 필드입니다.
     */
    private String clientSecret;
}
