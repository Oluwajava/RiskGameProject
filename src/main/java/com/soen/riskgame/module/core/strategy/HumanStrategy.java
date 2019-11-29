package com.soen.riskgame.module.core.strategy;

import com.soen.riskgame.module.core.model.MapData;

import java.io.Serializable;

/**
 * This class defines the strategy of a human ,implemets commands amanually
 * @author Sibil
 */
public class HumanStrategy implements PlayerStrategy, Serializable {
    /**
     * method for reinforce
     * @param mapData hold the map's data
     */
    @Override
    public void reinforce(MapData mapData) {

    }
    /**
     * method for attack
     * @param mapData hold the map's data
     */
    @Override
    public void attack(MapData mapData) {

    }
    /**
     * method for fortify
     * @param mapData hold the map's data
     */
    @Override
    public void fortify(MapData mapData) {

    }


}
