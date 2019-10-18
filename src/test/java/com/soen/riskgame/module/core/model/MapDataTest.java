package com.soen.riskgame.module.core.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDataTest {

    private MapData mapData;

    @BeforeEach
    public void setupContext() {
        mapData = new MapData();
        mapData.setFileName("ameroki");
    }


    @Test
    void shouldAddCountryToContinent() {
        mapData.addContinent("Africa", 10);
        Country country = new Country();
        country.setContinentId("1");
        mapData.addCountryToContinent(country);
        Assertions.assertEquals(mapData.getContinents().get("1").getCountries().size(), 1);
    }

    @Test
    void shouldAddContinentToMapData() {
        mapData.addContinent("Africa", 10);
        Assertions.assertEquals(mapData.getContinents().size(), 1);
    }


}