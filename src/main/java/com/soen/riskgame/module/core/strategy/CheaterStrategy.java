package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.MapData;

public class CheaterStrategy implements PlayerStrategy {
    @Override
    public void reinforce(MapData mapData) {
        System.out.println("Cheater Reinforcement");
    }

    @Override
    public void attack(MapData mapData) {
        System.out.println("Cheater Attack");
    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        System.out.println("Cheater Fortification");
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }
}
