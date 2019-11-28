package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileWriter;

import java.io.FileNotFoundException;

/**
 * * Class uses command pattern to abstract SaveFile Command
 *  * for the Map This class can be called by either the GUI or the command line to perform
 *  * @see com.soen.riskgame.module.core.interfaces.Command
 *  * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author Mayokun
 */
public class SaveFileCommand implements Command {
    /**
     * holds map data
     */
    private MapData mapData;
    /**
     * name of the file to be saved
     */
    private String fileName;

    /**
     * Constructor for the MapEditor class Initializes mapIO object.
     * @param mapData holds map data
     * @param fileName name of the file to be saved
     */
    public SaveFileCommand(MapData mapData, String fileName) {
        this.mapData = mapData;
        this.fileName = fileName;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the RemoveCountryCommand
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
