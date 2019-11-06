package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.CountryDTO;

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

}
