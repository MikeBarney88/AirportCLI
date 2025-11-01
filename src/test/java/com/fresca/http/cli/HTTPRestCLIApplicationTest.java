package com.fresca.http.cli;

import com.fresca.domain.Aircraft;
import com.fresca.domain.Airport;
import com.fresca.domain.City;
import com.fresca.domain.Passenger;
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


    @Test void testGeneratePassengerAircraftReport() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        RESTClient mockRESTClient = new RESTClient() {
            @Override public List<Passenger> getAllPassengers() {
                List<Passenger> passengers = new ArrayList<>();
                Passenger passenger1 = new Passenger("Joey", "Thomas", "111-111-1111");
                passenger1.setId(1L);

                Passenger passenger2 = new Passenger("Michael", "Barney", "222-222-2222");
                passenger2.setId(2L);

                Passenger passenger3 = new Passenger("Brandon", "Pike", "333-333-3333");
                passenger3.setId(3L);

                passengers.add(passenger1);
                passengers.add(passenger2);
                passengers.add(passenger3);

                return passengers;
            }


            @Override public List<Aircraft> getAircraftForPassenger(Long passengerId) {
                List<Aircraft> aircrafts = new ArrayList<>();

                if (passengerId == 1) {
                    aircrafts.add(new Aircraft("Boeing 747", "JoeySkies", 300));
                } else if (passengerId == 2) {
                    aircrafts.add(new Aircraft("Airbus", "Joline", 400));
                } else if (passengerId == 3) {
                    aircrafts.add(new Aircraft("Crop Duster", "Figley", 4));
                }

                return aircrafts;
            }
        };
        cliApp.setRestClient(mockRESTClient);


        String report = cliApp.generatePassengerAircraftReport();
        Assertions.assertEquals("Joey Thomas has flown on: Boeing 747; Michael Barney has flown on: Airbus; Brandon Pike has flown on: Crop Duster", report);
    }


    @Test void testGenerateAircraftAirportReport() {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        RESTClient mockRESTClient = new RESTClient() {
            @Override public List<Aircraft> getAllAircraft() {
                List<Aircraft> aircrafts = new ArrayList<>();
                Aircraft aircraft1 = new Aircraft("Boeing 747", "JoeySkies", 300);
                aircraft1.setId(1L);

                Aircraft aircraft2 = new Aircraft("Airbus", "Joline", 400);
                aircraft2.setId(2L);

                Aircraft aircraft3 = new Aircraft("Crop Duster", "Figley", 4);
                aircraft3.setId(3L);

                aircrafts.add(aircraft1);
                aircrafts.add(aircraft2);
                aircrafts.add(aircraft3);

                return aircrafts;
            }


            @Override public List<Airport> getAirportsForAircraft(Long passengerId) {
                List<Airport> airports = new ArrayList<>();


                if (passengerId == 1) {
                    airports.add(new Airport("Toronto Pearson International Airport", "YYZ"));
                } else if (passengerId == 2) {
                    airports.add(new Airport("Bishop Toronto City Airport", "YTZ"));
                } else if (passengerId == 3) {
                    airports.add(new Airport("Totally Real Toronto Airport", "TRT"));
                }

                return airports;
            }
        };
        cliApp.setRestClient(mockRESTClient);


        String report = cliApp.generateAircraftAirportReport();
        Assertions.assertEquals("Boeing 747 (JoeySkies) takes off from and lands at: Toronto Pearson International Airport (YYZ); Airbus (Joline) takes off from and lands at: Bishop Toronto City Airport (YTZ); Crop Duster (Figley) takes off from and lands at: Totally Real Toronto Airport (TRT)", report);
    }
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