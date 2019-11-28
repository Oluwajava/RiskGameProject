package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.GameTypeListener;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;
import lombok.Data;

import java.util.List;

@Data
public class TournamentCommand implements Command {

    /**
     * Contains the Map Data
     */
    private PlayerCommandListener listener;

    private GameTypeListener gameTypeListener;

    private MapData mapData;

    private List<String> listOfMapFiles;

    private List<String> listOfPlayersStrategies;

    private int numberOfGames;

    private int maxNumberOfGames;

    public TournamentCommand() {
    }

    public TournamentCommand(PlayerCommandListener listener, GameTypeListener gameTypeListener, MapData mapData, List<String> listOfMapFiles, List<String> listOfPlayersStrategies, int numberOfGames, int maxNumberOfGames) {
        this.mapData = mapData;
        this.listOfMapFiles = listOfMapFiles;
        this.listOfPlayersStrategies = listOfPlayersStrategies;
        this.numberOfGames = numberOfGames;
        this.maxNumberOfGames = maxNumberOfGames;
        this.listener = listener;
        this.gameTypeListener = gameTypeListener;
    }

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
