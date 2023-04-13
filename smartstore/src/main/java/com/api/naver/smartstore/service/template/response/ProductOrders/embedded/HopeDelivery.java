package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import lombok.Data;

@Data
public class HopeDelivery {
    private String region;
    private String additionalFee;
    private String hopeDeliveryYmd;
    private String hopeDeliveryHm;
    private String changeReason;
    private String changer;
}
