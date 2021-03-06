package com.soen.riskgame.module.core.interfaces;

/**
 * Interface PlayerCommandListener to provide functionality of various Player commands
 * @author Hitansh
 */
public interface PlayerCommandListener {

    /**
     * method to add player
     * @param name name of the player to be added
     */
    void addPlayer(String name, String strategy);

    /**
     * method to remove a player
     * @param name name of the player to be removed
     */
    void removePlayer(String name);

}