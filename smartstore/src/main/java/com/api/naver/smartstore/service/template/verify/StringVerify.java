package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.NaverCommonRequest;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;

import java.lang.reflect.Field;

public class StringVerify implements NaverRequestVerify {
    @Override
    public <T extends NaverCommonRequest<?>> void verifyRequest(T request) {
        Field[] declaredFields = request.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object data;
            try {
                data = declaredField.get(request);
                if (data == null) throw new RequestVerifyException();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (data instanceof String && ((String) data).isEmpty()) {
                throw new RequestVerifyException();
            }
        }
    }
}
