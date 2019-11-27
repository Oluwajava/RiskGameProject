package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Add Country
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * AddCountry to MapData
 *  *   <pre>
 *  *   AddCountryCommand command
 *  *     = new AddCountryCommand(new MapData(), new String("nigeria"), new String("africa"));
 *  *   </pre>
 * @see Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Mayokun
 */

public class RemovePlayerCommand implements Command {

    /**
     * Contains the Map Data
     */
    private PlayerCommandListener mapData;

    /**
     * Country name that should be added to the map data
     */
    private String playerName;


    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param mapData Contains all information about the Map.
     * @param playerName The name of the country that should be added
     *
     */
    public RemovePlayerCommand(PlayerCommandListener mapData, String playerName) {
        this.mapData = mapData;
        this.playerName = playerName;
    }


    @Override
    public void execute() {
        mapData.removePlayer(playerName);
    }
}
