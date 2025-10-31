package com.fresca.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fresca.domain.Airport;
import com.fresca.domain.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.http.HttpClient;
import java.util.List;

public class RESTClientTest {
    private RESTClient restClient;

    @BeforeEach void setUp() {
        restClient = new RESTClient() {
            @Override public HttpClient getClient() {
                return null;
            }
        };
        restClient.setServerURL("http://localhost:8080/");
    }


    @Test void testGetAllAirports() throws JsonProcessingException {
        String mockJSON = "[{\"name\": \"Toronto Pearson International Airport\", \"code\": \"YYZ\"}]";
        List<Airport> airports = restClient.buildAirportListFromResponse(mockJSON);


        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals("Toronto Pearson International Airport", airports.get(0).getName());
        Assertions.assertEquals("YYZ", airports.get(0).getCode());
    }


    @Test void testGetAllCities() throws JsonProcessingException {
        String mockJSON = "[{\"name\": \"St. Johns\", \"state\": \"Newfoundland and Labrador\", \"population\": 250000}]";
        List<City> cities = restClient.buildCitiesListFromResponse(mockJSON);


        Assertions.assertEquals(1, cities.size());
        Assertions.assertEquals("St. Johns", cities.get(0).getName());
        Assertions.assertEquals("Newfoundland and Labrador", cities.get(0).getState());
    }
}