package com.fresca.domain;

public class City {
    private long id;
    private String name, state;
    private long population;


    //Constructors
    public City() {

    }

    public City(String name, String state, long population) {
        this.name = name;
        this.state = state;
        this.population = population;
    }


    //Instance Methods
    @Override
    public String toString() {
        return String.format("City[id = %d, name = \"%s\", state = \"%s\", population = %d]", this.id, this.name, this.state, this.population);
    }


    //Getter Methods
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public long getPopulation() {
        return this.population;
    }


    //Setter Methods
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}