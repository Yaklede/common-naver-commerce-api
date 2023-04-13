package com.api.naver.smartstore.service.template.common;


import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import org.springframework.http.HttpMethod;

public interface NaverCommonRequest<T> {
    ServiceType findServiceType();
    HttpMethod findHttpMethod();
    String findUrl();
    default T findBody() {return null;}

    Class<? extends NaverCommonResponse> findResponseType();
}
