package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class PopulateCountriesCommand implements Command {

    private MapData mapData;

    public PopulateCountriesCommand(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void execute() {
        mapData.populateCountries();
    }
}
