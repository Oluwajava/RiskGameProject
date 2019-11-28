package com.soen.riskgame.module.core.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PlayerTest {

    private Player player;

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

    @Test
    public void getNumberOfReinforcementArmy() {
        assertEquals(player.getNumberOfReinforcementArmy(), 2);
    }
}