package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Data transfer object that holds the border data
 * obtained from the map file
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderDTO {

    /**
     * Stores country's Id
     */
    private Long countryId;

    /**
     * Holds a list of adjacent countries
     */
    private List<Long> adjacentCountries;


}