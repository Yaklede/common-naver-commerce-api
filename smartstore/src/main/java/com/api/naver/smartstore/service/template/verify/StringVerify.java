package com.api.naver.smartstore.service.template.verify;

public class StringVerify extends AbstractNaverRequestVerify<String> {
    @Override
    public Class<String> settingVerifyType() {
        return String.class;
    }
    @Override
    public Boolean verify(String data) {
        return !data.isEmpty();
    }
}
