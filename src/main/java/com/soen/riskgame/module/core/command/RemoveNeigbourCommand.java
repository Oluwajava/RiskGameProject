package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Remove Neighbour Country
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * RemoveNeighbour to MapData
 *  *   <pre>
 *  *   RemoveNeighbourCommand command
 *  *     = new RemoveNeighbourCommand(new MapData(), new String("france"), new String("belgium"));
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class RemoveNeigbourCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * The country whose neighbour is to be removed from the map data
     */
    private String countryName;

    /**
     * The neighbour country to be removed from the map data
     */
    private String countryNeigbourName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param countryName The country whose neighbour is to be added to the map data
     * @param countryNeigbourName The neighbour country to be added to the map data
     *
     */
    public RemoveNeigbourCommand(MapData mapData, String countryName, String countryNeigbourName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.countryNeigbourName = countryNeigbourName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the RemoveNeighbourCommand
     */
    @Override
    public void execute() {
        mapData.removeNeighbour(countryName, countryNeigbourName);
    }
}
