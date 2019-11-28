package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.MapData;

public class AggressiveStrategy implements PlayerStrategy {
    @Override
    public void reinforce(MapData mapData) {
    }

    @Override
    public void attack(MapData mapData) {
        System.out.println("Aggressive Attack");
        mapData.attackNone();
    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        System.out.println("Aggressive Fortification");
        mapData.fortifyNone();
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }
}
