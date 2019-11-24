package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * PlayerBehaviour class contains methods for the Player Behavior.
 * There are five player behaviours 
 * i.e. Human, aggressive, benevolent, random and cheater
 * 
 * @author Karandeep Singh
 * @author Palash Jain
 * 
 */
public abstract class PlayerBehaviour implements Serializable {

    /**
     * 
	 * Method for reinforcement phase. 
	 * Start and end of the reinforcement phase. 
	 * 
	 * @param countryList
	 *            List of countries owned by the player.
	 * @param country
	 *            Country to which reinforcement armies are to be assigned.
	 * @param currentPlayer
	 *            Current player.
	 *            
	 */
    abstract public void reinforcementPhase(List<Country> countryList, Country country, Player currentPlayer);

    /**
     * 
	 * Method for attack phase.
	 * 
	 * @param attackingCountryList
	 *            List of countries attacking.
	 * @param defendingCountryList
	 *            List of countries defending.
	 * @param currentPlayer
	 *            Current player.
	 *            
	 */ 
    abstract public void attackPhase(List<Country> attackingCountryList, List<Country> defendingCountryList,
                                     Player currentPlayer);

    /**
     * 
	 * Method for fortification phase. 
	 * Start and end of the fortification phase. 
	 * 
	 * @param selectedCountry
	 *            List of countries selected by the player.
	 * @param adjCountry
	 *            List of adjacent countries.
	 * @param playerPlaying
	 *            Current player.
	 * 
	 * @return true 
     * 			  If the fortification successful; other wise false.
     * 
	 */ 
    abstract public boolean fortificationPhase(List<Country> selectedCountry, List<Country> adjCountry,
                                               Player playerPlaying);


    /**
     * 
	 * Method to get list of defending countries. 
	 * 
	 * @param attackingCountry
	 *            Attacking country.
	 *
	 * @return List 
     * 			  List of defending countries.
     * 
	 */
    public List<Country> getDefendingCountryList(Country attackingCountry) {
        List<Country> defendingCountries = attackingCountry.getAdjacentCountries().stream()
                .filter(t -> (attackingCountry.getPlayer() != t.getPlayer())).collect(Collectors.toList());

        return defendingCountries;

    }

    /**
     * 
	 * Method for if player can attack.
	 * 
	 * @param countries
	 *            List of countries owned by the player.
	 *   
	 * @return true 
     * 			  If player can attack; other wise false.
     *            
	 */    
    abstract public boolean playerCanAttack(List<Country> countries);

    /**
     * 
	 * Method for to check if fortification phase is valid.
	 * 
	 * @param mapData
	 *            MapData Object.
	 * @param playerPlaying           
	 *   		  Player playing.
	 *   
	 * @return true 
     * 			  If fortification phase is valid; other wise false.
     *            
	 */ 
    public boolean isFortificationPhaseValid(MapData mapData, Player playerPlaying) {
       return false;
    }


}
