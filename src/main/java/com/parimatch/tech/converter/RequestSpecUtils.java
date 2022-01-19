package com.parimatch.tech.converter;

import com.parimatch.tech.common.Request;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtils {

    private RequestSpecBuilder requestSpecBuilder;

    private RequestSpecUtils() {
        this.requestSpecBuilder = new RequestSpecBuilder();
    }

    public static RequestSpecification convertRequestToRequestSpecification(Request request) {
        return new RequestSpecUtils()
            .setRequestContentType(request)
            .setHeaders(request)
            .setQuery(request)
            .setBody(request)
            .build();
    }

    private RequestSpecUtils setRequestContentType(Request request) {
        requestSpecBuilder.setContentType(request.getRequestContentType());
        return this;
    }

    private RequestSpecUtils setHeaders(Request request) {
        if (request.getHeaders() != null) {
            requestSpecBuilder.addHeaders(request.getHeaders());
        }
        return this;
    }

    private RequestSpecUtils setQuery(Request request) {
        if (request.getQuery() != null) {
            requestSpecBuilder.addQueryParams(request.getQuery());
        }
        return this;
    }

    private RequestSpecUtils setBody(Request request) {
        if (request.getBody() != null) {
            requestSpecBuilder.setBody(request.getBody());
        }
        return this;
    }


    private RequestSpecification build() {
        requestSpecBuilder.setUrlEncodingEnabled(false);
        return requestSpecBuilder.build();
    }
}
