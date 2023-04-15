package com.api.naver.smartstore.service.template.crypto;

import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtils {
    public static String issueClientSecretSign(NaverToken token, Long timestamp) {
        //Password 생성
        String password = StringUtils.joinWith( "_",token.getClientId(), timestamp);

        //hashedPassword 생성
        String hashedPassword = BCrypt.hashpw(password, token.getClientSecret());

        //encoding 작업
        return Base64.getUrlEncoder().encodeToString(hashedPassword.getBytes(StandardCharsets.UTF_8));
    }
}
