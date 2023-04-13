package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import lombok.Data;

@Data
public class Delivery {
    private String deliveredDate;
    private String deliveryCompany;
    private String deliveryMethod;
    private String deliveryStatus;
    private String isWrongTrackingNumber;
    private String pickupDate;
    private String sendDate;
    private String trackingNumber;
    private String wrongTrackingNumberRegisteredDate;
    private String wrongTrackingNumberType;
}
