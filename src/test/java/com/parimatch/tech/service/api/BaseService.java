package com.parimatch.tech.service.api;

import com.parimatch.tech.converter.ResponseConverter;
import com.parimatch.tech.common.Request;
import com.parimatch.tech.common.Response;
import io.restassured.response.ResponseOptions;
import org.springframework.beans.factory.annotation.Value;

import static com.parimatch.tech.converter.RequestSpecUtils.convertRequestToRequestSpecification;

abstract class BaseService implements RequestExecution {

    @Value("${open.brewery.db.url}")
    String baseHostUrl;

    Response executeRequest(Request request) {
        ResponseOptions responseOptions = executeRequest(request,
            convertRequestToRequestSpecification(request)
        );
        return ResponseConverter.getResponseView(responseOptions, request.getOutputClass());
    }

}
