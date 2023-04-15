package com.api.naver.smartstore.service.template.common.interfaces;

import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import com.api.naver.smartstore.service.template.verify.NaverRequestVerify;

public interface NaverCommerceTemplate {
    <T extends NaverCommonRequest<?>, R extends NaverCommonResponse> R execute(NaverToken token, T reqeust, Class<R> response);
    <T extends NaverRequestVerify> void addVerify(T request);
}
