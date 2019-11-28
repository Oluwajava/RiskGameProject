package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.MapData;

public class BenevolentStrategy implements PlayerStrategy {
    @Override
    public void reinforce(MapData mapData) {
        System.out.println("Benevolent Reinforcement");
    }

    @Override
    public void attack(MapData mapData) {
        System.out.println("Benevolent Attack");
        mapData.attackNone();
    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        System.out.println("Benevolent Fortification");
        mapData.fortifyNone();
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }
}
