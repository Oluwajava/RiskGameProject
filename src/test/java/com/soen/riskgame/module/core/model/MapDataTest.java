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

    @Test
    void removeContinent() {
        mapData.addContinent("Africa", 10);
        mapData.removeCountry("Africa");
        Assertions.assertEquals(mapData.getContinents().size(), 0);
    }

    @Test
    void editContinent() {
    }

    @Test
    void addCountry() {
    }

    @Test
    void addCountry1() {
    }

    @Test
    void removeCountry() {
    }

    @Test
    void addNeighbour() {
    }

    @Test
    void removeNeighbour() {
    }

    @Test
    void toFile() {
    }


    @Test
    void buildStringData() {
    }

    @Test
    void addPlayer() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void populateCountries() {
    }

    @Test
    void placeArmy() {
    }

    @Test
    void placeAll() {
    }

    @Test
    void toList() {
    }

    @Test
    void fortifyCountry() {
    }

    @Test
    void fortifyNone() {
    }

    @Test
    void reinforceCountry() {
    }

    @Test
    void isGameStarted() {
    }

    @Test
    void getFileName() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void getCountries() {
    }

    @Test
    void getContinents() {
    }

    @Test
    void setGameStarted() {
    }

    @Test
    void setFileName() {
    }

    @Test
    void setPlayers() {
    }

    @Test
    void setCountries() {
    }

    @Test
    void setContinents() {
    }

}