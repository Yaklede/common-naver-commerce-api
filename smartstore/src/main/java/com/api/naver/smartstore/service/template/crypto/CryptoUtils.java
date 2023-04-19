package com.api.naver.smartstore.service.template.crypto;

import com.api.naver.smartstore.service.template.common.dto.NaverToken;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtils {
    /**
     * 이 메소드는 클라이언트 ID, 타임스탬프 및 클라이언트 비밀번호를 조합하여 NaverToken을 발급하는 데 필요한 Sign 정보를 생성합니다.
     * @param token 클라이언트 ID 및 비밀번호가 포함된 NaverToken 객체입니다.
     * @param timestamp Sign 정보에 사용될 타임스탬프입니다.
     * @return 발급된 NaverToken의 Sign 정보입니다.
     */
    public static String issueClientSecretSign(NaverToken token, Long timestamp) {
        // Password 생성
        String password = StringUtils.joinWith("_", token.getClientId(), timestamp);

        // hashedPassword 생성
        String hashedPassword = BCrypt.hashpw(password, token.getClientSecret());

        // encoding 작업
        return Base64.getUrlEncoder().encodeToString(hashedPassword.getBytes(StandardCharsets.UTF_8));
    }
}
