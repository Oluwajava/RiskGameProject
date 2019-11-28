package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class uses command pattern to abstract Remove Continent
 * from Map Data. This class can be called by either the GUI or the command line to perform
 * RemoveContinent from MapData
 *
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">HERO-402</a>
 * @author John
 */
public class RemoveContinentCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * name to of the continent to be removed
     */
    private String continentName;

    /**
     * Constructor for the class Initializes mapIO object.
     * @param mapData Contains the Map Data
     * @param continentName name to of the continent to be removed
     */
    public RemoveContinentCommand(MapData mapData, String continentName) {
        this.mapData = mapData;
        this.continentName = continentName;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        mapData.removeContinent(continentName);
    }
}
