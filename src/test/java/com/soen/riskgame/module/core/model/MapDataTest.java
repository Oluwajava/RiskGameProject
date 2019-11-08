package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDataTest {

    private MapData mapData;
    private MapData mapData2;

    @BeforeEach
    public void setupContext() {
        mapData = new MapData();
        mapData2 = new MapData();
        try {
            FileReader fileReader = new FileReader("/Users/oluwajava/Documents/Software Engineering/Advance Programming Practice/RiskGameProject/src/main/resources/maps/eurasien.map");
            String mapDataStr = fileReader.readData().replaceAll(MapDelimiters.CARRIAGE_DELIMITER, "");
            MapParser mapParser = new MapParser(mapDataStr);
            mapData2 = new Map.Builder(mapParser.getGameFile(), mapParser.getCountries(), mapParser.getContinentDTOS(), mapParser.getBorderDTOS()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapData2.addPlayer("John");
        mapData2.addPlayer("Jide");
    }

    @Test
    void shouldAddCountryToContinent() {
        mapData.addContinent("Africa", 10);
        Country country = new Country();
        country.setContinentId("1");
        mapData.addCountryToContinent(country);
        assertEquals(mapData.getContinents().get("1").getCountries().size(), 1);
    }

    @Test
    void shouldAddContinentToMapData() {
        mapData.addContinent("Africa", 10);
        assertEquals(mapData.getContinents().size(), 1);
    }

    @Test
    void testCorrectStartUpPhaseBeforePlaceAll() {
        mapData2.populateCountries();
        boolean valid = true;
        for (Country country : mapData2.getCountries().values()) {
            if (country.getNoOfArmies() <= 0)
                valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testCorrectStartUpPhaseAfterPlaceAll() {
        mapData2.populateCountries();
        mapData2.placeAll();
        boolean valid = true;
        for (Country country : mapData2.getCountries().values()) {
            if (country.getNoOfArmies() <= 0)
                valid = false;
        }
        assertTrue(valid);
    }


}