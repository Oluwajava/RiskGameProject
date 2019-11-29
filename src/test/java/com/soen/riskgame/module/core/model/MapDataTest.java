package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.enums.Phase;
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
        mapData2.addContinent("Africa", 5);
        mapData2.addContinent("Europe", 4);
        mapData2.addCountry("Nigeria", "Africa");
        mapData2.addCountry("Ghana", "Africa");
        mapData2.addCountry("UK", "Europe");
        mapData2.addCountry("Sweden", "Europe");
        mapData2.addNeighbour("Nigeria", "Ghana");
        mapData2.addNeighbour("Ghana", "UK");
        mapData2.addNeighbour("UK", "Sweden");
        john = mapData2.addPlayer("John", "human");
        jide = mapData2.addPlayer("Jide", "human");
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
        Country nigeria = mapData2.getCountries().get("1");
        Country ghana = mapData2.getCountries().get("2");
        mapData2.populateCountries();
        mapData2.placeAll();
        nigeria.setPlayer(john);
        ghana.setPlayer(jide);
        nigeria.setNoOfArmies(5);
        ghana.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(nigeria.getName(), ghana.getName(), true);
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            nigeria.setNoOfArmies(3);
            ghana.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (nigeria.getNoOfArmies() == 1 && ghana.getNoOfArmies() == 2) {
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
        Country nigeria = mapData2.getCountries().get("1");
        Country ghana = mapData2.getCountries().get("2");
        mapData2.populateCountries();
        mapData2.placeAll();
        nigeria.setPlayer(john);
        ghana.setPlayer(jide);
        nigeria.setNoOfArmies(5);
        ghana.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.REINFORCEMENT);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(nigeria.getName(), ghana.getName(), true);
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            nigeria.setNoOfArmies(3);
            ghana.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.REINFORCEMENT);
            mapData2.attackMove(2);
            if (nigeria.getNoOfArmies() == 1 && ghana.getNoOfArmies() == 2) {
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
        Country nigeria = mapData2.getCountries().get("1");
        Country uk = mapData2.getCountries().get("3"); //uk is not adjacent with nigeria
        mapData2.populateCountries();
        mapData2.placeAll();
        nigeria.setPlayer(john);
        uk.setPlayer(jide);
        nigeria.setNoOfArmies(5);
        uk.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(nigeria.getName(), uk.getName(), true);
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            nigeria.setNoOfArmies(3);
            uk.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (nigeria.getNoOfArmies() == 1 && uk.getNoOfArmies() == 2) {
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
        Country nigeria = mapData2.getCountries().get("1");
        Country ghana = mapData2.getCountries().get("2");
        mapData2.populateCountries();
        mapData2.placeAll();
        nigeria.setPlayer(john);
        ghana.setPlayer(jide);
        nigeria.setNoOfArmies(5);
        ghana.setNoOfArmies(5);
        RoundRobin<Player> players = mapData2.getPlayers();
        Player currentPlayer = players.last();
        currentPlayer.setPhase(Phase.ATTACK);
        players.setElement(currentPlayer);
        mapData2.setPlayers(players);
        mapData2.attack(nigeria.getName(), ghana.getName(), 3);
        if (mapData2.getAttackFromCountry() != null && mapData2.getAttackToCountry() != null) {
            nigeria.setNoOfArmies(3);
            ghana.setNoOfArmies(0);
            currentPlayer.setPhase(Phase.ATTACK_MOVE);
            mapData2.attackMove(2);
            if (nigeria.getNoOfArmies() == 1 && ghana.getNoOfArmies() == 2) {
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
     * test calculation of reinforcement armies
     */
    @Test
    public void testCalculationOfReinforcementArmies() {
        Country nigeria = mapData2.getCountries().get("1");
        Country ghana = mapData2.getCountries().get("2");
        Country uk = mapData2.getCountries().get("3");
        Country sweden = mapData2.getCountries().get("4");
        nigeria.setPlayer(john);
        john.addCountry(nigeria);
        ghana.setPlayer(john);
        john.addCountry(ghana);
        uk.setPlayer(john);
        john.addCountry(uk);
        sweden.setPlayer(jide);
        jide.addCountry(sweden);
        assertTrue(john.getNumberOfReinforcementArmy() == 6);
    }
}