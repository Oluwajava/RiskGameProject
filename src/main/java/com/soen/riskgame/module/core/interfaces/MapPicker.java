package com.soen.riskgame.module.core.interfaces;

/**
 * Interface to provide the functionality of choosing a particular map
 * @author hitan
 *
 */
public interface MapPicker {

	/**
	 * Method onMapSelected
	 * @param name name of the type String
	 * @throws Exception
	 */
    void onMapSelected(String name) throws Exception;

}
