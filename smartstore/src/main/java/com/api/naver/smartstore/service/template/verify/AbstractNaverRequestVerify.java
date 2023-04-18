package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
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
            } catch (RequestVerifyException requestVerifyException) {
                throw requestVerifyException;
            } catch (Exception e) {
                throw new IllegalArgumentException("검증 도중 에러가 발생하였습니다.");
            }

            if (settingVerifyType().isInstance(data)) {
                if (!verify(data)) throw new RequestVerifyException();
            }
        }
    }
    public abstract Class<V> settingVerifyType();
    public abstract Boolean verify(V data);
}
