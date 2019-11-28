package com.soen.riskgame.module.core.interfaces;

import java.util.List;

/**
 * Interface PlayerCommandListener to provide functionality of various Player commands
 * @author Hitansh
 */
public interface GameTypeListener {

    void setListOfMapFiles(List<String> listOfMapFiles);

    void setListOfPlayerStrategies(List<String> listOfMapFiles);

    void setNumberOfGames(int num);

    void setMaxNumberOfGames(int num);
    /**
     * method to add player
     */
    void setIsTournament();


}