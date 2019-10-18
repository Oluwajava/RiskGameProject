package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Add Continent
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * AddContinent to MapData
 *   <pre>
 *   AddContinentCommand command
 *     = new AddContinentCommand(new MapData(), new String("africa"), 5);
 *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @since       RiskGameProject1.0
 */
public class AddContinentCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Continent name that should be added to the map data
     */
    private String continentName;

    /**
     * Control value of the continent
     */
    private int controlValue;

    /**
     *Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param continentName The continent to be added to the map data
     * @param controlValue The control value of the continent to be added to the map data
     */
    public AddContinentCommand(MapData mapData, String continentName, int controlValue) {
        this.mapData = mapData;
        this.continentName = continentName;
        this.controlValue = controlValue;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the AddContinentCommand
     */
    @Override
    public void execute() {
        mapData.addContinent(continentName, controlValue);
    }
}