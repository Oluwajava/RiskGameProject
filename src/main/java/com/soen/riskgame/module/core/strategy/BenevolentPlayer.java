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
public class BenevolentPlayer implements PlayerStrategy {
    /**
     * Object of GamePlayController, control various activities during the game play.
     */
    private GamePlayController gamePlayController;

    private Country country;

    private Player currentPlayer;

    String countryToCheck;

    /**
     * Benevolent Player default Constructor
     */
    public BenevolentPlayer() {
    }


    @Override
    public void reinforce(MapData mapData) {
        List<Country> sortedList = sortCountryListByArmyCount((List<Country>) mapData.getCountries());
        if (!sortedList.isEmpty()) {

            country = sortedList.get(0);
            countryToCheck=sortedList.get(0).getName();
            if(currentPlayer.doesCountryBelongToPlayer(countryToCheck)) {
                country.setNoOfArmies(country.getNoOfArmies() + currentPlayer.getNumOfArmies());
                currentPlayer.setNumOfArmies(0);
            }
        }
    }

    @Override
    public void attack(MapData mapData) {

    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        Country weakestCountry = findWeakestIfNoAdjacentCountryToFortify((List<Country>) mapData.getCountries());
        if (weakestCountry != null) {
            Country strongestAdjacentCountry = getStrongestAdjacentCountry(weakestCountry);
            if (strongestAdjacentCountry != null) {
                int fortificationArmies = (strongestAdjacentCountry.getNoOfArmies() - weakestCountry.getNoOfArmies()) / 2;
                weakestCountry.setNoOfArmies(weakestCountry.getNoOfArmies() + fortificationArmies);
                strongestAdjacentCountry.setNoOfArmies(strongestAdjacentCountry.getNoOfArmies() - fortificationArmies);

            }
        }

    }

    @Override
    public void exchangeCard(MapData mapData) {

    }


    public List<Country> sortCountryListByArmyCount(List<Country> list) {
        Collections.sort(list, Comparator.comparing(obj -> Integer.valueOf(obj.getNoOfArmies())));
        return list;
    }
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
