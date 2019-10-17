package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * Class LoadMapCommand implements the interface Command and is to support the functionality of loading a particular map
 *
 */
public class LoadMapCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * listener for show map
     */
    private ShowMapListener showMapListener;

    /**
     * Method loadMapCommand provides the functionlity of loading the command
     * @param mapData object of class mapData
     * @param showMapListener object of ShowMapListener
     */
    public LoadMapCommand(MapData mapData, ShowMapListener showMapListener) {
        this.mapData = mapData;
        this.showMapListener = showMapListener;
    }

    /**
     * Method execute is to execute a particular functionality
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
    	 * @param mapData Details about a particular map
    	 */
        void showMap(String mapData);

    }
}
