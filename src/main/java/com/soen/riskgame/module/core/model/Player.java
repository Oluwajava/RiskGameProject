package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	private int exchangeCount=0;

    private String playerName;

    private List<Country> countries;

    private PlayerColor playerColor;

    private int placeArmiesNo;

    private int numOfArmies;
    
    private Card cards;

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

    public boolean doesCountryBelongToPlayer(String countryName) {
        for (Country country :
                countries) {
            if (country.getName().equalsIgnoreCase(countryName)) return true;
        }
        return false;
    }

    public int  exchangeTheCards(int x,int y,int z) {
    	int res=this.getCards().reduceCard(x, y, z);
    	if(res==0)
    		return 0;
    	else
    		this.exchangeCount++;
    		int numberOfArmies=this.getNumOfArmies()+(this.exchangeCount+1)*5;
    		this.setNumOfArmies(numberOfArmies);
    		return 1;
    }
    @Data
    public static class PlayerColor {

        private int red;

        private int blue;

        private int green;
    }

}
