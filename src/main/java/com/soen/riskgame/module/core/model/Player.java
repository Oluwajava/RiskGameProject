package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.enums.Phase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int exchangeCount = 0;

    private String playerName;

    private List<Country> countries;

    private PlayerColor playerColor;

    private int placeArmiesNo;

    private int numOfArmies;

    private Card cards;

    private boolean countriesPopulated;

    private Phase phase;

    public Player(String playerName) {
        this.playerName = playerName;
        this.countries = new ArrayList<>();
        this.cards = new Card();
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public void resetReinforcementCalculation() {
        this.numOfArmies = getNumberOfReinforcementArmy();
    }

    public void addCountry(Country country) {
        countries.add(country);
        numOfArmies = getNumberOfReinforcementArmy();
    }

    public int getNumberOfReinforcementArmy() {
        return (int) Math.floor(countries.size() / 3);
    }

    public void decreaseNumOfArmies() {
        numOfArmies--;
    }

    public void decreaseNumOfArmies(int num) {
        numOfArmies -= num;
    }

    public void resetNumOfArmies() {
        numOfArmies = 0;
    }

    public void decreasePlaceArmmiesNo() {
        placeArmiesNo--;

    }

    public void decreasePlaceArmiesNo(int num) {
        placeArmiesNo -= num;
    }

    public boolean doesCountryBelongToPlayer(String countryName) {
        for (Country country :
                countries) {
            if (country.getName().equalsIgnoreCase(countryName)) return true;
        }
        return false;
    }

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

    @Data
    public static class PlayerColor {

        private int red;

        private int blue;

        private int green;
    }

}
