package com.api.naver.smartstore.crypto;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtils {
    public static String issueClientSecretSign(String clientId, String clientSecret, Long timestamp) {
        //Password 생성
        String password = StringUtils.joinWith( "_",clientId, timestamp);

        //hashedPassword 생성
        String hashedPassword = BCrypt.hashpw(password, clientSecret);

        //encoding 작업
        return Base64.getUrlEncoder().encodeToString(hashedPassword.getBytes(StandardCharsets.UTF_8));
    }
}
