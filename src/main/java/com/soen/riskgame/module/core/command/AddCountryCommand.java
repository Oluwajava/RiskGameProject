package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AddCountryCommand implements Command {

    private MapData mapData;

    private String countryName;

    private String continentName;

    public AddCountryCommand(MapData mapData, String countryName, String continentName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.continentName = continentName;
    }

    @Override
    public void execute() {

    }
}
