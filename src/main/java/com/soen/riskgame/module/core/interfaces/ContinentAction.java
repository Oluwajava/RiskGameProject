package com.soen.riskgame.module.core.interfaces;
/**
 * Interface Continent to provide the basic functionality related to the entity Continent.
 */
public interface ContinentAction {
	/**
	 * Method addContinent to add a new continent
	 *@param name name of the continent
	 *@param controlValue control value for a component 
	 */
    void addContinent(String name, int controlValue);

    /**
     * Method removeContinent is remove a particular continent
     * @param name name of the continent to be removed.
     */
    void removeContinent(String name);

    /**
     * Method edit continent is to provide the functionality of editing a particular continent
     * @param name name of the continent to be edited
     * @param controlValue control value of the continent to be edited
     */
    void editContinent(String name, int controlValue);

}
