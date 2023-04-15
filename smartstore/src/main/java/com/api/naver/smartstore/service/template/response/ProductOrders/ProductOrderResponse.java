package com.api.naver.smartstore.service.template.response.ProductOrders;

import com.api.naver.smartstore.service.template.common.NaverCommonResponse;
import com.api.naver.smartstore.service.template.enumeration.ServiceType;
import com.api.naver.smartstore.service.template.response.ProductOrders.embedded.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductOrderResponse {
    @JsonProperty("cancel")
    private Cancel cancel;
    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("exchange")
    private Exchange exchange;
    @JsonProperty("order")
    private Order order;
    @JsonProperty("productOrder")
    private ProductOrder productOrder;
    @JsonProperty("return")
    private Return aReturn;
}
