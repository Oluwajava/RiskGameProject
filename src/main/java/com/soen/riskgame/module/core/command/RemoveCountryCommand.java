package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.interfaces.Command;

public class RemoveCountryCommand implements Command {
    private MapData mapData;
    private String countryName;

    public RemoveCountryCommand(MapData mapData, String countryName) {
        this.mapData = mapData;
        this.countryName = countryName;
    }

    @Override
    public void execute() {}
}
