package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class ShowMapCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    private ShowMapListener showMapListener;

    public ShowMapCommand(MapData mapData, ShowMapListener showMapListener) {
        this.mapData = mapData;
        this.showMapListener = showMapListener;
    }

    @Override
    public void execute() {
        if (mapData.getGameStarted()) {
            showMapListener.showMap(mapData.buildStringGamePlayData());
        } else {
            showMapListener.showMap(mapData.buildStringData());
        }
    }

    public interface ShowMapListener {

        void showMap(String mapData);

    }
}
