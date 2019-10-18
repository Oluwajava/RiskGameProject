package com.soen.riskgame.module.core.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.soen.riskgame.module.core.model.Country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GraphUtilTest {


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void shouldReturnTrueForConnectedGraph() {
        Set<Country> countries = new HashSet<Country>();
        Country country = new Country();
        List<Country> adjacentCountries = new ArrayList<Country>();
        Country country1 = new Country();
        adjacentCountries.add(country1);
        country.setAdjacentCountries(adjacentCountries);
        countries.add(country);
        GraphUtil graphUtil = new GraphUtil(countries);
        Assertions.assertTrue(graphUtil.isConnected());

    }

    @Test
    public void shouldReturnFalseForNotConnectedGraph() {
        Set<Country> countries = new HashSet<Country>();
        Country nigeria = new Country("Nigeria");
        nigeria.setId(1);
        Country ghana = new Country("Ghana");
        ghana.setId(2);
        Country uk = new Country("UK");
        uk.setId(3);

        nigeria.addNeighbour(ghana);
        ghana.addNeighbour(nigeria);

        countries.add(nigeria);
        countries.add(ghana);
        countries.add(uk);
        GraphUtil graphUtil = new GraphUtil(countries);
        Assertions.assertFalse(graphUtil.isConnected());
    }


}