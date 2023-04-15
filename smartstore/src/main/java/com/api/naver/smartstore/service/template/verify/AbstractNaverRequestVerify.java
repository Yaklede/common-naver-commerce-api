package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.NaverCommonRequest;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;

import java.lang.reflect.Field;

public abstract class AbstractNaverRequestVerify<V> implements NaverRequestVerify {
    @Override
    public <T extends NaverCommonRequest<?>> void verifyRequest(T request) {
        Field[] declaredFields = request.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            V data;
            try {
                data = (V) declaredField.get(request);
                if (data == null) throw new RequestVerifyException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (settingVerifyType().isInstance(data)) {
                if (!verify(data)) throw new RequestVerifyException();
            }
        }
    }
    public abstract Class<V> settingVerifyType();
    public abstract Boolean verify(V data);
}
