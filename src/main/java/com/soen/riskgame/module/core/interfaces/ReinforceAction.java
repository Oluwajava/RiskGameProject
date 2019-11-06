package com.soen.riskgame.module.core.interfaces;

/**
 * Interface ReinforceAction to support the functionality of Reinforce action
 * @author Mayokun
 */
public interface ReinforceAction {

    /**
     * method to reinforce a country
     * @param countryName name of the country
     * @param number number of armies
     */
    void reinforceCountry(String countryName, int number);

}