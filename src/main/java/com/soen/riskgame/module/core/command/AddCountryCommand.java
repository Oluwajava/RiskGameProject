package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Add Country
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * AddCountry to MapData
 *  *   <pre>
 *  *   AddCountryCommand command
 *  *     = new AddCountryCommand(new MapData(), new String("nigeria"), new String("africa"));
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */

public class AddCountryCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Country name that should be added to the map data
     */
    private String countryName;

    /**
     * Continent Name that the country that should be added belongs to
     */
    private String continentName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param countryName The name of the country that should be added
     * @param continentName The name of the continent that should be added
     *
     */
    public AddCountryCommand(MapData mapData, String countryName, String continentName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.continentName = continentName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the AddCountryCommand
     */
    @Override
    public void execute() {

    }
}
