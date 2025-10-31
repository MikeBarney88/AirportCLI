package com.fresca;

import com.fresca.http.cli.HTTPRestCLIApplication;
import com.fresca.http.client.RESTClient;
import com.fresca.domain.Airport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class HTTPRestCLIApplicationTest {

    @Test
    void testGeneratePassengerAirportsReport_validData() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();


        RESTClient fakeClient = new RESTClient() {
            @Override
            public List<Airport> getPassengerAirports() {
                List<Airport> airports = new ArrayList<>();
                airports.add(new Airport("Toronto Pearson International", "YYZ"));
                airports.add(new Airport("Halifax Stanfield", "YHZ"));
                airports.add(new Airport("St. John's International", "YYT"));
                return airports;
            }
        };

        cliApp.setRestClient(fakeClient);

        String report = cliApp.generatePassengerAirportsReport();

        String expected =
                "Toronto Pearson International (YYZ),\n" +
                        "Halifax Stanfield (YHZ),\n" +
                        "St. John's International (YYT)";

        Assertions.assertEquals(expected, report);
    }


    @Test
    void testGeneratePassengerAirportsReport_emptyList() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();


        RESTClient fakeClient = new RESTClient() {
            @Override
            public List<Airport> getPassengerAirports() {
                return new ArrayList<>();
            }
        };

        cliApp.setRestClient(fakeClient);

        String report = cliApp.generatePassengerAirportsReport();

        Assertions.assertEquals("No passenger airport data available.", report);
    }
}
