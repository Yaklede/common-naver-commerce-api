package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import lombok.Data;

@Data
public class Order {
    private String chargeAmountPaymentAmount;
    private String checkoutAccumulationPaymentAmount;
    private String generalPaymentAmount;
    private String naverMileagePaymentAmount;
    private String orderDate;
    private String orderDiscountAmount;
    private String orderId;
    private String ordererId;
    private String ordererName;
    private String ordererTel;
    private String paymentDate;
    private String paymentDueDate;
    private String paymentMeans;
    private String isDeliveryMemoParticularInput;
    private String payLocationType;
    private String ordererNo;
    private String payLaterPaymentAmount;
}
