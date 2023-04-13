package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cancel {
    private String cancelApprovalDate;
    private String cancelCompletedDate;
    private String cancelDetailedReason;
    private String cancelReason;
    private String claimRequestDate;
    private String claimStatus;
    private String refundExpectedDate;
    private String refundStandbyReason;
    private String refundStandbyStatus;
    private String requestChannel;
}
