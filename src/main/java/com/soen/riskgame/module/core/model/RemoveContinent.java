package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RemoveContinent model is used to store the name of the Continent that
 * will be removed from the MapData and will be passed into the
 * RemoveContinentCommand
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveContinent {

    private String continentName;

}
