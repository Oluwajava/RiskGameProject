package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class RemoveNeigbourCommand implements Command {

    private MapData mapData;

    private String countryName;

    private String countryNeigbourName;

    public RemoveNeigbourCommand(MapData mapData, String countryName, String countryNeigbourName) {
        this.mapData = mapData;
        this.countryName = countryName;
        this.countryNeigbourName = countryNeigbourName;
    }

    @Override
    public void execute() {
        mapData.removeNeighbour(countryName, countryNeigbourName);
    }
}
