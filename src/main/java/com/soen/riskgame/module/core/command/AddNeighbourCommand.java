package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AddNeighbourCommand implements Command {

    MapData mapData;
    String countryName;
    String neighbourCountryName;


    public AddNeighbourCommand(MapData mapData, String countryName, String neighbourCountryName) {
        super();
        this.mapData = mapData;
        this.countryName = countryName;
        this.neighbourCountryName = neighbourCountryName;
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

}
