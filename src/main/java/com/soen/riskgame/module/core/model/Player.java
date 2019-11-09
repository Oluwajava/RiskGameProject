package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.enums.Phase;
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
     * cards that are exchanged
     */
    private int exchangeCount=0;
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
     * number of placed armies for each card
     */
    private int placeArmiesNo;
    /**
     * number of armies each player holding
     */
    private int numOfArmies;
    /**
     * type of cards
     */
    private Card cards;
    /**
     * value to chck if all countries are populated are not
     */
    private boolean countriesPopulated;
    /**
     * game phase
     */
    private Phase phase;

    /**
     * * Constructor for the  class Initializes  object
     * @param playerName name of the player
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.countries = new ArrayList<>();
        this.cards = new Card();
    }

    /**
     * setter  method for phase
     * @param phase game phase
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    /**
     * method to reset Reinforcement army
     */
    public void resetReinforcementCalculation() {
        this.numOfArmies = getNumberOfReinforcementArmy();
    }
    /**
     * method to add country to the player
     * @param country list of countries
     */
    public void addCountry(Country country) {
        countries.add(country);
        numOfArmies = getNumberOfReinforcementArmy();
    }
    /**
     * method to get getNumberOfReinforcementArmy
     * @return size of reinforced army
     */
    public int getNumberOfReinforcementArmy() {
        return (int) Math.floor(countries.size() / 3);
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
        numOfArmies -= num;
    }
    /**
     * method to reset number of armies
     */
    public void resetNumOfArmies() {
        numOfArmies = 0;
    }
    /**
     * method to reduce the number of armies
     */
    public void decreasePlaceArmmiesNo() {
        placeArmiesNo--;

    }
    /**
     * method to reduce the number of armies
     */
    public void decreasePlaceArmiesNo(int num) {
        placeArmiesNo -= num;
    }

    /**
     *   method to validate the country , belongs to player or not
     *   @param countryName name of the country
      * @return <b>true or false </b>
     */
    public boolean doesCountryBelongToPlayer(String countryName) {
        for (Country country :
                countries) {
            if (country.getName().equalsIgnoreCase(countryName)) return true;
        }
        return false;
    }
    /**
     * method to exchange the cards
     * @param x type of card
     * @param y type of card
     * @param z type of card
     * @return 0 or 1
     */
    public int exchangeTheCards(int x, int y, int z) {
        int res = this.getCards().reduceCard(x, y, z);
        if (res == 0)
            return 0;
        else
            this.exchangeCount++;
        int numberOfArmies = this.getPlaceArmiesNo() + this.exchangeCount * 5;
        this.numOfArmies += numberOfArmies;
        return 1;
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
