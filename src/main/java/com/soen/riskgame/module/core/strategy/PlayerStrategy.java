package com.soen.riskgame.module.core.strategy;


import com.soen.riskgame.module.core.model.MapData;

/**
 * Interface for PlayerStrategy
 * @author Mayo
 */
public interface PlayerStrategy  {
    /**
     * method for reinforce
     * @param mapData hold the map's data
     */
    void reinforce(MapData mapData);
    /**
     * method for attack
     * @param mapData hold the map's data
     */
    void attack(MapData mapData);
    /**
     * method for fortify
     * @param mapData hold the map's data
     */
    void fortify(MapData mapData);


}
