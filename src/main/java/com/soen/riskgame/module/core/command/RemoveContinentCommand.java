package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class RemoveContinentCommand implements Command {

    private MapData mapData;

    private String continentName;

    public RemoveContinentCommand(MapData mapData, String continentName) {
        this.mapData = mapData;
        this.continentName = continentName;
    }

    @Override
    public void execute() {
    }
}
