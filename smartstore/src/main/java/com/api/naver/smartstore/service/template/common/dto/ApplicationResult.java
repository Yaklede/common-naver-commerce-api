package com.api.naver.smartstore.service.template.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResult {
    private Integer code;
    private String message;
}
