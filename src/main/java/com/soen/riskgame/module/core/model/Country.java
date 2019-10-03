package com.soen.riskgame.module.core.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private long id;
    private Continent continent;
    private Player player;
    private String XCoordinate;
    private String YCoordinate;
    private int noOfArmies;
    private ArrayList<Country> adjacentCountries= new ArrayList<Country>();
    private String continentId;
    public Country(){

    }
    public Country( String name)
    {

    }
    public Country(String name, long id, String XCoordinate, String YC0ordinate)
    {

    }

    @Override
    public String toString() {
        return "Country [name=" + name + ", id=" + id + ", color=" + color + ", controlValue=" + controlValue + "]";
        //return super.toString();
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }
}
