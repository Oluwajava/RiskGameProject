package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EditMapCommand implements Command {

    private EditMapListener editMapListener;

    private String fileName;

    public EditMapCommand(EditMapListener editMapListener, String fileName) {
        this.editMapListener = editMapListener;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        editMapListener.editMap(data);
    }

    public interface EditMapListener {

        void editMap(MapData ma);

    }
}
