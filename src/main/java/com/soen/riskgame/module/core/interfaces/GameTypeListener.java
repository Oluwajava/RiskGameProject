package com.soen.riskgame.module.core.interfaces;

import java.util.List;

/**
 * Interface PlayerCommandListener to provide functionality of various Player commands
 * @author Hitansh
 */
public interface GameTypeListener {
    /**
     * method for list of map files
     * @param listOfMapFiles list of map files
     */
    void setListOfMapFiles(List<String> listOfMapFiles);

    /**
     * method for set List Of Player Strategies
     * @param listOfMapFiles ist Of Player Strategies
     */
    void setListOfPlayerStrategies(List<String> listOfMapFiles);

    /**
     * method for set Number Of Games
     * @param num number of games
     */
    void setNumberOfGames(int num);

    /**
     * method for  setMaxNumberOfGames
     * @param num number of games
     */
    void setMaxNumberOfGames(int num);
    /**
     * method to add player
     */
    void setIsTournament();


}