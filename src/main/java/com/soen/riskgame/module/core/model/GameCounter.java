package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Class to implement the output of GameCounter
 * @author Sai Sukruth
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCounter {
    /**
     * object for map file
     */
    private String mapFile;
    /**
     * object for number of games
     */
    private int numberOfGames = 0;

    /**
     * method increment games
     */
    public void increment() {
        numberOfGames++;
    }
}
