package com.api.naver.smartstore.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailRequest {
    List<String> productOrderIds = new ArrayList<>();

    public List<String> getProductOrderIds() {
        return productOrderIds;
    }

    public void setProductOrderIds(List<String> productOrderIds) {
        this.productOrderIds = productOrderIds;
    }
}
