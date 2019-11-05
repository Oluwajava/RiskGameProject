package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AttackNoneCommand implements Command {

    private MapData mapData;

    public AttackNoneCommand(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void execute() {
        mapData.attackNone();
    }

}
