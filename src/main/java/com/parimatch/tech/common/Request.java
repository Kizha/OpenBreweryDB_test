package com.parimatch.tech.common;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Request<T, S> {

    private String resourcePath;
    private Method method;
    private String contentType;
    private ContentType requestContentType;
    private Map headers;
    private T body;
    private Map query;
    private String jwtToken;
    private Class outputClass;
}
