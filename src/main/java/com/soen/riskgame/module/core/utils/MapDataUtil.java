package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;

import java.util.HashMap;

public class MapDataUtil {


    public static Continent findContinentByName(String continentName, HashMap<String, Continent> continents) {
        return continents.entrySet()
                .stream()
                .filter(v -> v.getValue().getName().equalsIgnoreCase(continentName))
                .findFirst()
                .get()
                .getValue();
    }


    public static Country findCountryByName(String countryName, HashMap<String, Country> countries) {
        return countries.entrySet()
                .stream()
                .filter(v -> v.getValue().getName().equalsIgnoreCase(countryName))
                .findFirst()
                .get()
                .getValue();
    }
}
