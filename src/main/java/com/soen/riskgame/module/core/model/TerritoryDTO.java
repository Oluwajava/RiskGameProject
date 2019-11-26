package com.soen.riskgame.module.core.model;

import lombok.Data;

import java.util.List;

@Data
public class TerritoryDTO {

    private String name;

    private long id;

    private String XCoordinate;

    private String YCoordinate;

    private String continentName;

    private List<String> adjacentTerritories;

}
