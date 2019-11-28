package com.soen.riskgame.module.core.interfaces;
/**
 * Interface CountryAction  is the interface to provide the basic functionality related to the entity Country
 * @author Hitansh,Mayokun
 */
public interface CountryAction {
	 /**
     * Method addCountry is to add a Country
     * @param countryName name of the Country to be added
     * @param continentName name of the continent in which the country would be added.
     */
    void addCountry(String countryName, String continentName);
    /**
     * Method to add the countries
     * @param countryName name of the country
     * @param continentName name of the continent
     * @param xCoordinate coordinate of x plane
     * @param yCoordinate coordinate of y plane
     */
    void addCountry(String countryName, String continentName, String xCoordinate, String yCoordinate);
    /**
     * Method removeCountry is to remove a particular country from a Map
     * @param country name of the country
     */
    void removeCountry(String country);
    /**
     * Method addNeighbour provides the functionality of adding neighbor of a particular country
     * @param countryName name of the country
     * @param neighborCountryName name of the neighbor country
     */
    void addNeighbour(String countryName, String neighborCountryName);
    /**
     * Method removeCountry provides the functionality of removing a country
     * @param countryName name of the country
     * @param neighbourCountryName name of the neighbor country
     */
    void removeNeighbour(String countryName, String neighbourCountryName);

}
