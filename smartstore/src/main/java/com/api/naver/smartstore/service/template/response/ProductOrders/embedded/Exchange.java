package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import lombok.Data;

@Data
public class Exchange {
    private String claimDeliveryFeeDemandAmount;
    private String claimDeliveryFeePayMeans;
    private String claimDeliveryFeePayMethod;
    private String claimRequestDate;
    private String claimStatus;
    private CollectAddress collectAddress;
    private String collectCompletedDate;
    private String collectDeliveryCompany;
    private String collectDeliveryMethod;
    private String collectStatus;
    private String collectTrackingNumber;
    private String etcFeeDemandAmount;
    private String etcFeePayMeans;
    private String etcFeePayMethod;
    private String exchangeDetailedReason;
    private String exchangeReason;
    private String holdbackDetailedReason;
    private String holdbackReason;
    private String holdbackStatus;
    private String reDeliveryMethod;
    private String reDeliveryStatus;
    private String reDeliveryCompany;
    private String reDeliveryTrackingNumber;
    private CollectAddress returnReceiveAddress;
    private String holdbackConfigDate;
    private String holdbackConfigurer;
    private String holdbackReleaseDate;
    private String holdbackReleaser;
    private String claimDeliveryFeeProductOrderIds;
    private String reDeliveryOperationDate;
    private String claimDeliveryFeeDiscountAmount;
    private String remoteAreaCostChargeAmount;
}
