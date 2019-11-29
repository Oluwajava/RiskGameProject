package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.game_play.GamePlayController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * BenevolentPlayer is player strategy
 * Player only reinforces the weakest country
 * Fortifies that weakest country from the strongest neighbour
 * @author Sai Sukruth
 */
public class BenevolentStrategy implements PlayerStrategy {

    /**
     * object of the Country
     */
    private Country country;
    /**
     * object of the Country for weakest country
     */
    private Country weakestCountry;
    /**
     * object of the Country for strongest Adjacent Country
     */
    private Country strongestAdjacentCountry;
    /**
     * object of the Player
     */
    private Player currentPlayer;
    /**
     * String to store country
     */
    String countryToCheck;

    /**
     * Benevolent Player default Constructor
     */
    public BenevolentStrategy() {
    }

    /**
     * Method for Benevolent class for reinforcement phase.
     * Start and end of the reinforcement phase.
     * @param mapData Contains map (countries) details
     */
    @Override
    public void reinforce(MapData mapData) {
        currentPlayer=mapData.getPlayers().last();
        List<Country> sortedList = sortCountryListByArmyCount(currentPlayer.getCountries() );
        if (!sortedList.isEmpty()) {
            country = sortedList.get(0);
            countryToCheck=sortedList.get(0).getName();
            if(currentPlayer.doesCountryBelongToPlayer(countryToCheck)) {
                mapData.reinforceCountry(countryToCheck,country.getNoOfArmies() + currentPlayer.getNumOfArmies());
                currentPlayer.setNumOfArmies(0);
            }

        }
    }

    /**
     * Method for Benevolent class for attack phase.
     * @param mapData Contains map (countries) details
     */
    @Override
    public void attack(MapData mapData) {
        mapData.attackNone();
    }

    /**
     * Method for Benevolent class for attack move.
     * @param mapData Contains map (countries) details
     */
    @Override
    public void attackMove(MapData mapData) {

    }

    /**
     * Method for Benevolent class for fortification phase.
     * Start and end of the fortification phase.
     * @param mapData Contains map (countries) details
     */
    @Override
    public void fortify(MapData mapData) {
        currentPlayer=mapData.getPlayers().last();
        weakestCountry = findWeakestIfNoAdjacentCountryToFortify(currentPlayer.getCountries());
        if (weakestCountry != null) {
            strongestAdjacentCountry = getStrongestAdjacentCountry(weakestCountry);
            if (strongestAdjacentCountry != null) {
                int fortificationArmies = (strongestAdjacentCountry.getNoOfArmies() - weakestCountry.getNoOfArmies()) / 2;
                weakestCountry.setNoOfArmies(weakestCountry.getNoOfArmies() + fortificationArmies);
                strongestAdjacentCountry.setNoOfArmies(strongestAdjacentCountry.getNoOfArmies() - fortificationArmies);
                mapData.fortifyCountry(weakestCountry.getName(), strongestAdjacentCountry.getName(), fortificationArmies);

            }
        }
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }

    /**
     * Method to sort country list by army count.
     *
     * @param list List of countries.
     * @return List
     */
    public List<Country> sortCountryListByArmyCount(List<Country> list) {
        Collections.sort(list, Comparator.comparing(obj -> Integer.valueOf(obj.getNoOfArmies())));
        return list;
    }
    /**
     * Method to find the strongest adjacent country.
     * @param country Country against which to find the strongest adjacent country.
     * @return Country
     */
    public Country getStrongestAdjacentCountry(Country country) {
        if (country == null) {
            return null;
        }
        List<Country> adjacentCountries = new ArrayList<>();
        for (Country adjacentCountry : country.getAdjacentCountries()) {
            if (country.getPlayer().equals(adjacentCountry.getPlayer())) {
                adjacentCountries.add(adjacentCountry);
            }
        }
        if (adjacentCountries.isEmpty()) {
            return null;
        }
        Collections.sort(adjacentCountries, Comparator.comparing(obj -> Integer.valueOf(obj.getNoOfArmies()), Collections.reverseOrder()));
        if (adjacentCountries.get(0).getNoOfArmies() > 1)
            return adjacentCountries.get(0);
        return null;
    }
    /**
     * Method to check and find the weakest country if
     * no adjacent country to fortify.
     *
     * @param list List of countries.
     * @return Country
     * Strongest country.
     */
    public Country findWeakestIfNoAdjacentCountryToFortify(List<Country> list) {
        if (!list.isEmpty()) {
            for (Country country : list) {
                if (country != null && country.getNoOfArmies() >= 1 && getStrongestAdjacentCountry(country) != null) {
                    return country;
                }
            }
        }
        return null;
    }
}
