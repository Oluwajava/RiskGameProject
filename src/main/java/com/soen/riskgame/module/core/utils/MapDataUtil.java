package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * This class loads the map data from the file
 * @author Sibil
 */
public class MapDataUtil {
    /**
     * method to load the map from the given file
     * @param fileName
     * @return map parsed data
     */
    public static MapData loadMapFromFile(String fileName) {
        FileReader fileReader = new FileReader("/Users/oluwajava/Documents/Software Engineering/Advance Programming Practice/RiskGameProject/src/main/resources/maps/" + fileName + ".map");
        String mapData = null;
        try {
            mapData = fileReader.readData().replaceAll(MapDelimiters.CARRIAGE_DELIMITER, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MapValidator mapValidator = null;
        try {
            mapValidator = new MapValidator(mapData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mapValidator.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MapParserAdapter mapParser = null;
        try {
            if (mapValidator.isRiskMap()) {
                mapParser = new MapParser(mapData);
            } else {
                mapParser = new ConquestMapParser(mapData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        MapData data = null;
        if (mapParser != null) {
            data = new com.soen.riskgame.module.core.model.Map.Builder(mapParser.getGameFile(), mapParser.getCountries(), mapParser.getContinentDTOS(), mapParser.getBorderDTOS()).build();
        } else {
            data = new MapData();
        }
        data.setFileName(fileName);
        return data;
    }
    /**
     * method to find the continent by name
     * @param continentName name of the continent
     * @param continents list of continents
     * @return if present the continent else null
     */
    public static Continent findContinentByName(String continentName, HashMap<String, Continent> continents) {
        Optional<Map.Entry<String, Continent>> entries = continents.entrySet()
                .stream()
                .filter(v -> v.getValue().getName().equalsIgnoreCase(continentName))
                .findFirst();
        if (entries.isPresent()) {
            return entries.get().getValue();
        }
        return null;
    }

    /**
     * method to find the country by name
     * @param countryName name of the country
     * @param countries list of countries
     * @return if present the country else null
     */
    public static Country findCountryByName(String countryName, HashMap<String, Country> countries) {
        Optional<Map.Entry<String, Country>> entries = countries.entrySet()
                .stream()
                .filter(v -> v.getValue().getName().equalsIgnoreCase(countryName))
                .findFirst();
        if (entries.isPresent()) {
            return entries.get().getValue();
        }
        return null;
    }
    /**
     * method to check all countries are assigned are not
     * @param coutries list of countries
     * @return true or false based on all countries that are assigned
     */
    public static boolean isAllCountryAssigned(HashMap<String, Country> coutries) {
        for(Country country: coutries.values()) {
            if (country.getPlayer() == null) {
                return false;
            }
        }
        return true;
    }
}
