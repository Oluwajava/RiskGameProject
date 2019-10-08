package com.soen.riskgame.module.core.model;

import lombok.Data;

@Data
public class CountryDTO {

    private String name;
    private Long id;
    private String XCoordinate;
    private String YCoordinate;
    private ContinentDTO continentDTO;
    private String continentId;

    public CountryDTO() {

    }

    public CountryDTO(Long id, String name, String continentId, ContinentDTO, String XCoordinate, String YCoordinate) {
        super();
        this.name = name;
        this.id = id;

    }


}