package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Reinforce Country
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * ReinForceCountry on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class ReinforceCountryCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Contains the name of the country to reinforce
     */
    private String countryName;

    /**
     * Contains the number of reinforcement armies
     */
    private int num;

    /**
     * ReinforceCountriesCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param countryName Contains the name of the country to reinforce
     * @param num Contains the number of reinforcement armies
     */
    public ReinforceCountryCommand(MapData mapData, String countryName, int num) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.num = num;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the ReinforceCountryCommand
     */
    @Override
    public void execute() {
        mapData.reinforceCountry(countryName, num);
    }

}
