package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinentDTO {
    /**
     * contains Continent id value
     */
    private Long id;
    /**
     * contains Continent name
     */
    private String name;
    /**
     * contains the control value of the each continent
     */
    private Integer controlValue;
    /**
     * contains the color of the each continent
     */
    private String color;

}
