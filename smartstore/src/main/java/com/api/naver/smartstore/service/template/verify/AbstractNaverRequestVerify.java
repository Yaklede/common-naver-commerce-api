package com.api.naver.smartstore.service.template.verify;

import com.api.naver.smartstore.service.template.common.interfaces.NaverCommonRequest;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public abstract class AbstractNaverRequestVerify<V> implements NaverRequestVerify {
    private List<V> verifyList = new ArrayList<>();
    private int verifyCount = 0;

    /**
     * 주어진 request의 필드를 검증합니다.
     *
     * @param request 검증할 request
     * @param <T>     request의 타입
     * @throws RequestVerifyException 검증에 실패한 필드가 있는 경우 발생하는 예외
     */
    @Override
    public <T extends NaverCommonRequest<?>> void verifyRequest(T request) {
        gerRequestFieldData(request);
        for (V data : verifyList) {
            try {
                if (verifyList.get(verifyCount).getClass().isInstance(data)) {
                    if (!verify(data)) {
                        clearFieldData();
                        throw new RequestVerifyException();
                    }
                }
            } catch (ClassCastException classCastException) {
                verifyCount++;
            }
        }
        clearFieldData();
    }

    private void clearFieldData() {
        verifyList.clear();
        verifyCount = 0;
    }

    /**
     * 주어진 request의 필드에서 검증할 데이터를 추출합니다.
     *
     * @param request 검증할 request
     * @param <T>     request의 타입
     */
    @SuppressWarnings("unchecked")
    private <T extends NaverCommonRequest<?>> void gerRequestFieldData(T request) {
        Field[] declaredFields = request.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                V data = (V) declaredField.get(request);
                verifyList.add(data);
                if (data == null) throw new RequestVerifyException();
            } catch (RequestVerifyException requestVerifyException) {
                throw requestVerifyException;
            } catch (Exception e) {
                throw new IllegalArgumentException("검증 도중 에러가 발생하였습니다.");
            }
        }
    }
    /**
     * 주어진 데이터를 검증합니다.
     *
     * @param data 검증할 데이터
     * @return 검증 결과
     */
    public abstract Boolean verify(V data);
}
