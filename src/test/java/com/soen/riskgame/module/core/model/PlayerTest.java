package com.soen.riskgame.module.core.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * test class for player test
 */
public class PlayerTest {
    /**
     * object for player
     */
    private Player player;

    /**
     * initize the objects
     */
    @Before
    public void setupContext() {
        player = new Player();
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());
        countries.add(new Country());
        countries.add(new Country());
        countries.add(new Country());
        countries.add(new Country());
        player.setCountries(countries);
    }

    /**
     * test method for  getNumberOfReinforcementArmy
     */
    @Test
    public void getNumberOfReinforcementArmy() {
        assertEquals(player.getNumberOfReinforcementArmy(), 2);
    }
}