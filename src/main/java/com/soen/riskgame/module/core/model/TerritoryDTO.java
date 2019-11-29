package com.soen.riskgame.module.core.model;

import lombok.Data;

import java.util.List;

/**
 * class to parse TerritoryDTO object
 * @author Mayokun
 */
@Data
public class TerritoryDTO {
    /**
     * name of the map
     */
    private String name;
    /**
     * id of the map
     */
    private long id;
    /**
     * XCoordinate of the map
     */
    private String XCoordinate;
    /**
     * YCoordinate of the map
     */
    private String YCoordinate;
    /**
     * Name of the continent
     */
    private String continentName;
    /**
     * List of adjacentTerritories
     */
    private List<String> adjacentTerritories;

}
