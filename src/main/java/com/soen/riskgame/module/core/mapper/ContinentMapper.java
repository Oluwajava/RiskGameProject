package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.ContinentDTO;

/**
 * class ContinentMapper uses pattern to continentDTO class and contains the methods 
 * mapToContinent ,which pass continent name in String
 * @author Sai Sukruth
 *
 */
public class ContinentMapper {
    /**
     * Constructor for class
     */
    public ContinentMapper() {

    }

    /**
     * mapToContienet method is used to split the map file based on Continent and these method uses ContientDTO has call type
     * @param data String input data from map
     * @return <b>continentDTO</b> output of data
     */
    public static ContinentDTO mapToContinent(String data) {
        String[] continentData = data.split(MapDelimiters.SPACE_DELIMITER);
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(continentData[0]);
        continentDTO.setControlValue(Integer.valueOf(continentData[1]));
        continentDTO.setColor(continentData[2]);
        return continentDTO;
    }

    /**
     *  mapToContienet method is used to split the map file based on Continent and these method uses ContientDTO has call type
     * @param data String input data from map
     * @return <b>continentDTO</b> output of data
     */
    public static ContinentDTO mapConquestToContinent(String data) {
        String[] continentData = data.split("=");
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(continentData[0]);
        continentDTO.setControlValue(Integer.valueOf(continentData[1]));
        return continentDTO;
    }
}
