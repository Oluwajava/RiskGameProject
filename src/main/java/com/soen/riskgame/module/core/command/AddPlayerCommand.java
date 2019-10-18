package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;

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
 */
public class AddPlayerCommand implements Command {

    /**
     * Contains the Map Data
     */
    private PlayerCommandListener listener;

    /**
     * Country name that should be added to the map data
     */
    private String playerName;


    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     *
     * @param listener Contains all information about the Map.
     * @param playerName The name of the country that should be added
     *
     */
    public AddPlayerCommand(PlayerCommandListener listener, String playerName) {
        this.listener = listener;
        this.playerName = playerName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the AddPlayerCommand
     */
    @Override
    public void execute() {
        listener.addPlayer(playerName);
    }
}
