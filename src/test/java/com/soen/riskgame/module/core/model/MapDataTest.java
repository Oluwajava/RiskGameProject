package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.RoundRobin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for class MapData
 */
public class MapDataTest {

    /**
     * Holds map data
     */
    private MapData mapData;

    /**
     * Holds map data
     */
    private MapData mapData2;

    /**
     * Holds player data for John
     */
    Player john;

    /**
     * Holds player data for Jide
     */
    Player jide;

    /**
     * This method sets the map and game context
     */
    @Before
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
        john = mapData2.addPlayer("John");
        jide = mapData2.addPlayer("Jide");
    }

    /**
     * test AddCountryToContinent
     */
    @Test
    public void shouldAddCountryToContinent() {
        mapData.addContinent("Africa", 10);
        Country country = new Country();
        country.setContinentId("1");
        mapData.addCountryToContinent(country);
        assertEquals(mapData.getContinents().get("1").getCountries().size(), 1);
    }

    /**
     * test AddContinentToMapData
     */
    @Test
    public void shouldAddContinentToMapData() {
        mapData.addContinent("Africa", 10);
        assertEquals(mapData.getContinents().size(), 1);
    }

    /**
     * test CorrectStartUpPhase Before PlaceAll
     */
    @Test
    public void testCorrectStartUpPhaseBeforePlaceAll() {
        mapData2.populateCountries();
        boolean valid = true;
        for (Country country : mapData2.getCountries().values()) {
            if (country.getNoOfArmies() <= 0)
                valid = false;
        }
        assertTrue(valid);
    }

    /**
     * test CorrectStartUpPhase After PlaceAll
     */
    @Test
    public void testCorrectStartUpPhaseAfterPlaceAll() {
        mapData2.populateCountries();
        mapData2.placeAll();
        boolean valid = true;
        for (Country country : mapData2.getCountries().values()) {
            if (country.getNoOfArmies() <= 0)
                valid = false;
        }
        assertTrue(valid);
    }

    /**
     * test Attack With Correct Conditions
     */
    @Test
    public void testAttackWithCorrectConditions() {
        boolean valid;
        Country country17 = mapData2.getCountries().get("17");
        Country country18 = mapData2.getCountries().get("18");
        mapData2.populateCountries();
        mapData2.placeAll();
        country17.setPlayer(john);
        country18.setPlayer(jide);
        country17.setNoOfArmies(5);
        country18.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(country17.getName(), country18.getName());
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            country17.setNoOfArmies(3);
            country18.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (country17.getNoOfArmies() == 1 && country18.getNoOfArmies() == 2) {
                valid = true;
            }
            else {
                valid = false;
            }
        }
        else {
            valid = false;
        }
        assertTrue(valid);
    }

    /**
     * test Attack in the wrong phase
     */
    @Test
    public void testAttackWrongPhase() {
        boolean valid;
        Country country17 = mapData2.getCountries().get("17");
        Country country18 = mapData2.getCountries().get("18");
        String country17Owner;
        String country18Owner;

        do {
            mapData2.populateCountries();
            mapData2.placeAll();
            country17Owner = country17.getPlayer().getPlayerName();
            country18Owner = country18.getPlayer().getPlayerName();
        } while (country17Owner.equalsIgnoreCase("John") && country18Owner.equalsIgnoreCase("Jide"));

        country17.setNoOfArmies(5);
        country18.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.REINFORCEMENT);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(country17.getName(), country18.getName());
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            country17.setNoOfArmies(3);
            country18.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.REINFORCEMENT);
            mapData2.attackMove(2);
            if (country17.getNoOfArmies() == 1 && country18.getNoOfArmies() == 2) {
                valid = false;
            }
            else {
                valid = true;
            }
        }
        else {
            valid = true;
        }
        assertTrue(valid);
    }

    /**
     * test Attack with non adjacent countries
     */
    @Test
    public void testAttackingNotAdjacentCountries() {
        boolean valid;
        Country country17 = mapData2.getCountries().get("17");
        Country country35 = mapData2.getCountries().get("35");
        String country17Owner;
        String country18Owner;

        do {
            mapData2.populateCountries();
            mapData2.placeAll();
            country17Owner = country17.getPlayer().getPlayerName();
            country18Owner = country35.getPlayer().getPlayerName();
        } while (country17Owner.equalsIgnoreCase("John") && country18Owner.equalsIgnoreCase("Jide"));

        country17.setNoOfArmies(5);
        country35.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(country17.getName(), country35.getName());
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            country17.setNoOfArmies(3);
            country35.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (country17.getNoOfArmies() == 1 && country35.getNoOfArmies() == 2) {
                valid = false;
            }
            else {
                valid = true;
            }
        }
        else {
            valid = true;
        }
        assertTrue(valid);
    }

    /**
     * test Attack With Correct Conditions using second attack method
     */
    @Test
    public void testAttackWithCorrectConditions2() {
        boolean valid;
        Country country17 = mapData2.getCountries().get("17");
        Country country18 = mapData2.getCountries().get("18");
        String country17Owner;
        String country18Owner;

        do {
            mapData2.populateCountries();
            mapData2.placeAll();
            country17Owner = country17.getPlayer().getPlayerName();
            country18Owner = country18.getPlayer().getPlayerName();
        } while (country17Owner.equalsIgnoreCase("John") && country18Owner.equalsIgnoreCase("Jide"));

        country17.setNoOfArmies(5);
        country18.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(country17.getName(), country18.getName(), 3);
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            country17.setNoOfArmies(3);
            country18.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (country17.getNoOfArmies() == 1 && country18.getNoOfArmies() == 2) {
                valid = true;
            }
            else {
                valid = false;
            }
        }
        else {
            valid = false;
        }
        assertTrue(valid);
    }
}