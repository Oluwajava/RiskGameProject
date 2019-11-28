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
    /**
     * reduce the no of armies to the player
     * @param num number of armies
     */
    public void decreaseNumOfArmies(int num) {
        numOfArmies -=  num;
    }
    /**
     * method to reset number of armies
     */
    public void resetNumOfArmies() {
        numOfArmies = 0;
    }
    /**
     *   method to validate the country , belongs to player or not
     *   @param countryName name of the country
      * @return <b>true or false </b>
     */
    public boolean doesCountryBelongToPlayer(String countryName) {
        for (Country country:
             countries) {
            if (country.getName().equalsIgnoreCase(countryName)) return true;
        }
        return false;
    }
    /**
     * color for each player
     */
    @Data
    public static class PlayerColor {

        private int red;

        private int blue;

        private int green;
    }

}
