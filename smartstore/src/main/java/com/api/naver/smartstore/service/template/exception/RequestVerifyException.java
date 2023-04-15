package com.api.naver.smartstore.service.template.exception;

public class RequestVerifyException extends RuntimeException {
    public RequestVerifyException() {
        super("request값은 필수입니다.");
    }
}
