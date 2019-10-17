package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.model.Country;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class GraphUtil {

    private static HashMap<Country, Boolean> visitedCountries;

    private static Set<Country> countries;

    private boolean isConnectedGraph;

    public static boolean isGraphConnected(Set<Country> countrySet) {
        countries = countrySet;
        visitedCountries = new HashMap<>();
        countrySet.forEach(country -> visitedCountries.put(country, false));
        validateGraph();
        return isConnectedGraph();
    }

    private static void validateGraph() {
        for (Country country : countries) {
            for (Country adjacentCountry : country.getAdjacentCountries()) {
                visitedCountries.put(adjacentCountry, true);
            }
        }
    }

    private static boolean isConnectedGraph() {
        Iterator<Country> it = countries.iterator();
        while (it.hasNext()) {
            Country country = it.next();
            if (visitedCountries.get(country) == false) {
                return false;
            }
        }
        return true;
    }


}
