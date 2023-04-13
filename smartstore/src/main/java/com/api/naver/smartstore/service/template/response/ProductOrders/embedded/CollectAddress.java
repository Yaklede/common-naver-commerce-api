package com.api.naver.smartstore.service.template.response.ProductOrders.embedded;

import lombok.Data;

@Data
public class CollectAddress {
    private String addressType;
    private String baseAddress;
    private String city;
    private String country;
    private String detailedAddress;
    private String name;
    private String state;
    private String tel1;
    private String tel2;
    private String zipCode;
    private String isRoadNameAddress;
}
