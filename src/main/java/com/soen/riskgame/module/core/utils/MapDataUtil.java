package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapDataUtil {

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
        MapParser mapParser = null;
        try {
            mapParser = new MapParser(mapData);
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

    public static boolean isAllCountryAssigned(HashMap<String, Country> coutries) {
        for(Country country: coutries.values()) {
            if (country.getPlayer() == null) {
                return false;
            }
        }
        return true;
    }
}
