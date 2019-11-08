package com.soen.riskgame.module.core.utils;

import java.util.HashSet;
import java.util.Set;
import com.soen.riskgame.module.core.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphUtilTest {

    @Test
    public void shouldReturnTrueForConnectedGraph() {
        Set<Country> countries = new HashSet<Country>();
        Country nigeria = new Country("Nigeria");
        nigeria.setId(1);
        Country ghana = new Country("Ghana");
        ghana.setId(2);
        Country uk = new Country("UK");
        uk.setId(3);
        Country sweden = new Country("Sweden");
        sweden.setId(4);

        nigeria.addNeighbour(ghana);
        nigeria.addNeighbour(uk);
        uk.addNeighbour(sweden);

        countries.add(nigeria);
        countries.add(ghana);
        countries.add(uk);
        countries.add(sweden);

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
        Country sweden = new Country("Sweden");
        sweden.setId(4);

        nigeria.addNeighbour(ghana);
        uk.addNeighbour(sweden);

        countries.add(nigeria);
        countries.add(ghana);
        countries.add(uk);
        countries.add(sweden);

        GraphUtil graphUtil = new GraphUtil(countries);
        Assertions.assertFalse(graphUtil.isConnected());
        System.out.println(nigeria.getAdjacentCountries());
    }


}