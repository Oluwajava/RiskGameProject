package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    private Long id;

    private String name;

    private Player player;

    private String continentId;

    private Continent continent;

    private String xCoordinate;

    private String yCoordinate;

    private int noOfArmies;

    private List<Country> adjacentCountries;

    public Country(String name) {
        this.name = name;
        adjacentCountries = new ArrayList<>();
    }

    public Country(Long id, String name, String xCoordinate, String yCoordinate) {
        this.id = id;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }



}
