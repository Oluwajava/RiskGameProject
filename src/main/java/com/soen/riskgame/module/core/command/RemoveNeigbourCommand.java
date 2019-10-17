package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class RemoveNeighbourCommand implements the interface Command
 * provides the functionality of the command remove neighbor
 *
 */
public class RemoveNeigbourCommand implements Command {

	/**
	 * object of the class mapData
	 * contains details about the map
	 */
    private MapData mapData;

    /**
     * name of the country
     */
    private String countryName;

    /**
     * name of the country's neighbor
     */
    private String countryNeigbourName;

    /**
     * Method RemoveNeighbourCommand is to to remove a particular neighbor country
     * @param mapData data in the map
     * @param countryName name of the country
     * @param countryNeigbourName name of the country's neigbor
     */
    public RemoveNeigbourCommand(MapData mapData, String countryName, String countryNeigbourName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.countryNeigbourName = countryNeigbourName;
    }

    /**
     * Method execute()
     * implementation of the method execute to execute the commands.
     * Calls the method of the class MapData to remove a neighbor
     */
    @Override
    public void execute() {
        mapData.removeNeighbour(countryName, countryNeigbourName);
    }
}
