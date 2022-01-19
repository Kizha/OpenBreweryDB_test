package com.parimatch.tech.common;

import io.restassured.http.Headers;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T> {

    private int statusCode;
    private Headers headers;
    private T body;
}
