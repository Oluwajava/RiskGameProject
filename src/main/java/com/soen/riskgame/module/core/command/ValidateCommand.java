package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapValidator;

/**
 * Class uses command pattern to abstract Validate
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * Validate on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class ValidateCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Holds an object of the validateMapListener class
     */
    private ValidateMapListener validateMapListener;

    /**
     * validateMapCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param validateMapListener Holds an object of the validateMapListener class
     *
     */
    public ValidateCommand(MapData mapData, ValidateMapListener validateMapListener) {
        this.mapData = mapData;
        this.validateMapListener = validateMapListener;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the validateMapCommand
     */
    @Override
    public void execute() {
        validateMapListener.onMapValidated(MapValidator.isGraphConnected(mapData));
    }

    /**
     * This interface declares a method to be implemented
     * in its subclass
     */
    public interface ValidateMapListener {

        /**
         * Method to be implemented in ValidateMapListener's subclass
         *
         * @param isMapValid contain whether or not map is valid
         */
        void onMapValidated(Boolean isMapValid);

    }
}
