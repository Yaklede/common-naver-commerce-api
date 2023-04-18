package com.api.naver.smartstore.service.template.verify;

public class ArrayStringVerify extends AbstractNaverRequestVerify<String[]> {
    @Override
    public Class<String[]> settingVerifyType() {
        return String[].class;
    }

    @Override
    public Boolean verify(String[] data) {
        if (data.length == 0) {
           return false;
        }
        for (String string : data) {
            if (string.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
