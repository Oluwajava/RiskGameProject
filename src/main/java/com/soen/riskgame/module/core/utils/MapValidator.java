package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.MapData;

import java.util.HashSet;
/**
 * class to validate the map
 * @author Sibil
 */
public class MapValidator {
    /**
     * map data as string
     */
    private String mapData;

    /**
     * constructor for the class
     * @param mapData map data as string
     * @throws Exception
     */
    public MapValidator(String mapData) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        this.mapData = mapData;
    }
    /**
     * validate method to chck all tags
     * @throws Exception
     */
    public void validate() throws Exception {
        checkAllTags();
    }
    /**
     * method to chcek all tags
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
     * method to if all countries and continent in graph are connected are not
     * @param data  map data
     * @return true or false based on graph connectivity
     */
    public static boolean isGraphConnected(MapData data) {
        boolean connected = new GraphUtil(new HashSet<>(data.getCountries().values())).isConnected();
        if (!connected) return connected;
        connected = isCountriesInContinentsConnected(data, connected);
        return connected;
    }
    /**
     * method to if all countries and continent are connected are not
     * @param data map data
     * @param connected boolean value for graph connectivity
     * @return true or false based on graph connectivity
     */
    public static boolean isCountriesInContinentsConnected(MapData data, boolean connected) {
        for (java.util.Map.Entry<String, Continent> continent : data.getContinents().entrySet()) {
            connected = new GraphUtil(new HashSet<>(continent.getValue().getCountries())).isConnectedSubGraph();
        }
        return connected;
    }

}
