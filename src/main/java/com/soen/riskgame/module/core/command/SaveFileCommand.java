package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileWriter;

import java.io.FileNotFoundException;

/**
 * Class SaveFileCommand implements the interface Command
 * provides the functionality of the game command "save file"
 *
 */
public class SaveFileCommand implements Command {

	/**
	 * object of the class MapData
	 * Contains details about the map
	 */
    private MapData mapData;

    /**
     * name of the mapFile
     */
    private String fileName;

    /**
     * Parameterized Constructor
     * @param mapData Object of MapData
     * @param fileName name of the file
     */
    public SaveFileCommand(MapData mapData, String fileName) {
        this.mapData = mapData;
        this.fileName = fileName;
    }

    /**
     * Method execute()
     * calls the save function of FileWriter to save a particular Map file
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
