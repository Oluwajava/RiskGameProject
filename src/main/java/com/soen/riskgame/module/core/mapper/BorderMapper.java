package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.BorderDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class BorderMapper uses pattern to BorderDTO class and contains the methods 
 * mapToBorder
 * @author Sai Sukruth
 */
public class BorderMapper {

    /**
     * default Constructor of the BorderMapper class
     */
    public BorderMapper() {

    }
    /**
     * mapToborder method is used to split the map file based on border and these method uses BorderDTO has call type
     * @param data String input data from map
     * @return <b>borderDTO</b> output of data
     */
    public static BorderDTO mapToBorder(String data) {
        String[] borderData = data.split(MapDelimiters.SPACE_DELIMITER);
        BorderDTO borderDTO = new BorderDTO();
        borderDTO.setCountryId(Long.valueOf(borderData[0]));
        List<Long> adjacentCountries = Arrays.stream(borderData).map(Long::valueOf).collect(Collectors.toList());
        adjacentCountries.remove(borderDTO.getCountryId());
        borderDTO.setAdjacentCountries(adjacentCountries);
        return borderDTO;
    }
}  
