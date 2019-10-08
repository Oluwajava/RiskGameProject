package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.MapData;

import java.util.HashSet;

public class MapValidator {
    String mapData;

    public MapValidator(String data) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        this.mapData = mapData;
    }

    public void validate() throws Exception {
        checkAllTags();
    }

    public void checkAllTags() throws Exception {
        if (!mapData.contains(MapDelimiters.BORDER_DELIMETER)) {
            throw new Exception("Cannot find BorderDTO for Map");
        }

        if (!mapData.contains(MapDelimiters.CONTINENT_DELIMETER)) {
            throw new Exception("Cannot find ContinentDTO for Map");
        }

        if (!mapData.contains(MapDelimiters.COUNTRY_DELIMETER)) {
            throw new Exception("Cannot find CountryDTO for Map");
        }

        if (!mapData.contains(MapDelimiters.FILE_DELIMITER)) {
            throw new Exception("Canont find File Data for Map");
        }
    }

    public boolean isGraphConnected(MapData data) {
       boolean connected = false;
        return connected;
    }

    public boolean isCountriesInContinentsConnected(MapData data, boolean connected) {

        return connected;
    }

}
