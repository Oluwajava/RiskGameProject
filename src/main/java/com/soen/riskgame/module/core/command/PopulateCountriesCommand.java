package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Populate Countries
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * PopulateCountries on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class PopulateCountriesCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * PopulateCountriesCommand's Constructor
     *
     * @param mapData Contains the Map Data
     */
    public PopulateCountriesCommand(MapData mapData) {
        this.mapData = mapData;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the PopulateCountriesCommand
     */
    @Override
    public void execute() {
        mapData.populateCountries();
    }
}
