package com.api.naver.smartstore.service.template.verify;

public class IntegerVerify extends AbstractNaverRequestVerify<Integer> {
    @Override
    public Boolean verify(Integer data) {
        if (data == null) return false;
        if (data >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
