package com.fresca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Aircraft {
    private Long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;

    @JsonIgnore
    private Set<Passenger> passengers = new LinkedHashSet<>();

    @JsonIgnore
    private Set<Airport> airports = new LinkedHashSet<>();


    //Constructors
    public Aircraft() {}

    public Aircraft(String type, String airlineName, int numberOfPassengers) {
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }


    //Getter and Setter Methods
    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
    public int getNumberOfPassengers() { return numberOfPassengers; }
    public void setNumberOfPassengers(int numberOfPassengers) { this.numberOfPassengers = numberOfPassengers; }

    public Set<Passenger> getPassengers() { return passengers; }
    public Set<Airport> getAirports() { return airports; }

    public void addPassenger(Passenger p) { passengers.add(p); }
    public void removePassenger(Passenger p) { passengers.remove(p); }
    public void addAirport(Airport a) { airports.add(a); }
    public void removeAirport(Airport a) { airports.remove(a); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;
        Aircraft aircraft = (Aircraft) o;
        return id != null && Objects.equals(id, aircraft.id);
    }

    @Override public int hashCode() { return getClass().hashCode(); }
}
