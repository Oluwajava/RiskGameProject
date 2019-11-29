package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.CountryDTO;
import com.soen.riskgame.module.core.model.TerritoryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * class CountryMapper uses pattern to countryDTO class and contains the methods 
 * mapToCountry ,which pass country name in String
 * @author Sai Sukruth
 */
public class CountryMapper {
    public CountryMapper(){

    }
    /**
     * mapToCountry method is used to split the map file based on country and these method uses ContientDTO has call type
     * @param data String input data from map
     * @return <b>countryDTO</b> output of data
     */
    public static CountryDTO mapToCountry(String data){
        String[] countryData = data.split(MapDelimiters.SPACE_DELIMITER);
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(Long.valueOf(countryData[0]));
        countryDTO.setName(countryData[1]);
        countryDTO.setContinentId(countryData[2]);
        countryDTO.setXCoordinate(countryData[3]);
        countryDTO.setYCoordinate(countryData[4]);
        return countryDTO;
    }

    /**
     * method for TerritoryDTO
     * @param data data
     * @return TerritoryDTO
     */
    public static TerritoryDTO mapToTerritory(String data) {
        String[] countryData = data.split(MapDelimiters.COMMA_DELIMITER);
        TerritoryDTO countryDTO = new TerritoryDTO();
        countryDTO.setName(countryData[0]);
        countryDTO.setXCoordinate(countryData[1]);
        countryDTO.setYCoordinate(countryData[2]);
        countryDTO.setContinentName(countryData[3]);
        List<String> adjacentCountries = new ArrayList<>();
        for(int i = 4; i < countryData.length; i++) {
            adjacentCountries.add(countryData[i]);
        }
        countryDTO.setAdjacentTerritories(adjacentCountries);
        return countryDTO;
    }
}
