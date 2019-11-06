package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.MapValidator;

import java.io.IOException;

/**
 *  * * Class uses command pattern to abstract Editmap Command
 *  *  * for the Map This class can be called by either the GUI or the command line to perform
 *  *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Mayokun
 */
public class LoadMapCommand implements Command {
    /**
     * Name to the map file
     */
    private String fileName;
    /**
     * object of the interface implemented
     */
    private LoadMapListener loadMapListener;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param fileName Name to the map file
     * @param loadMapListener object of the interface implemented
     */
    public LoadMapCommand(String fileName, LoadMapListener loadMapListener) {
        this.fileName = fileName;
        this.loadMapListener = loadMapListener;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        loadMapListener.loadMap(data);
    }

    /**
     * Interface representation for the class
     */
    public interface LoadMapListener {

        void loadMap(MapData ma);

    }
}
