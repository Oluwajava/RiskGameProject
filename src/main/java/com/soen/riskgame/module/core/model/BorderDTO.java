package com.soen.riskgame.module.core.model;

import java.util.List;

public class BorderDTO {
    private Long countryId;
    private List<Long> adjacentCountries;

    public BorderDTO(Long countryId, List<Long> adjacentCountries) {
        this.countryId = countryId;
        this.adjacentCountries = adjacentCountries;
    }

    public BorderDTO() {
        //TODO
    }

    public boolean equals(Object object) {
        //TODO
        return false; //to modify
    }

    public boolean canEqual(Object object) {
        //TODO
        return false; //to modify
    }

    public int hashCode() {
        //TODO
        return 0; //to modify
    }

    public String toString() {
        //TODO
        return null; //to modify
    }
}