package com.soen.riskgame.module.core.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Country {
    public String name;
    public long id;
    //Continent continent;
    //Player player;
    String XCoordinate;
    String YCoordinate;
    int noOfArmies;
    ArrayList<Country> adjacentCountries= new ArrayList<Country>();
    String continentId;
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
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
