package com.soen.riskgame.module.core.interfaces;
/**
 * Interface Continent to provide the basic functionality related to the entity Continent.
 * @author Hitansh,Mayokun
 */
public interface ContinentAction {

    void addContinent(String name, int controlValue);

    void removeContinent(String name);

    void editContinent(String name, int controlValue);

}
