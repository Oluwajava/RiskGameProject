package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileWriter;

import java.io.FileNotFoundException;

/**
 * Class uses command pattern to abstract Save File
 * to Map Data. This class can be called by either the GUI or the command line to perform
 * SaveFile on MapData
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 */
public class SaveFileCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    /**
     * Holds the name of the file that contains the map data
     */
    private String fileName;

    /**
     * PlaceArmyCommand's Constructor
     *
     * @param mapData Contains the Map Data
     * @param fileName Holds the name of the file that contains the map data
     */
    public SaveFileCommand(MapData mapData, String fileName) {
        this.mapData = mapData;
        this.fileName = fileName;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the SaveFileCommand
     */
    @Override
    public void execute() {
        FileWriter fileWriter = new FileWriter(mapData.toFile(), fileName);
        try {
            fileWriter.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
