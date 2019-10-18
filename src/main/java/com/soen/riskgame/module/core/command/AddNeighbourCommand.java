package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Add neighbour country
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * AddNeighbour to MapData
 *  *   <pre>
 *  *   AddNeighbourCommand command
 *  *     = new AddNeighbourCommand(new MapData(), new String("france"), new String("belgium"));
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class AddNeighbourCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * The country whose neighbour is to be added to the map data
     */
    private String countryName;

    /**
     * The neighbour country to be added to the map data
     */
    private String neighbourCountryName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param countryName The country whose neighbour is to be added to the map data
     * @param neighbourCountryName The neighbour country to be added to the map data
     *
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
     * the AddNeighbourCommand
     */
    @Override
    public void execute() {
        mapData.addNeighbour(countryName, neighbourCountryName);
    }

}
