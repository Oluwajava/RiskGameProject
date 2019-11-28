package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/**
 * Class player to implement the game for the each player
 * @author Hitansh,Mayokun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	/**
     * name of the player
     */
    private String playerName;
    /**
     * list of countries under player
     */
    private List<Country> countries;
    /**
     * color assigned to each player
     */
    private PlayerColor playerColor;
    /**
     * number of armies each player holding
     */
    private int numOfArmies;
    /**
     * * Constructor for the  class Initializes  object
     * @param playerName name of the player
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.countries = new ArrayList<>();
    }
    /**
     * method to add country to the player
     * @param country list of countries
     */
    public void addCountry(Country country) {
        countries.add(country);
        numOfArmies = (int) Math.floor(countries.size()/3);
    }
    /**
     * reduce the no of armies to the player
     */
    public void decreaseNumOfArmies() {
        numOfArmies--;
    }

    public void decreaseNumOfArmies(int num) {
        numOfArmies -=  num;
    }

    public void resetNumOfArmies() {
        numOfArmies = 0;
    }

    public boolean doesCountryBelongToPlayer(String countryName) {
        for (Country country:
             countries) {
            if (country.getName().equalsIgnoreCase(countryName)) return true;
        }
        return false;
    }

    @Data
    public static class PlayerColor {

        private int red;

        private int blue;

        private int green;
    }

}
