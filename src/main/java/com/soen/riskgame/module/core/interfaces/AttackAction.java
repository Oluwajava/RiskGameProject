package com.soen.riskgame.module.core.interfaces;

public interface AttackAction {

    void attack(String fromCountry, String toCountry, int numOfDice);

    void attack(String fromCountry, String toCountry);

    void attackMove( int num);

    void attackNone();

    void defend(int num);

}
