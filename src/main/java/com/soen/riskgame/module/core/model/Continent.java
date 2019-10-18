package com.soen.riskgame.module.core.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Continent is the final stage of the data that is parsed  from the map files.
 * Parsed data from mapper class is passed to DTO classes.
 * which will send the data to Continent class
 * Continent class will contains all the information from DTO class and also additional details like
 * list of the countries
 *
 * @see com.soen.riskgame.module.core.mapper
 */
@Data
public class Continent {
    /**
     * Contains the id of the continent
     */
    private Long id;

    /**
     * contains the name of the continent
     */
    private String name;

    /**
     * contains the control value of the particular continent
     */
    private int controlValue;

    /**
     * contains the color by which the continent will be represented
     */
    private String color;

    /*
     * List of countries in a particular continent.
     */
    private List<Country> countries;

    /**
     * parameterized constructed of the Continent class
     * @param name name of the continent
     * @param controlValue control value of the continent
     * @param color color of the continent
     */

    public Continent(String name, int controlValue, String color) {
        this.name = name;
        this.controlValue = controlValue;
        this.color = color;
        countries = new ArrayList<>();
    }

    /**
     * Method to add country to the continent
     * @param country object of the country class that has to be added to a particular continent.
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * Method to remove country from the continent
     * @param name name of the country  that has to be removed from a particular continent.
     */
    public void removeCountry(String name) {
        this.countries = countries.stream().filter(v -> !(v.getName().equalsIgnoreCase(name))).collect(Collectors.toList());
    }

    /*
     * toString method to print the object.
     */
    @Override
    public String toString() {
        return "Continent{" +
                "name='" + name + '\'' +
                ", controlValue=" + controlValue +
                ", color='" + color + '\'' +
                '}';
    }


}