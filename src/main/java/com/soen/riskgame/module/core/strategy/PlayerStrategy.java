package com.soen.riskgame.module.core.strategy;


import com.soen.riskgame.module.core.model.MapData;


public interface PlayerStrategy  {

    void reinforce(MapData mapData);

    void attack(MapData mapData);

    void fortify(MapData mapData);

}
