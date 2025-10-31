package com.fresca;

import com.fresca.domain.Airport;
import com.fresca.domain.City;
import com.fresca.http.cli.HTTPRestCLIApplication;
import com.fresca.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



public class HTTPRestCLIApplicationTest {
    @Test void testGenerateAirportReport() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        RESTClient mockRESTClient = new RESTClient() {
            @Override public List<Airport> getAllAirports() {
                List<Airport> airports = new ArrayList<>();
                airports.add(new Airport("Toronto Pearson International Airport", "YYZ"));
                airports.add(new Airport("Bishop Toronto City Airport", "YTZ"));
                return airports;
            }
        };
        cliApp.setRestClient(mockRESTClient);


        String report = cliApp.generateAirportReport();
        Assertions.assertEquals("Toronto Pearson International Airport - YYZ,\nBishop Toronto City Airport - YTZ", report);
    }


    @Test void testGenerateCitiesReport() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        RESTClient mockRESTClient = new RESTClient() {
            @Override public List<City> getAllCities() {
                List<City> cities = new ArrayList<>();
                cities.add(new City("St. Johns", "Newfoundland and Labrador", 250000));
                cities.add(new City("Toronto", "Ontario", 3200000));
                return cities;
            }
        };
        cliApp.setRestClient(mockRESTClient);


        String report = cliApp.generateCitiesReport();
        Assertions.assertEquals("St. Johns, Newfoundland and Labrador - 250000,\nToronto, Ontario - 3200000", report);
    }
}