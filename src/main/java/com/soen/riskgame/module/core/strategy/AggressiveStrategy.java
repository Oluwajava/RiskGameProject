package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Aggressive class contains methods for the Player Behavior
 * Strategies of computer player.
 * <p>
 * An aggressive computer player strategy that focuses on attack
 * (reinforces its strongest country, then always attack with it
 * until it cannot attack anymore, then fortifies in order to
 * maximize aggregation of forces in one country).
 *
 * @author Sai Sukruth
 * @author Hitansh
 * @author Sibil
 */
public class AggressiveStrategy implements PlayerStrategy {
    /**
     * object of the Country
     */
    private Country country;
    /**
     * object of the player
     */
    private Player currentPlayer;
    /**
     * object of string to store strongest country
     */
    String strongestCountry;

    /**
     * Aggresive Player Constructor
     */
    public AggressiveStrategy() {
    }

    /**
     * Method for Aggressive class for reinforcement phase.
     * Start and end of the reinforcement phase.
     * @param mapData Contains map (countries) details
     */
    @Override
    public void reinforce(MapData mapData) {

        currentPlayer = mapData.getPlayers().last();
        List<Country> sortedList = sortCountryListByArmyCount(currentPlayer.getCountries());
        if (!sortedList.isEmpty()) {
            country = findStrongestCountryForReinforcement(sortedList);
            strongestCountry=country.getName();
            if (country != null) {
                country.setNoOfArmies(country.getNoOfArmies() + currentPlayer.getNumberOfReinforcementArmy());
                mapData.reinforceCountry(strongestCountry,country.getNoOfArmies() + currentPlayer.getNumberOfReinforcementArmy());
            }

        }
    }

    /**
     *Method for Aggressive class for attack.
     * @param mapData
     */
        @Override
        public void attack (MapData mapData) {
            System.out.println("Aggressive Attack");

            currentPlayer = mapData.getPlayers().last();

            List<Country> currentPlayerCountryList = currentPlayer.getCountries();
            Country strongestCountry = findStrongestCountryForReinforcement(currentPlayerCountryList);
            List<Country> defendingCountries = strongestCountry.getAdjacentCountries();

            Iterator<Country> defendingCountriesIterator = defendingCountries.iterator();
            while (defendingCountriesIterator.hasNext()) {
                if (strongestCountry.getNoOfArmies() > 1) {
                    System.out.println("Attacking with strongest country " + strongestCountry.getName());
                    mapData.attack(strongestCountry.getName(), defendingCountriesIterator.next().getName(),3);
                    break;
                }
            }
        }



    /**
     * Method for Aggressive class for fortification phase.
     * Start and end of the fortification phase.
     * @param mapData Contains map (countries) details
     */
        @Override
        public void fortify (MapData mapData){
            System.out.println("Aggressive Fortification");
            Country countryToFortify = null;
            Country countryFromFortify = null;
            int maxSum = 0;
            int sum;
            currentPlayer=mapData.getPlayers().last();
            for (Country country : currentPlayer.getCountries()) {
                sum = country.getNoOfArmies();
                for (Country adjacentCountry : country.getAdjacentCountries()) {
                    if (country.getPlayer().equals(adjacentCountry.getPlayer())) {
                        sum += adjacentCountry.getNoOfArmies() > 1 ? (adjacentCountry.getNoOfArmies() - 1) : 0;
                        if (sum > maxSum) {
                            maxSum = sum;
                            countryToFortify = country;
                            countryFromFortify = adjacentCountry;
                            sum = country.getNoOfArmies();
                        }
                    }
                }
            }

            if (countryToFortify != null && countryFromFortify != null) {
                mapData.fortifyCountry(countryFromFortify.getName(),countryToFortify.getName(),countryToFortify.getNoOfArmies() + countryFromFortify.getNoOfArmies() - 1);
            }

            mapData.fortifyNone();
        }


        /**
         * Method to find the strongest country for reinforcement.
         *
         * @param list List of countries.
         * @return Country country
         * Strongest country.
         */
        public Country findStrongestCountryForReinforcement (List < Country > list) {
            if (!list.isEmpty()) {
                for (Country country : list) {
                    if (country != null && country.getNoOfArmies() >= 1 && getDefendingCountryList(country).size() > 0) {
                        return country;
                    }
                }
            }
            return null;
        }

        /**
         * Method to sort country list by army count.
         * List of countries which have been sorted.
         * @param list List of countries.
         * @return List sorted countries list
         */
        public List<Country> sortCountryListByArmyCount (List < Country > list) {
            Collections.sort(list, Comparator.comparing(obj -> Integer.valueOf(obj.getNoOfArmies()), Comparator.reverseOrder()));
            return list;
        }

    /**
     * Method to get defending country
     * @param attackingCountry
     * @return defending country
     */
    public List<Country> getDefendingCountryList(Country attackingCountry) {
        List<Country> defendingCountries = attackingCountry.getAdjacentCountries().stream()
                .filter(t -> (attackingCountry.getPlayer() != t.getPlayer())).collect(Collectors.toList());

        return defendingCountries;

    }





}
