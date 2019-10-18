package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.*;

/**
 * Class uses command pattern to abstract Edit Map
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * EditMap on MapData
 *  *   <pre>
 *  *   EditMapCommand command
 *  *     = new EditMapCommand(new EditMapListener(), new String("map_file"));
 *  *   </pre>
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class EditMapCommand implements Command {

    /**
     * Holds an object of the EditMapListener class
     */
    private EditMapListener editMapListener;

    /**
     * Holds the name of the file that contains the map data
     */
    private String fileName;

    /**
     * EditMapCommand's Constructor
     *
     * @param editMapListener Holds an object of the EditMapListener class
     * @param fileName Holds the name of the file that contains the map data
     *
     */
    public EditMapCommand(EditMapListener editMapListener, String fileName) {
        this.editMapListener = editMapListener;
        this.fileName = fileName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the EditMapCommand
     */
    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        editMapListener.editMap(data);
    }

    /**
     * This interface declares a method to be implemented
     * in its subclass
     */
    public interface EditMapListener {

        /**
         * Method to be implemented in EditMapListener's subclass
         *
         * @param mapData is an instance of the Mapdata class
         */
        void editMap(MapData mapData);

    }
}
