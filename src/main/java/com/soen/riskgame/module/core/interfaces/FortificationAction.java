package com.soen.riskgame.module.core.interfaces;

/**
 * Interface to support the functionality of fortification phase
 * @author hitan
 *
 */
public interface FortificationAction {

	/**
	 * Method to Fortify a Country
	 * @param fromCountry name of Country
	 * @param toCountry name of New Country
	 * @param num number
	 */
    void fortifyCountry(String fromCountry, String toCountry, int num);

    /**
     * Method for not Fortifying anything
     */
    void fortifyNone();
}
