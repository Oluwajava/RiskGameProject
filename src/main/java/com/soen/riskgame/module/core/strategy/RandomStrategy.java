package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.game_play.GamePlayController;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements PlayerStrategy {
    /**
     * Object of GamePlayController
     * Controls various activities during the gameplay
     */
    private GamePlayController gamePLayController;

    /**
     * object of class Country
     */
    private Country country;


    /**
     * Object of Player class
     */
    private Player player;

    /**
     * Default Constructor
     */
    public RandomStrategy() {

    }

    @Override
    public void reinforce(MapData mapData) {
        System.out.println("Random Reinforcement");
        
    	Random random=new Random();
    	//Getting the current player from the mapData
		Player currentPlayer=mapData.getPlayers().last();
		
		//Getting the number of armies for the current player
		int noOfArmies=currentPlayer.getNumOfArmies();
		int loopCount=random.nextInt(noOfArmies);
		List<Country> countryList=currentPlayer.getCountries();
//		for(int i=0;i<loopCount;i++) {
//			if(currentPlayer.getNumOfArmies()==0) {
//				break;
//			}
		while(currentPlayer.getNumOfArmies()!=0) {
		int j=random.nextInt(countryList.size());
		int reinforcementCount=random.nextInt(currentPlayer.getNumOfArmies());
		mapData.reinforceCountry(countryList.get(j).getName(), reinforcementCount );
		}
		
		//}

    }

    private List<Country> getFromCountry(Player player) {
        List<Country> playerCountries = player.getCountries();
        for (Country country : playerCountries) {
            if (country.getNoOfArmies() == 1) {
                playerCountries.remove(country);
            }

        }
        return playerCountries;
    }

    private List<Country> getToCountry(List<Country> adjacentCountries, Player player) {
        for (Country country : player.getCountries()) {
            adjacentCountries.remove(country);
        }
        return adjacentCountries;

    }

    @Override
    public void attack(MapData mapData) {

        Random random = new Random();
        Player currentPlayer = mapData.getPlayers().last();
        int noOfFromCountry = getFromCountry(currentPlayer).size();
        int loopCount = random.nextInt(noOfFromCountry);


        for (int i = 0; i < loopCount; i++) {
            List<Country> fromCountries = getFromCountry(mapData.getPlayers().last());
            int x = random.nextInt(fromCountries.size());
            List<Country> toCountries = getToCountry(fromCountries.get(x).getAdjacentCountries(), mapData.getPlayers().last());
            if (fromCountries.size() == 0 || toCountries.size() == 0) {
                break;
            }

            int y = random.nextInt(toCountries.size());
            mapData.attack(fromCountries.get(x).getName(), toCountries.get(y).getName());
        }


    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        Player currentPlayer = mapData.getPlayers().last();
        List<Country> eligibleCountries = getFromCountry(currentPlayer);

        Random random = new Random();
        int x = random.nextInt(eligibleCountries.size());
        List<Country> countries = getToCountry(eligibleCountries.get(x).getAdjacentCountries(), mapData.getPlayers().last());
        int y = random.nextInt(countries.size());
        int num = random.nextInt(eligibleCountries.get(x).getNoOfArmies() - 1);

        mapData.fortifyCountry(eligibleCountries.get(x).getName(), countries.get(y).getName(), num);


    }

    @Override
    public void exchangeCard(MapData mapData) {

    }

}
