package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.utils.RoundRobin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class defines the strategy of a cheater
 *
 * @author Adekunle
 */
public class CheaterStrategy implements PlayerStrategy {

    /**
     * Cheater's default Constructor
     */
    public CheaterStrategy() {
    }

    /**
     * Cheater's reinforce method
     * doubles the number of armies on all its countries
     *
     * @param mapData hold the map's data
     */
    @Override
    public void reinforce(MapData mapData) {
        StringBuilder b = new StringBuilder();
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        System.out.println("Reinforce by " + currentPlayer.getPlayerName());
        List<Country> countries = currentPlayer.getCountries();
        for (Country country : countries) {
            country.setNoOfArmies(country.getNoOfArmies() * 2);
            b.append(currentPlayer.getPlayerName() + " reinforce by setting " + country.getName() + " to " + country.getNoOfArmies() + "\n");
        }

        currentPlayer.setPhase(Phase.ATTACK);
        mapData.addLog("Reinforce", b.toString());
        mapData.updateView();
    }

    /**
     * Cheater's attack method
     * automatically conquers all the neighbors of all its countries
     *
     * @param mapData hold the map's data
     */
    @Override
    public void attack(MapData mapData) {
        StringBuilder b = new StringBuilder();
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        System.out.println("Attack by " + currentPlayer.getPlayerName());
        System.out.println("Current Player County Size: " + currentPlayer.getCountries().size());
        List<Country> countries = currentPlayer.getCountries();
        List<Country> newCountries = new ArrayList<>();
        for (Country country : countries) {
            List<Country> neighbours = country.getAdjacentCountries();
            for (Country neighbour : neighbours) {
                if (neighbour.getPlayer() != currentPlayer) { //if enemy neighbour
                    neighbour.setNoOfArmies(country.getNoOfArmies());
                    Country c = mapData.getCountries().get(String.valueOf(neighbour.getId()));
                    c.setPlayer(currentPlayer);
                    newCountries.add(c);
                    b.append(currentPlayer.getPlayerName() + " attack by setting " + neighbour.getName() + " to 0" + "\n");
                }
            }
        }
        Player firstPlayer = players.last();
        Player temp = firstPlayer;
        do {
            temp.getCountries().removeAll(newCountries);
            players.rotate();
            temp = players.last();
        } while (temp != firstPlayer);
        countries.addAll(newCountries);
        System.out.println("Current Player County Size: " + currentPlayer.getCountries().size());
        currentPlayer.setPhase(Phase.FORTIFICATION);
        mapData.addLog("Attack", b.toString());
        mapData.setGameIsOver();
        mapData.updateView();
    }

    @Override
    public void attackMove(MapData mapData) {

    }

    /**
     * Cheater's fortify method
     * doubles the number of armies on its countries that have neighbors that belong to
     * other players
     *
     * @param mapData hold the map's data
     */
    @Override
    public void fortify(MapData mapData) {
        StringBuilder b = new StringBuilder();
        RoundRobin<Player> players = mapData.getPlayers();
        Player currentPlayer = players.last();
        System.out.println("Fortify by " + currentPlayer.getPlayerName());
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
            if (toFortify) {
                country.setNoOfArmies(country.getNoOfArmies() * 2);
                b.append(currentPlayer.getPlayerName() + " fortify by setting " + country.getName() + " to " + country.getNoOfArmies() + "\n");
            }
        }
        mapData.fortifyNone();
        mapData.updateView();
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }
}
