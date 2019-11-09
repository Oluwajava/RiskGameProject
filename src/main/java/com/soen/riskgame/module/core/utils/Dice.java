package com.soen.riskgame.module.core.utils;

/**
 * Class for to roll dice
 */
public class Dice {
    /**
     * method for output of dice
     * @return integer 1-6
     */
    public static int roll() {
        return (int) (Math.random() * 6 + 1);
    }

}
