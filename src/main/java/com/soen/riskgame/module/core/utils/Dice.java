package com.soen.riskgame.module.core.utils;

public class Dice {

    public static int roll() {
        return (int) (Math.random() * 6 + 1);
    }

}
