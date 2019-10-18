package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Fortify
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * Fortify on MapData
 *  *   <pre>
 *  *   FortifyCommand command
 *  *     = new AddCountryCommand(new MapData(), new String("france"), new String("belgium"), 5);
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class FortifyCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Country where armies are taken in fortify
     */
    private String fromCountry;

    /**
     * Country to fortify
     */
    private String toCountry;

    /**
     * number of armies used in fortify
     */
    private int num;

    /**
     * FotifyCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param fromCountry Country where armies are taken in fortify
     * @param toCountry Country to fortify
     * @param num number of armies used in fortify
     *
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
     * the FortifyCommand
     */
    @Override
    public void execute() {
        mapData.fortifyCountry(fromCountry, toCountry, num);
    }
}
