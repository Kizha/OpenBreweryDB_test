package com.parimatch.tech.pojo;

import lombok.Data;

@Data
public class BreweryView {

    private String id;
    private String name;
    private String brewery_type;
    private String street;
    private String address_2;
    private String address_3;
    private String city;
    private String state;
    private String county_province;
    private String postal_code;
    private String country;
    private Double longitude;
    private Double latitude;
    private String phone;
    private String website_url;
    private String updated_at;
    private String created_at;
}
