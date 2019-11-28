package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.MapData;

public class RandomStrategy implements PlayerStrategy {
    @Override
    public void reinforce(MapData mapData) {
        System.out.println("Random Reinforcement");
    }

    @Override
    public void attack(MapData mapData) {
        System.out.println("Random Attack");
    }

    @Override
    public void attackMove(MapData mapData) {

    }

    @Override
    public void fortify(MapData mapData) {
        System.out.println("Random Fortification");
    }

    @Override
    public void exchangeCard(MapData mapData) {

    }
}
