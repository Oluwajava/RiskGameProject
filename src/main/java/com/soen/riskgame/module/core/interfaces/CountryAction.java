package com.soen.riskgame.module.core.interfaces;
/**
 * Interface CountryAction  is the interface to provide the basic functionality related to the entity Country
 * @author Hitansh,Mayokun
 */
public interface CountryAction {

    void addCountry(String countryName, String continentName);

    void addCountry(String countryName, String continentName, String xCoordinate, String yCoordinate);

    void removeCountry(String country);

    void addNeighbour(String countryName, String neighborCountryName);

    void removeNeighbour(String countryName, String neighbourCountryName);

}
