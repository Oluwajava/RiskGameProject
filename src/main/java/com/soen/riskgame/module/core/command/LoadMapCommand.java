package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapDataUtil;

/**
 * Class uses command pattern to abstract load Map
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * LoadMap on MapData
 *  *   <pre>
 *  *   LoadMapCommand command
 *  *     = new LoadMapCommand(new String("map_file"), new LoadMapListener());
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class LoadMapCommand implements Command {

    /**
     * Holds the name of the file that contains the map data
     */
    private String fileName;

    /**
     * Holds an object of the LoadMapListener class
     */
    private LoadMapListener loadMapListener;

    /**
     * LoadMapCommand's Constructor
     *
     * @param loadMapListener Holds an object of the LoadMapListener class
     * @param fileName Holds the name of the file that contains the map data
     *
     */
    public LoadMapCommand(String fileName, LoadMapListener loadMapListener) {
        this.fileName = fileName;
        this.loadMapListener = loadMapListener;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the LoadMapCommand
     */
    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        loadMapListener.loadMap(data);
    }

    /**
     * This interface declares a method to be implemented
     * in its subclass
     */
    public interface LoadMapListener {

        /**
         * Method to be implemented in LoadMapListener's subclass
         *
         * @param mapData is an instance of the Mapdata class
         */
        void loadMap(MapData mapData);
    }
}
