package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AttackCommand implements Command {

    private MapData mapData;

    private String fromCountry;

    private String toCountry;

    private int num;

    private String extendedAction;

    public AttackCommand(MapData mapData, String fromCountry, String toCountry, int num) {
        this.mapData = mapData;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.num = num;
    }
    @Override
    public void execute() {
        mapData.attack(fromCountry, toCountry, num);
    }

    public void setExtendedAction(String extendedAction) {
        this.extendedAction = extendedAction;
    }
}
