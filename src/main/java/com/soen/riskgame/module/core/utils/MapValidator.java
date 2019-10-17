package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.MapData;

import java.util.HashSet;

/**
 * This class validates the map
 */
public class MapValidator {

    private String mapData;

    public MapValidator(String mapData) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        this.mapData = mapData;
    }

    /**
     * check all the tags
     * @throws Exception
     */
    public void validate() throws Exception {
        checkAllTags();
    }

    /**
     * check whether the map contains all the tags
     * @throws Exception
     */
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

    /**
     * check whether the graph is connected or not
     * @param data
     * @return
     */
    public static boolean isGraphConnected(MapData data) {
        boolean connected = GraphUtil.isGraphConnected(new HashSet<>(data.getCountries().values()));
        connected = isCountriesInContinentsConnected(data, connected);
        return connected;
    }

    /**
     * This fucntion will check the countries in continent is connected or not
     * @param data
     * @param connected
     * @return
     */
    public static boolean isCountriesInContinentsConnected(MapData data, boolean connected) {
        for (java.util.Map.Entry<String, Continent> continent : data.getContinents().entrySet()) {
            connected = GraphUtil.isGraphConnected(new HashSet<>(continent.getValue().getCountries()));
        }
        return connected;
    }

}
