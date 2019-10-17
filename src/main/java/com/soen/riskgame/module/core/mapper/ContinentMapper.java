package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.model.ContinentDTO;
import com.soen.riskgame.module.core.constants.MapDelimiters;


/**
 * class ContinentMapper uses pattern to continentDTO class and contains the methods 
 * mapToContinent ,which pass continent name in String
 *
 */
public class ContinentMapper {
    public ContinentMapper() {

    }

    /**
     * mapToContienet method is used to split the map file based on Continent and these method uses ContientDTO has call type
     * @param data
     * @return
     */
    public static ContinentDTO mapToContinent(String data) {
        String[] continentData = data.split(MapDelimiters.SPACE_DELIMITER);
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(continentData[0]);
        continentDTO.setControlValue(Integer.valueOf(continentData[1]));
        continentDTO.setColor(continentData[2]);
        return continentDTO;
    }
}
