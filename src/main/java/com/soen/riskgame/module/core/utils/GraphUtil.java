package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.model.Country;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class will take care of the validation of the graph
 *
 *
 */


public class GraphUtil {

    private HashMap<Country, Boolean> visitedCountries;

    private Set<Country> countrySet;

    private boolean isConnectedGraph;

    /**
     * This will return a boolean whether depending upon the graph is connected or not
     *
     * @param countrySet
     * @return
     */
    public GraphUtil(Set<Country> countrySet) {
        this.countrySet = countrySet;
        this.visitedCountries = new HashMap<>();
        countrySet.forEach(country -> visitedCountries.put(country, false));
    }


    /**
     * Method for traversing the graph for depth first traversal.
     *
     * @param startCountry Starting country
     */
    private void depthFirstTraversal(Country startCountry) {
        visitedCountries.put(startCountry, true);
        Iterator<Country> it = startCountry.getAdjacentCountries().iterator();
        while (it.hasNext()) {
            Country adjacentCountry = it.next();
            if (!visitedCountries.get(adjacentCountry)) {
                depthFirstTraversal(adjacentCountry);
            }
        }
    }

    /**
     * Method for checking if the graph is connected or not.
     *
     * @return true if map is connect; else false.
     */
    public boolean isConnected() {
        depthFirstTraversal(countrySet.iterator().next());
        Iterator<Country> it = countrySet.iterator();
        while (it.hasNext()) {
            Country country = it.next();
            if (visitedCountries.get(country) == false) {
                System.out.println("Map is not a connected graph.");
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if graph is connected.
     *
     * @return true or false
     */
    public boolean isConnectedSubGraph() {
        depthFirstTraversalSubGraph(countrySet.iterator().next());
        Iterator<Country> it = countrySet.iterator();
        while (it.hasNext()) {
            Country country = it.next();
            if (visitedCountries.get(country) == false) {
                System.out.println("Continent " + country.getContinent() + " is not connected.");
                return false;
            }
        }
        return true;
    }

    /**
     * Method to traverse subGraph in depth first pattern.
     *
     * @param startCountry	starting country
     */
    private void depthFirstTraversalSubGraph(Country startCountry) {
        visitedCountries.put(startCountry, true);
        Iterator<Country> it = startCountry.getAdjacentCountries().iterator();
        while (it.hasNext()) {
            Country adjacentCountry = it.next();
            if (countrySet.contains(adjacentCountry)) {
                if (!visitedCountries.get(adjacentCountry)) {
                    depthFirstTraversalSubGraph(adjacentCountry);
                }
            }
        }
    }
}
