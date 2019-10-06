package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.BorderDTO;
import com.soen.riskgame.module.core.model.ContinentDTO;
import com.soen.riskgame.module.core.model.CountryDTO;
import com.soen.riskgame.module.core.model.GameFile;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Data
public class MapParser {

    private List<ContinentDTO> continentDTOS;

    private List<BorderDTO> borderDTOS;

    private GameFile gameFile;

    private List<CountryDTO> countries;


    MapParser(String mapData) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        gameFile = new GameFile();
        continentDTOS = new ArrayList<>();
        countries = new ArrayList<>();
        borderDTOS = new ArrayList<>();
        processData(mapData);
    }

    private void processData(String mapData) {
        String[] lines = mapData.split(MapDelimiters.NEXT_LINE_DELIMETER);
        String currentDelimeter = "";
        for (String line : lines) {
            processFileInformation(currentDelimeter, line);
            processBorderInformation(currentDelimeter, line);
            processContinentInformation(currentDelimeter, line);
            processCountryInformation(currentDelimeter, line);
        }
        System.out.println();
    }

    private String processCountryInformation(String currentDelimeter, String line) {
        if (line.trim().equals(MapDelimiters.COUNTRY_DELIMETER) || currentDelimeter.equals(MapDelimiters.COUNTRY_DELIMETER)) {
            currentDelimeter = MapDelimiters.COUNTRY_DELIMETER;
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                countries.add(mapToCountry(line));
            }
        }
        return currentDelimeter;
    }

    private static String processContinentInformation(String value1, String value2) {
        String returnValue = null;
        return returnValue;
    }

    private static String processBorderInformation(String value1, String value2) {
        String returnValue = null;
        return returnValue;
    }

    private static String processFileInformation(String value1, String value2) {
        String returnValue = null;
        return returnValue;
    }

    private void addDataToList(String currentDelimiter, List<String> files, String line) {
        if (isSectionContent(line, currentDelimiter) && StringUtils.isNotBlank(line)) {
            files.add(line.trim());
        }
    }

    private boolean isSectionContent(String line, String currentDelimeter) {
        return !(line.trim().equals(MapDelimiters.BORDER_DELIMETER)) && !(line.trim().equals(MapDelimiters.CONTINENT_DELIMETER)) &&
                !(line.trim().equals(MapDelimiters.COUNTRY_DELIMETER)) && !(line.trim().equals(MapDelimiters.NEXT_LINE_DELIMETER)) &&
                !(line.trim().equals(currentDelimeter));
    }
}
