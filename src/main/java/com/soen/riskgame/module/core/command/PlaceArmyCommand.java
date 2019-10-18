package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Place Army
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * PlaceArmy on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class PlaceArmyCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * The country on which army is placed
     */
    private String countryName;

    /**
     * PlaceArmyCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param countryName The country on which army is placed
     */
    public PlaceArmyCommand(MapData mapData, String countryName) {
        this.mapData = mapData;
        this.countryName = countryName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the PlaceArmyCommand
     */
    @Override
    public void execute() {
        mapData.placeArmy(countryName);
    }
}
