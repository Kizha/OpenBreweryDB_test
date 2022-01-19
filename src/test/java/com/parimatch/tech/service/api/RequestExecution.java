package com.parimatch.tech.service.api;

import com.parimatch.tech.common.Request;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

interface RequestExecution {

    default ResponseOptions executeRequest(Request request,
        RequestSpecification requestSpecification) {
        return
            given()
                .spec(requestSpecification)
                .urlEncodingEnabled(true)
            .when()
                .request(request.getMethod(), request.getResourcePath())
            .then()
                .contentType(request.getContentType())
                .extract()
                .response();
    }
}
