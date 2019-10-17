package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class ShowMapCommand implements the interface Command
 * provides the functionality of the game command "show map"
 *
 */
public class ShowMapCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * instance of ShowMapListerner
     */
    private ShowMapListener showMapListener;

    /**
     * Parameterized Constructor
     * @param mapData Object of MapData
     * @param showMapListener Show map listener
     */
    public ShowMapCommand(MapData mapData, ShowMapListener showMapListener) {
        this.mapData = mapData;
        this.showMapListener = showMapListener;
    }

    /**
     * Method execute()
     * calls the showMap function of ShowMapListerner 
     */
    @Override
    public void execute() {
        if (mapData.getGameStarted()) {
            showMapListener.showMap(mapData.buildStringGamePlayData());
        } else {
            showMapListener.showMap(mapData.buildStringData());
        }
    }

    /**
     * 
     * Interface ShowMapListener
     *
     */
    public interface ShowMapListener {
    	/**
    	 * Method showMap
    	 * @param mapData data in the map
    	 */
        void showMap(String mapData);

    }
}
