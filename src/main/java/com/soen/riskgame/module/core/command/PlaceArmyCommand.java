package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class PlaceArmyCommand implements Command {

    private MapData mapData;

    private String countryName;

    public PlaceArmyCommand(MapData mapData, String countryName) {
        this.mapData = mapData;
        this.countryName = countryName;
    }


    @Override
    public void execute() {
        mapData.placeArmy(countryName);
    }
}
