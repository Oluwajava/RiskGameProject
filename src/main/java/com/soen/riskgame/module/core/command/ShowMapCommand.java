package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 * * Class uses command pattern to abstract ShowMap Command
 *  * for the Map This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author John
 */
public class ShowMapCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * object to interface method
     */
    private ShowMapListener showMapListener;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData Contains the Map Data
     * @param showMapListener object to interface method
     */
    public ShowMapCommand(MapData mapData, ShowMapListener showMapListener) {
        this.mapData = mapData;
        this.showMapListener = showMapListener;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        showMapListener.showMap(mapData.buildStringData());
    }

    /**
     * ShowMap interface having show map method
     */
    public interface ShowMapListener {

        void showMap(String mapData);

    }
}
