package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.MapValidator;


import java.io.IOException;

public class LoadMapCommand implements Command {

    private String fileName;

    private LoadMapListener loadMapListener;

    public LoadMapCommand(String fileName, LoadMapListener loadMapListener) {
        this.fileName = fileName;
        this.loadMapListener = loadMapListener;
    }

    @Override
    public void execute() {
        MapData data = MapDataUtil.loadMapFromFile(fileName);
        loadMapListener.loadMap(data);
    }

    public interface LoadMapListener {

        void loadMap(MapData ma);

    }
}
