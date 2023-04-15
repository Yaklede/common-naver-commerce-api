package com.api.naver.smartstore.service.template.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse<T> {
    private ApplicationResult result;
    private T payload;
}
