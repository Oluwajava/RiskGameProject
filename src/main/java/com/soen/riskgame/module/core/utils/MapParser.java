package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.mapper.BorderMapper;
import com.soen.riskgame.module.core.mapper.ContinentMapper;
import com.soen.riskgame.module.core.mapper.CountryMapper;
import com.soen.riskgame.module.core.model.*;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.soen.riskgame.module.core.mapper.CountryMapper.mapToCountry;

/**
 * This class parse the data from file. In this class the data is parsed on the basis
 * of the section in the file.
 * The sections are divided into four continents,borders, countries and gameFile
 *  @author Sibil
 */
@Getter
public class MapParser implements MapParserAdapter{
    /**
     * game file data
     */
    private GameFile gameFile;
    /**
     * list of continents
     */
    private List<ContinentDTO> continentDTOS;
    /**
     * list of countries
     */
    private List<CountryDTO> countries;
    /**
     * list of borders
     */
    private List<BorderDTO> borderDTOS;
    /**
     * delimeter of the string
     */
    private String currentDelimeter = "";

    /**
     * Constructor intialize the variables
     *
     * @param mapData map data values
     * @throws Exception
     */
    public MapParser(String mapData) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        gameFile = new GameFile();
        continentDTOS = new ArrayList<>();
        countries = new ArrayList<>();
        borderDTOS = new ArrayList<>();
        processData(mapData);
    }

    /**
     * This function process the data and reads the string line by line.
     *
     * @param mapData map data values
     */
    private void processData(String mapData) {
        String[] lines = mapData.split(MapDelimiters.NEXT_LINE_DELIMETER);
        for (String line : lines) {
            setCurrentDelimeter(line);
            processFileInformation(currentDelimeter, line);
            processBorderInformation(currentDelimeter, line);
            processContinentInformation(currentDelimeter, line);
            processCountryInformation(currentDelimeter, line);
        }
        System.out.println();
    }

    /**
     * This process the country information and set the delimeter to the country
     *
     * @param currentDelimeter
     * @param line
     * @return
     */
    private void processCountryInformation(String currentDelimeter, String line) {
        if (this.currentDelimeter.equals(MapDelimiters.COUNTRY_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.COUNTRY_DELIMETER;
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                countries.add(mapToCountry(line));
            }
        }
    }

    /**
     * methdo for delimeter
     * @param line token
     */
    private void setCurrentDelimeter(String line) {
        if (line.equalsIgnoreCase(MapDelimiters.COUNTRY_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.COUNTRY_DELIMETER;
        } else if (line.equalsIgnoreCase(MapDelimiters.CONTINENT_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.CONTINENT_DELIMETER;
        } else if (line.equalsIgnoreCase(MapDelimiters.BORDER_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.BORDER_DELIMETER;
        } else if (line.equalsIgnoreCase(MapDelimiters.FILE_DELIMITER)) {
            this.currentDelimeter = MapDelimiters.FILE_DELIMITER;
        }
    }

    /**
     * This process the continent information and set the delimeter to the continent
     *
     * @param currentDelimeter delimeter of the string
     * @param line String value
     */
    private void processContinentInformation(String currentDelimeter, String line) {
        if (this.currentDelimeter.equals(MapDelimiters.CONTINENT_DELIMETER)) {
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                continentDTOS.add(ContinentMapper.mapToContinent(line));
            }
        }

    }

    /**
     * This process the border information and set the delimeter to the boreder
     *
     * @param currentDelimeter delimeter of the string
     * @param line String value
     * @return String value delimeter
     */
    private String processBorderInformation(String currentDelimeter, String line) {
        if (this.currentDelimeter.equals(MapDelimiters.BORDER_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.BORDER_DELIMETER;
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                borderDTOS.add(BorderMapper.mapToBorder(line));
            }
        }
        return currentDelimeter;
    }

    /**
     * This process the file information and set the delimeter to the file delimeter
     *
     * @param currentDelimeter delimeter of the string
     * @param line String value
     * @return String value delimeter
     */
    private String processFileInformation(String currentDelimeter, String line) {
        if (line.trim().equals(MapDelimiters.FILE_DELIMITER) || this.currentDelimeter.equals(MapDelimiters.FILE_DELIMITER)) {
            this.currentDelimeter = MapDelimiters.FILE_DELIMITER;

        }
        return currentDelimeter;
    }

    /**
     *
     * @param currentDelimiter String value delimeter
     * @param files list of files
     * @param line delimeter string value
     */
    private void addDataToList(String currentDelimiter, List<String> files, String line) {
        if (isSectionContent(line, currentDelimiter) && StringUtils.isNotBlank(line)) {
            files.add(line.trim());
        }
    }

    /**
     * Checking the currentDelimenter and borderDelimeter
     *
     * @param line String value
     * @param currentDelimeter String value delimeter
     * @return boolean on the basis of the check whether currentDelimeter ==borderDelimeter
     */
    private boolean isSectionContent(String line, String currentDelimeter) {
        return !(line.trim().equals(MapDelimiters.BORDER_DELIMETER)) && !(line.trim().equals(MapDelimiters.CONTINENT_DELIMETER)) &&
                !(line.trim().equals(MapDelimiters.COUNTRY_DELIMETER)) && !(line.trim().equals(MapDelimiters.NEXT_LINE_DELIMETER)) &&
                !(line.trim().equals(currentDelimeter));
    }
}
