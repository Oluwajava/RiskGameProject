package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class uses command pattern to abstract Remove NeighbourCountry
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * Remove NeighbourCountry to MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Sibil
 */
public class RemoveNeigbourCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * Country name that should be removed to the map data
     */
    private String countryName;
    /**
     * NeighbourCountry name that should be removed to the map data
     */
    private String countryNeigbourName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData Contains the Map Data
     * @param countryName Country name that should be removed to the map data
     * @param countryNeigbourName NeighbourCountry name that should be removed to the map data
     */
    public RemoveNeigbourCommand(MapData mapData, String countryName, String countryNeigbourName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.countryNeigbourName = countryNeigbourName;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        mapData.removeNeighbour(countryName, countryNeigbourName);
    }
}
