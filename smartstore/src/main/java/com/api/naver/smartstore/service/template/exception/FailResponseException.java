package com.api.naver.smartstore.service.template.exception;

import org.springframework.web.client.HttpClientErrorException;

public class FailResponseException extends RuntimeException {
    public FailResponseException(String message) {
        super(message);
    }
}
