package com.soen.riskgame.module.core.interfaces;

public interface CountryAction {

    void addCountry(String countryName, String continentName);

    void removeCountry(String country);

    void addNeighbour(String countryName, String neighborCountryName);

    void removeNeighbour(String countryName, String neighbourCountryName);

}
