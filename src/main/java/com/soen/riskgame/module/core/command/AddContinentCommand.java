package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AddContinentCommand implements Command {
    private MapData mapData;
    private String continentName;
    private int controlValue;

    public AddContinentCommand(MapData mapData, String continentName, int controlValue) {
        this.mapData = mapData;
        this.continentName = continentName;
        this.controlValue = controlValue;
    }

    @Override
    public void execute() {}
}