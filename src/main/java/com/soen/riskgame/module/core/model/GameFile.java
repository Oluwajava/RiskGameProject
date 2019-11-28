package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Holds information about the game resource files
 * @author John
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameFile {
    /**
     * Stores information about the map preview file
     */
    private String preview;

    /**
     * Stores map file information
     */
    private String map;

    /**
     * Stores the picture names
     */
    private String pic;

    /**
     * Stores the card file information
     */
    private String card;
}