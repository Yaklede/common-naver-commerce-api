package com.api.naver.smartstore.service.template.common.interfaces;


import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import org.springframework.http.HttpMethod;

/**
 * 네이버 커머스 API에서 사용되는 공통 요청 인터페이스입니다.
 *
 * @param <T> 요청의 바디(body) 타입
 */
public interface NaverCommonRequest<T> {

    /**
     * 해당 요청에 맞는 서비스 타입을 반환합니다.
     *
     * @return 서비스 타입
     */
    ServiceType findServiceType();

    /**
     * 해당 요청에 맞는 HTTP 메소드를 반환합니다.
     *
     * @return HTTP 메소드
     */
    HttpMethod findHttpMethod();

    /**
     * 해당 요청에 맞는 URL을 반환합니다.
     *
     * @return 요청 URL
     */
    String findUrl();

    /**
     * 해당 요청에 맞는 바디(body)를 반환합니다.
     *
     * (해당 요청에 바디(body)가 필요할 시 구현해야한다)
     * @return 요청 바디(body)
     *
     */
    default T findBody() {
        return null;
    }
}
