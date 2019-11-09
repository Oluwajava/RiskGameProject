package com.soen.riskgame.module.core.interfaces;

/**
 * Interface for card action
 * @author Hitansh
 */
public interface CardAction {
    /**
     * Method for exchange cards
     * @param num1 type of card
     * @param num2 type of card
     * @param num3 type of card
     */
    void exchange(int num1, int num2, int num3);

    /**
     * Method to exchange none
     */
    void exchangeNone();


}
