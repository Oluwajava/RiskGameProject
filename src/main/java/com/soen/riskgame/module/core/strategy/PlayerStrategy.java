package com.soen.riskgame.module.core.strategy;


import com.soen.riskgame.module.core.model.MapData;

import java.io.Serializable;


public interface PlayerStrategy extends Serializable {

    void reinforce(MapData mapData);

    void attack(MapData mapData);
    
    void attackMove(MapData mapData);

    void fortify(MapData mapData);

    void exchangeCard(MapData mapData);
}
