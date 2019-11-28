package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class uses command pattern to abstract Add NeighbourCountry
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * Add NeighbourCountry to MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Hitansh
 */
public class AddNeighbourCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * Country name that should be added to the map data
     */
    private String countryName;
    /**
     * NeighbourCountry name that should be added to the map data
     */
    private String neighbourCountryName;


    /**
     * Constructor for the MapEditor class Initializes mapIO object
     * @param mapData Contains all information about the Map.
     * @param countryName The name of the country that should be added
     * @param neighbourCountryName The name of the neighbour country that should be added
     */
    public AddNeighbourCommand(MapData mapData, String countryName, String neighbourCountryName) {
        super();
        this.mapData = mapData;
        this.countryName = countryName;
        this.neighbourCountryName = neighbourCountryName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the AddNeighbourCountry Command
     */
    @Override
    public void execute() {
        mapData.addNeighbour(countryName, neighbourCountryName);
    }

}
