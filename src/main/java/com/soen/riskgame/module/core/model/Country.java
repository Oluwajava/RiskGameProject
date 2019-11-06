package com.soen.riskgame.module.core.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class Country is the final stage of the data that is parsed  from the map files.
 * Parsed data from mapper class is passed to DTO classes.
 * which will send the data to Country class
 * Country class will contains all the information from DTO class and also additional details like player info, noOfArmies
 * list of the adjacent countries
 *
 * @see com.soen.riskgame.module.core.mapper
 * @author Sai Sukruth
 */
@Data
@EqualsAndHashCode(exclude = {"continent", "adjacentCountries"})
public class Country {

    /**
     * contains country name
     */
    private String name;
    /**
     * contains country id value
     */
    private long id;
    /**
     * contains continent name in which the country is present
     */
    private Continent continent;

    /**
     * contains details of the player who is holding the country
     */
    private Player player;
    /**
     * contains country X coordinate data while using the GUI
     */

    private String xCoordinate;
    /**
     * contains country Y coordinate data while using the GUI
     */
    private String yCoordinate;
    /**
     * contains noOfArmies that are present in the country
     */
    private int noOfArmies = 0;

    /**
     * contains list of adjacent countries
     */
    private List<Country> adjacentCountries;
    /**
     * contains the continent Id data the country is in
     */
    private String continentId;

    /**
     * default constructor of the country class
     */
    public Country() {
        adjacentCountries = new ArrayList<>();
    }

    /**
     * parameterized constructor of the Country class with name of the country
     *
     * @param name name refers to the name of the country
     */

    public Country(String name) {
        this.name = name;
        adjacentCountries = new ArrayList<>();
    }

    /**
     * Parameterized constructor of the Country class
     *
     * @param id          id is country id reference
     * @param name        name refers to the name if the country
     * @param xCoordinate X coordinate contains country X coordinate data while using the GUI
     * @param yCoordinate Y coordinate contains country Y coordinate data while using the GUI
     */
    public Country(Long id, String name, String xCoordinate, String yCoordinate) {
        this.id = id;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.adjacentCountries = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Method to add army
     */
    public void addArmy() {
        noOfArmies++;
    }

    /**
     * Method to add army
     * @param num number of armies
     */
    public void addArmy(int num) {
        noOfArmies += num;
    }

    /**
     * Method to remove army
     * @param num number of armies
     */
    public void removeArmy(int num) {
        noOfArmies -= num;
    }

    /**
     * Method to add neighbour
     * @param country name of the country
     */
    public void addNeighbour(Country country) {
        this.adjacentCountries.add(country);
    }

    /**
     * method to remove neighbour
     * @param country name of the country
     */
    public void removeNeighbour(Country country) {
        this.adjacentCountries = adjacentCountries.stream().filter(v -> !(v.equals(country))).collect(Collectors.toList());
    }

    /**
     * Methid to chcek if the Country is Adjacent
     * @param countryName
     * @return boolean value either True or False
     */
    public boolean isCountryAdjacent(String countryName) {
        for (Country country: adjacentCountries) {
            if (country.getName().equalsIgnoreCase(countryName)) {
                return true;
            }
        }
        return false;
    }
}
