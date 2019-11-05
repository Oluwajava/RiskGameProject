package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class AttackMoveCommand implements Command {

    private MapData mapData;


    private int num;


    public AttackMoveCommand(MapData mapData, int num) {
        this.mapData = mapData;
        this.num = num;
    }

    @Override
    public void execute() {
        mapData.attackMove(num);
    }

}
