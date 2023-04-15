package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.NaverCommonRequest;

public interface NaverRequestVerify {
    <T extends NaverCommonRequest<?>> void verifyRequest(T request);
}
