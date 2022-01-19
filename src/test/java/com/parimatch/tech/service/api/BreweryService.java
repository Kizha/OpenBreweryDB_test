package com.parimatch.tech.service.api;

import com.parimatch.tech.common.Request;
import com.parimatch.tech.common.Response;

import com.parimatch.tech.pojo.BreweryView;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BreweryService extends BaseService {

    @Value("${open.brewery.db.search.uri}")
    private String searchBreweriesUri;


    public Response searchBreweries(Map queries, String jwtToken) {
        return executeRequest(Request.builder()
            .resourcePath(baseHostUrl + searchBreweriesUri)
            .method(Method.GET)
            .query(queries)
            .requestContentType(ContentType.JSON)
            .contentType(ContentType.JSON.toString())
            .outputClass(List.class)
            .jwtToken("Bearer " + jwtToken)
            .build()
        );
    }

}

