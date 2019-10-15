package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileWriter;

import java.io.FileNotFoundException;

public class SaveFileCommand implements Command {

    private MapData mapData;

    private String fileName;

    public SaveFileCommand(MapData mapData, String fileName) {
        this.mapData = mapData;
        this.fileName = fileName;
    }

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
