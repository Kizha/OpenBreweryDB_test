package com.parimatch.tech.tests;

import com.parimatch.tech.pojo.BreweryView;
import com.parimatch.tech.service.api.BreweryService;
import com.parimatch.tech.utils.StringConstants;
import com.parimatch.tech.common.Response;

import java.util.List;
import java.util.Map;

import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BreweryServiceTest extends BaseTest {

    @Autowired
    private BreweryService breweryService;

    // TODO: bug - endpoint returned empty list
    @Test
    public void positiveSearchBreweries() {
        Response response = breweryService.searchBreweries(null, null);
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        List<BreweryView> breweryList = (List<BreweryView>) response.getBody();
        softAssertions.assertThat(breweryList.isEmpty()).isFalse();
    }


    @Parameters(method = "positiveSearchingParameters")
    @Test
    public void positiveSearchBreweriesWithQuery(String queryParameter, String queryValue,
        String jwtToken) {
        Map<String, String> query = Map.of(queryParameter, queryValue);

        Response response = breweryService.searchBreweries(query, jwtToken);
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        List<BreweryView> breweryList = (List<BreweryView>) response.getBody();
        softAssertions.assertThat(breweryList.isEmpty()).isFalse();
    }


    @Parameters(method = "negativeSearchingParameters")
    @Test
    public void negativeSearchBreweries(String queryParameter, String queryValue) {
        Map<String, String> query = Map.of();
        if (queryParameter != null && queryValue != null) {
            query = Map.of(queryParameter, queryValue);
        }

        Response response = breweryService.searchBreweries(query, null);
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        List<BreweryView> breweryList = (List<BreweryView>) response.getBody();
        softAssertions.assertThat(breweryList.isEmpty()).isTrue();
    }


    // TODO: bug - too many entities returned
    @Test
    public void negativeSearchBreweriesTooManyParams() {
        Map<String, String> query = Map.of("query", "value", "by_city", "Oakland");

        Response response = breweryService.searchBreweries(query, null);
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        List<BreweryView> breweryList = (List<BreweryView>) response.getBody();
        softAssertions.assertThat(breweryList.isEmpty()).isTrue();
    }


    private static Object[][] positiveSearchingParameters() {
        return new Object[][]{
            {StringConstants.QUERY_PARAM, "p", null}, // TODO: bug - body should not be returned
            {StringConstants.QUERY_PARAM, "pa", null}, // TODO: bug - body should not be returned
            {StringConstants.QUERY_PARAM, "pair", null},
            {StringConstants.QUERY_PARAM, "United_States", null},
            // TODO: bug - query is not encoded
            {StringConstants.QUERY_PARAM, "United%20States", null},
            // TODO: bug - query is not encoded
            {StringConstants.QUERY_PARAM, "Oakland", "some_token"},
            {StringConstants.QUERY_PARAM, "-122", "some_token"},
            {StringConstants.QUERY_PARAM, "530", null},
        };
    }

    private static Object[][] negativeSearchingParameters() {
        return new Object[][]{
            {"", "dog"}, {"", "dog"}, {StringConstants.QUERY_PARAM, null},
            {"by_city", "Oakland"}, {StringConstants.QUERY_PARAM, "%"},
            {StringConstants.QUERY_PARAM, "!@#$%^"},
            {StringConstants.QUERY_PARAM, "&"},
            // TODO: bug - there were lot of entities in the result
            {StringConstants.QUERY_PARAM, "*"},
            // TODO: bug - there were lot of entities in the result
        };
    }
}
