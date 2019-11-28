package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.utils.RoundRobin;

import java.util.*;

/**
 * This class defines the strategy of a cheater
 * @author Adekunle
 */
public class Cheater implements PlayerStrategy {

    /**
     * Cheater's default Constructor
     */
    public Cheater() {}

    /**
     * Cheater's reinforce method
     * doubles the number of armies on all its countries
     * @param mapData hold the map's data
     */
    @Override
    public void reinforce(MapData mapData) {
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        List<Country> countries = currentPlayer.getCountries();
        for (Country country : countries) {
            country.setNoOfArmies(country.getNoOfArmies()*2);
        }
    }

    /**
     * Cheater's attack method
     * automatically conquers all the neighbors of all its countries
     * @param mapData hold the map's data
     */
    @Override
    public void attack(MapData mapData) {
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        List<Country> countries = currentPlayer.getCountries();
        for (Country country : countries) {
            List<Country> neighbours = country.getAdjacentCountries();
            for (Country neighbour : neighbours) {
                if (neighbour.getPlayer() != currentPlayer) { //if enemy neighbour
                    neighbour.setNoOfArmies(0);
                }
            }
        }
    }

    /**
     * Cheater's fortify method
     * doubles the number of armies on its countries that have neighbors that belong to
     * other players
     * @param mapData hold the map's data
     */
    @Override
    public void fortify(MapData mapData) {
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        List<Country> countries = currentPlayer.getCountries();
        boolean toFortify;
        for (Country country : countries) {
            List<Country> neighbours = country.getAdjacentCountries();
            toFortify = false;
            for (Country neighbour : neighbours) {
                if (neighbour.getPlayer() != currentPlayer) { //has enemy neighbour
                    toFortify = true;
                }
            }
            if(toFortify) {
                country.setNoOfArmies(country.getNoOfArmies()*2);
            }
        }

    }
}
