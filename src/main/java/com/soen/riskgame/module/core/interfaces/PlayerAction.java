package com.soen.riskgame.module.core.interfaces;

import com.soen.riskgame.module.core.model.Player;

/**
 * Interface to provide the functionality related to the player actions
 * @author hitan
 *
 */
public interface PlayerAction {

    /**
     * Method to add a player
     * @param playerName name of the player
     * @return the player added
     */
    Player addPlayer(String playerName);

    /**
     * method to remove a player
     * @param playerName name of the player to be removed
     */
    void removePlayer(String playerName);

    /**
     * method to populate the countries
     */
    void populateCountries();
}