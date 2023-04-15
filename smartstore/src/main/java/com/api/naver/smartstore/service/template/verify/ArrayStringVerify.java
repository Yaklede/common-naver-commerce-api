package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.NaverCommonRequest;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;

import java.lang.reflect.Field;

public class ArrayStringVerify implements NaverRequestVerify {
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
            if (data instanceof String[]) {
                String[] stringArrays = (String[]) data;
                if (stringArrays.length == 0) {
                    throw new RequestVerifyException();
                }
                for (String string : stringArrays) {
                    if (string.isEmpty()) {
                        throw new RequestVerifyException();
                    }
                }
            }
        }
    }
}
