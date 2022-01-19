package com.parimatch.tech.converter;

import com.parimatch.tech.common.Response;
import io.restassured.http.Headers;
import io.restassured.response.ResponseOptions;


public class ResponseConverter {

    public static <T> Response getResponseView(ResponseOptions response, Class<T> outputClass) {
        int statusCode = response.getStatusCode();

        Headers headers = response.getHeaders();

        Object body = response.body().jsonPath().getObject(".", outputClass);

        return Response.builder()
            .statusCode(statusCode)
            .headers(headers)
            .body(body)
            .build();
    }

}
