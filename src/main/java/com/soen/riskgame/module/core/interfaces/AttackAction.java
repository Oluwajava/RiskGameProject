package com.soen.riskgame.module.core.interfaces;

/**
 * Interface ArmyAction to providing declaration of various methods related to army commands
 * @author hitan
 */
public interface AttackAction {
    /**
     * Method to attack
     * @param fromCountry from country
     * @param toCountry to country
     * @param numOfDice numbet of dice rolled
     */
    void attack(String fromCountry, String toCountry, int numOfDice);

    /**
     *Method to attack
     * @param fromCountry from country
     * @param toCountry to country
     */
    void attack(String fromCountry, String toCountry, boolean updateView);

    /**
     * Method to attack move
     * @param num number of armies
     */
    void attackMove( int num);

    /**
     * method to attack none
     */
    void attackNone();

    /**
     * method to defend
     * @param num no fo dice
     */
    void defend(int num);

}
