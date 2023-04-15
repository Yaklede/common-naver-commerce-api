package com.api.naver.smartstore.advice;


import com.api.naver.smartstore.service.template.common.ApplicationResponse;
import com.api.naver.smartstore.service.template.common.ApplicationResult;
import com.api.naver.smartstore.service.template.exception.RequestVerifyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ApplicationResponse<String> IllegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return ApplicationResponse.<String>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message("잘못된 요청입니다.")
                                .build()
                )
                .payload(
                        exception.getMessage()
                )
                .build();
    }

    @ExceptionHandler(RequestVerifyException.class)
    public ApplicationResponse<String> requestVerifyException(RequestVerifyException requestVerifyException) {
        return ApplicationResponse.<String>builder()
                .result(
                        ApplicationResult.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message("잘못된 요청입니다.")
                                .build()
                )
                .payload(
                        requestVerifyException.getMessage()
                )
                .build();
    }
}
