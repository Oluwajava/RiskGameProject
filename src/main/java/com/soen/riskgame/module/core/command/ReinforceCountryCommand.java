package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class ReinforceCountryCommand implements Command {

    private MapData mapData;

    private String countryName;

    private int num;

    public ReinforceCountryCommand(MapData mapData, String countryName, int num) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.num = num;
    }

    @Override
    public void execute() {
        mapData.reinforceCountry(countryName, num);
    }

}
