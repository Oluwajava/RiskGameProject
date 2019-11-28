package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * * Class uses command pattern to abstract Editmap Command
 *  * for the Map This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Sibil
 */
public class EditMapCommand implements Command {
    /**
     * object of the interface implemented
     */
    private EditMapListener editMapListener;
    /**
     * Name to the map file
     */
    private String fileName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param editMapListener
     * @param fileName
     */
    public EditMapCommand(EditMapListener editMapListener, String fileName) {
        this.editMapListener = editMapListener;
        this.fileName = fileName;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        editMapListener.editMap(data);
    }

    /**
     * Interface representation for the class
     */
    public interface EditMapListener {

        void editMap(MapData ma);

    }
}
