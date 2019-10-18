package com.soen.riskgame.module.core.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
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
    void getNumberOfReinforcementArmy() {
        Assertions.assertEquals(player.getNumberOfReinforcementArmy(), 2);
    }
}