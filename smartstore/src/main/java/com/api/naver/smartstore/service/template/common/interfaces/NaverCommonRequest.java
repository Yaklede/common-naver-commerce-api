package com.api.naver.smartstore.service.template.common.interfaces;


import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import org.springframework.http.HttpMethod;

public interface NaverCommonRequest<T> {
    ServiceType findServiceType();
    HttpMethod findHttpMethod();
    String findUrl();
    default T findBody() {return null;}
}
