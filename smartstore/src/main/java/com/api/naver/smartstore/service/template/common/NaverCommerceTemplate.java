package com.api.naver.smartstore.service.template.common;

import com.api.naver.smartstore.service.template.verify.NaverRequestVerify;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.InvocationTargetException;

public interface NaverCommerceTemplate {
    <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> R execute(NaverToken token, T reqeust, Class<R> response);
    <T extends NaverRequestVerify> void addVerify(T request);
}
