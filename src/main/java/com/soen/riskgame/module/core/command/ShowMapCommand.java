package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 * Class uses command pattern to abstract Show Map
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * ShowMap on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class ShowMapCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Holds an object of the ShowMapListener class
     */
    private ShowMapListener showMapListener;

    /**
     * ShowMapCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param showMapListener Holds an object of the ShowMapListener class
     *
     */
    public ShowMapCommand(MapData mapData, ShowMapListener showMapListener) {
        this.mapData = mapData;
        this.showMapListener = showMapListener;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the ShowMapCommand
     */
    @Override
    public void execute() {
        showMapListener.showMap(mapData.buildStringData());
    }

    /**
     * This interface declares a method to be implemented
     * in its subclass
     */
    public interface ShowMapListener {

        /**
         * Method to be implemented in ShowMapListener's subclass
         *
         * @param mapData is an instance of the Mapdata class
         */
        void showMap(String mapData);

    }
}
