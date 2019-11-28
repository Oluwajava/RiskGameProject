package com.soen.riskgame.module.core.interfaces;
/**
 * Interface ArmyAction to providing declaration of various methods related to army commands
 * @author hitan
 *
 */
public interface ArmyAction {
	   /**
     * Method to place army
     * @param countryName name of the country
     */
    void placeArmy(String countryName);
    /**
     * Method to place all the army
     */
    void placeAll();

}
