package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Remove Continent
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * RemoveContinent on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class RemoveContinentCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * The continent to remove
     */
    private String continentName;

    /**
     * RemoveContinentCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param continentName The continent to remove
     */
    public RemoveContinentCommand(MapData mapData, String continentName) {
        this.mapData = mapData;
        this.continentName = continentName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the RemoveContinentCommand
     */
    @Override
    public void execute() {
        mapData.removeContinent(continentName);
    }
}
