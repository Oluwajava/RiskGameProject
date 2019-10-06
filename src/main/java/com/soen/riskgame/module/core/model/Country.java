package com.soen.riskgame.module.core.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Class Country is the final stage of the data that is parsed  from the map files. 
 * Parsed data from mapper class is passed to DTO classes.
 * which will send the data to Country class
 * Country class will contains all the information from DTO class and also additional details like player info, noOfArmies 
 * list of the adjacent countries 
 *
 * @see com.soen.riskgame.module.core.mapper
 *
 */
@Data 
public class Country {
    
	/**
	 * contains country name
	 */
	private String name;
    /**
     * contains country id value
     */
    private long id;
    /**
     * contains continent name in which the country is present
     */
    private Continent continent;
    
    /**
     * contains details of the player who is holding the country
     */
    //private Player player;
    /**
     * contains country X coordinate data while using the GUI
     */
    
    private String XCoordinate;
    /**
     * contains country Y coordinate data while using the GUI
     */
    private String YCoordinate;
    /**
     * contains noOfArmies that are present in the country
     */
    private int noOfArmies;
    
    /**
     * contains list of adjacent countries
     */
    private ArrayList<Country> adjacentCountries= new ArrayList<Country>();
    /**
     * contains the continent Id data the country is in
     */
    private String continentId;
    /**
     * default constructor of the country class
     */
    public Country(){

    }
    /**
     * parameterized constructor of the Country class with name of the country
     * @param name name refers to the name of the country
     */
   
    public Country( String name)
    {

    }
    /**
     * Parameterized constructor of the Country class 
     * 
     * @param id   id is country id reference 
     * @param name   name refers to the name if the country
     * @param XCoordinate	X coordinate contains country X coordinate data while using the GUI
     * @param YCoordinate	Y coordinate contains country Y coordinate data while using the GUI
     */
    public Country(String name, long id, String XCoordinate, String YC0ordinate)
    {
    	
    }

    
}
