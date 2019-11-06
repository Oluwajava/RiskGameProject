package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Fortify command
 *  * to Game play. This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Sai Sukruth
 */
public class FortifyCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * the country from which army has to be moved
     */
    private String fromCountry;
    /**
     * the country to which army has to be moved
     */
    private String toCountry;
    /**
     * number of armies to be moved
     */
    private int num;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData Contains all information about the Map.
     * @param fromCountry The name of the country
     * @param toCountry The name of the country
     * @param num numbers of armies
     */
    public FortifyCommand(MapData mapData, String fromCountry, String toCountry, int num) {
        this.mapData = mapData;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.num = num;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        mapData.fortifyCountry(fromCountry, toCountry, num);
    }
}
