package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class uses command pattern to abstract Reinforce command
 *  * to Game play. This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Mayokun
 */
public class ReinforceCountryCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * Country name that should be reinforced
     */
    private String countryName;
    /**
     * number of armies to be added
     */
    private int num;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData Contains the Map Data
     * @param countryName Country name that should be reinforced
     * @param num number of armies to be added
     */
    public ReinforceCountryCommand(MapData mapData, String countryName, int num) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.num = num;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        mapData.reinforceCountry(countryName, num);
    }

}
