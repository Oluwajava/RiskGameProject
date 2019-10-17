package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class PlaceAllArmyCommand implements Command {

    private MapData mapData;

    public PlaceAllArmyCommand(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void execute() {
        mapData.placeAll();
    }
}
