package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapValidator;
/**
 * * Class uses command pattern to abstract Validate Command
 *  * for the Map This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Hitansh
 */
public class ValidateCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * object to interface method
     */
    private ValidateMapListener validateMapListener;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData Contains the Map Data
     * @param validateMapListener object to interface method
     */
    public ValidateCommand(MapData mapData, ValidateMapListener validateMapListener) {
        this.mapData = mapData;
        this.validateMapListener = validateMapListener;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        validateMapListener.onMapValidated(MapValidator.isGraphConnected(mapData));
    }

    /**
     * ValidateMapListener interface with onMapValidated method
     */
    public interface ValidateMapListener {

        void onMapValidated(Boolean isMapValid);

    }
}
