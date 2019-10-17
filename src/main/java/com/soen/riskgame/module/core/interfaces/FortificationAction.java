package com.soen.riskgame.module.core.interfaces;

public interface FortificationAction {

    void fortifyCountry(String fromCountry, String toCountry, int num);

    void fortifyNone();
}
