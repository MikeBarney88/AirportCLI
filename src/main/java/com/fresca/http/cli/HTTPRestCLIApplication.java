package com.fresca.http.cli;

import com.fresca.domain.Aircraft;
import com.fresca.domain.Airport;
import com.fresca.domain.Passenger;
import com.fresca.domain.City;
import com.fresca.http.client.RESTClient;

import java.util.List;

public class HTTPRestCLIApplication {
    private RESTClient restClient;

    public String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }


    public String generatePassengerAircraftReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName()).append(" ").append(passenger.getLastName());
            report.append(" has flown on: ");

            List<Aircraft> aircraft = getRestClient().getAircraftForPassenger(passenger.getId());

            if (aircraft.isEmpty()) {
                report.append("No aircraft");
            } else {
                for (int i = 0; i < aircraft.size(); i++) {
                    report.append(aircraft.get(i).getType());
                    if (i < aircraft.size() - 1) {
                        report.append(", ");
                    }
                }
            }

            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append("; ");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    
    public String generateCitiesReport() {
        List<City> cities = getRestClient().getAllCities();

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            report.append(city.getName());
            report.append(", ");
            report.append(city.getState());
            report.append(" - ");
            report.append(city.getPopulation());

            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append(",\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public String generateAircraftAirportReport() {
        List<Aircraft> aircraftList = getRestClient().getAllAircraft();

        StringBuffer report = new StringBuffer();

        for (Aircraft aircraft : aircraftList) {
            report.append(aircraft.getType()).append(" (").append(aircraft.getAirlineName()).append(")");
            report.append(" takes off from and lands at: ");

            List<Airport> airports = getRestClient().getAirportsForAircraft(aircraft.getId());

            if (airports.isEmpty()) {
                report.append("No airports");
            } else {
                for (int i = 0; i < airports.size(); i++) {
                    report.append(airports.get(i).getName()).append(" (").append(airports.get(i).getCode()).append(")");
                    if (i < airports.size() - 1) {
                        report.append(", ");
                    }
                }
            }

            if (aircraftList.indexOf(aircraft) != (aircraftList.size() - 1)) {
                report.append("; ");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public String generatePassengerAirportsReport() {
        List<Airport> airports = getRestClient().getPassengerAirports();

        StringBuffer report = new StringBuffer();

        if (airports == null || airports.isEmpty()) {
            report.append("No passenger airport data available.");
            System.out.println(report);
            return report.toString();
        }

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" (");
            report.append(airport.getCode());
            report.append(")");

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }


    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        String serverURL = args[0];

        if (serverURL != null && !serverURL.isEmpty()) {

            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);

            cliApp.setRestClient(restClient);

            if (serverURL.contains("aircrafts")) {
                cliApp.generateAircraftAirportReport();
            } else if (serverURL.contains("airports")) {
                cliApp.generateAirportReport();
            } else if (serverURL.contains("cities") || serverURL.contains("city")) {
                cliApp.generateCitiesReport();
            } else if (serverURL.contains("passengers/airports")) {
                cliApp.generatePassengerAirportsReport();
            } else if (serverURL.contains("passengers")) {
                cliApp.generatePassengerAircraftReport();
            } else {
                System.out.println("Error: Unrecognized URL.");
                System.out.println("Please use a URL containing one of: aircraft, airports, cities, passengers");}
        }
    }
}

