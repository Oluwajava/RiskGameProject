package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.GameTypeListener;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;
import lombok.Data;

import java.util.List;
/**
 * Class uses command pattern to TournamentCommand
 *  for the Map This class can be called by either the GUI or the command line to perform
 *  @see com.soen.riskgame.module.core.interfaces.Command
 *  @see <a href="https://refactoring.guru/design-patterns/command"> Command Pattern Tutorial</a>
 * @author Mayokun
 */
@Data
public class TournamentCommand implements Command {

    /**
     * object the PlayerCommandListener
     */
    private PlayerCommandListener listener;
    /**
     * object of GameTypeListener
     */
    private GameTypeListener gameTypeListener;
    /**
     * object of map data
     */
    private MapData mapData;
    /**
     * object of map list files
     */
    private List<String> listOfMapFiles;
    /**
     * lisy of strategy
     */
    private List<String> listOfPlayersStrategies;
    /**
     * number Of Games
     */
    private int numberOfGames;
    /**
     * max Number Of Games
     */
    private int maxNumberOfGames;

    /**
     * Constructor of TournamentCommand
     */
    public TournamentCommand() {
    }

    /**
     * Constructor of TournamentCommand
     * @param listener Listerner object
     * @param gameTypeListener gametype object
     * @param mapData mapdata object
     * @param listOfMapFiles  list of maps
     * @param listOfPlayersStrategies list of player strategies
     * @param numberOfGames number of games
     * @param maxNumberOfGames max number of games
     */
    public TournamentCommand(PlayerCommandListener listener, GameTypeListener gameTypeListener, MapData mapData, List<String> listOfMapFiles, List<String> listOfPlayersStrategies, int numberOfGames, int maxNumberOfGames) {
        this.mapData = mapData;
        this.listOfMapFiles = listOfMapFiles;
        this.listOfPlayersStrategies = listOfPlayersStrategies;
        this.numberOfGames = numberOfGames;
        this.maxNumberOfGames = maxNumberOfGames;
        this.listener = listener;
        this.gameTypeListener = gameTypeListener;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        gameTypeListener.setIsTournament();
        gameTypeListener.setListOfPlayerStrategies(listOfPlayersStrategies);
        gameTypeListener.setNumberOfGames(numberOfGames);
        gameTypeListener.setMaxNumberOfGames(maxNumberOfGames);
        gameTypeListener.setListOfMapFiles(listOfMapFiles);
        for (int i = 1; i <= listOfPlayersStrategies.size(); i++) {
            listener.addPlayer(listOfPlayersStrategies.get(i - 1).toUpperCase() + " Player " + i, listOfPlayersStrategies.get(i - 1));
        }
        mapData.setTournament(listOfMapFiles, listOfPlayersStrategies, numberOfGames, maxNumberOfGames);
    }
}
