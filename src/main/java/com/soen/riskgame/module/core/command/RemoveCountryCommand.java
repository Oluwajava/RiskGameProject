package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.interfaces.Command;

/**
 * Class uses command pattern to abstract Remove Country
 * from Map Data. This class can be called by either the GUI or the command line to perform
 * RemoveCountry from MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">HERO-402</a>
 */
public class RemoveCountryCommand implements Command {

    /**
     * holds map data
     */
    private MapData mapData;

    /**
     * stores country name
     */
    private String countryName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param countryName The name of the country that should be removed
     *
     */
    public RemoveCountryCommand(MapData mapData, String countryName) {
        this.mapData = mapData;
        this.countryName = countryName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the RemoveCountryCommand
     */
    @Override
    public void execute() {}
}
