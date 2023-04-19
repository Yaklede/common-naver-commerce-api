package com.api.naver.smartstore.service.template.common.interfaces;

import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import com.api.naver.smartstore.service.template.verify.NaverRequestVerify;
/**
 * 네이버 커머스 API를 호출하는데 사용되는 템플릿입니다.
 */
public interface NaverCommerceTemplate {

    /**
     * 네이버 커머스 API를 호출하고 응답을 반환합니다.
     *
     * @param token    API 호출에 사용할 액세스 토큰
     * @param request  API 호출에 사용할 요청 객체
     * @param response API 호출 결과를 담을 응답 클래스
     * @param <T>      요청 객체의 타입
     * @param <R>      응답 객체의 타입
     * @return API 호출 결과에 대한 응답 객체
     */
    <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> R execute(NaverToken token, T request, Class<R> response);

    /**
     * 네이버 API 호출 전에 요청 객체를 검증하는 검증기를 추가합니다.
     *
     * @param verifier 추가할 검증기 객체
     * @param <T>      검증기 객체의 타입
     * @return 검증기를 추가한 템플릿 객체
     */
    <T extends NaverRequestVerify> NaverCommerceTemplate addVerify(T verifier);
}
