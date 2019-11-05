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

    private String playerName;

    private List<Country> countries;

    private PlayerColor playerColor;

    private int placeArmiesNo;

    private int numOfArmies;

    private boolean countriesPopulated;

    private Phase phase;

    public Player(String playerName) {
        this.playerName = playerName;
        this.countries = new ArrayList<>();
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

    @Data
    public static class PlayerColor {

        private int red;

        private int blue;

        private int green;
    }

}
