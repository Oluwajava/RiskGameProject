package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class FortifyCommand implements Command {

    private MapData mapData;

    private String fromCountry;

    private String toCountry;

    private int num;

    public FortifyCommand(MapData mapData, String fromCountry, String toCountry, int num) {
        this.mapData = mapData;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.num = num;
    }


    @Override
    public void execute() {
        mapData.fortifyCountry(fromCountry, toCountry, num);
    }
}
