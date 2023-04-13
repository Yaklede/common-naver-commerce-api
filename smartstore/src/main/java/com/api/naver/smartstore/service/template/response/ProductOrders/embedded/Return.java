package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import com.api.naver.smartstore.service.template.response.ProductOrders.embedded.CollectAddress;
import lombok.Data;

@Data
public class Return {
    private Integer claimDeliveryFeeDemandAmount;
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
    private String holdbackDetailedReason;
    private String holdbackReason;
    private String holdbackStatus;
    private String refundExpectedDate;
    private String refundStandbyReason;
    private String refundStandbyStatus;
    private String requestChannel;
    private String returnDetailedReason;
    private String returnReason;
    private CollectAddress returnReceiveAddress;
    private String returnCompletedDate;
    private String holdbackConfigDate;
    private String holdbackConfigurer;
    private String holdbackReleaseDate;
    private String holdbackReleaser;
    private String claimDeliveryFeeProductOrderIds;
    private Integer claimDeliveryFeeDiscountAmount;
    private Integer remoteAreaCostChargeAmount;
}
