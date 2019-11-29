package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.mapper.ContinentMapper;
import com.soen.riskgame.module.core.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.soen.riskgame.module.core.mapper.CountryMapper.mapToTerritory;

/**
 * class for ConquestMapParser
 * @author John
 */
public class ConquestMapParser implements MapParserAdapter {
    /**
     * game file data
     */
    private GameFile gameFile;
    /**
     * list of continents
     */
    private List<ContinentDTO> continentDTOS;
    /**
     * list of Territory
     */
    private List<TerritoryDTO> territoryDTOS;
    /**
     * delimeter of the string
     */
    private String currentDelimeter = "";

    /**
     * Parser for conquest maps
     * @param mapData map data
     * @throws Exception
     */
    public ConquestMapParser(String mapData) throws Exception {
        if (mapData == null || mapData.length() == 0) {
            throw new Exception("Invalid data size");
        }
        gameFile = new GameFile();
        continentDTOS = new ArrayList<>();
        territoryDTOS = new ArrayList<>();
        processData(mapData);
    }

    /**
     * method to process data
     * @param mapData has map data
     */
    private void processData(String mapData) {
        String[] lines = mapData.split(MapDelimiters.NEXT_LINE_DELIMETER);
        for (String line : lines) {
            setCurrentDelimeter(line);
            processFileInformation(currentDelimeter, line);
            processContinentInformation(currentDelimeter, line);
            processTerritoryInformation(currentDelimeter, line);
        }
        for (int i = 1; i <= territoryDTOS.size(); i++) {
            territoryDTOS.get(i - 1).setId(i);
        }
        for (int i = 1; i <= continentDTOS.size(); i++) {
            continentDTOS.get(i - 1).setId(Long.valueOf(i));
        }
        System.out.println();
    }

    /**
     * This process the continent information and set the delimeter to the continent
     *
     * @param currentDelimeter delimeter of the string
     * @param line             String value
     */
    private void processContinentInformation(String currentDelimeter, String line) {
        if (this.currentDelimeter.equals(MapDelimiters.CONTINENT_DELIMETER_2)) {
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                continentDTOS.add(ContinentMapper.mapConquestToContinent(line));
            }
        }

    }

    /**
     * This process the country information and set the delimeter to the country
     *
     * @param currentDelimeter currentDelimeter
     * @param line line
     */
    private void processTerritoryInformation(String currentDelimeter, String line) {
        if (this.currentDelimeter.equals(MapDelimiters.TERRITORIES_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.TERRITORIES_DELIMETER;
            if (isSectionContent(line, currentDelimeter) && StringUtils.isNotBlank(line)) {
                territoryDTOS.add(mapToTerritory(line));
            }
        }
    }

    /**
     * methof for processFileInformation
     * @param currentDelimeter currentDelimeter
     * @param line line
     * @return currentDelimeter
     */
    private String processFileInformation(String currentDelimeter, String line) {
        if (line.trim().equals(MapDelimiters.FILE_DELIMITER) || this.currentDelimeter.equals(MapDelimiters.FILE_DELIMITER)) {
            this.currentDelimeter = MapDelimiters.FILE_DELIMITER;

        }
        return currentDelimeter;
    }

    /**
     * methdo for delimeter
     *
     * @param line token
     */
    private void setCurrentDelimeter(String line) {
        if (line.equalsIgnoreCase(MapDelimiters.TERRITORIES_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.TERRITORIES_DELIMETER;
        } else if (line.equalsIgnoreCase(MapDelimiters.CONTINENT_DELIMETER_2)) {
            this.currentDelimeter = MapDelimiters.CONTINENT_DELIMETER_2;
        } else if (line.equalsIgnoreCase(MapDelimiters.MAP_DELIMETER)) {
            this.currentDelimeter = MapDelimiters.MAP_DELIMETER;
        }
    }


    /**
     * Checking the currentDelimenter and borderDelimeter
     *
     * @param line             String value
     * @param currentDelimeter String value delimeter
     * @return boolean on the basis of the check whether currentDelimeter ==borderDelimeter
     */
    private boolean isSectionContent(String line, String currentDelimeter) {
        return !(line.trim().equals(MapDelimiters.CONTINENT_DELIMETER_2)) &&
                !(line.trim().equals(MapDelimiters.TERRITORIES_DELIMETER)) && !(line.trim().equals(MapDelimiters.NEXT_LINE_DELIMETER)) &&
                !(line.trim().equals(currentDelimeter));
    }

    /**
     * method for game file
     * @return gameFile
     */
    @Override
    public GameFile getGameFile() {
        return gameFile;
    }

    /**
     * method for list of continents
     * @return continentDTOS
     */
    @Override
    public List<ContinentDTO> getContinentDTOS() {
        return continentDTOS;
    }

    /**
     * method to  getCountries
     * @return countryDTO
     */
    @Override
    public List<CountryDTO> getCountries() {
        List<CountryDTO> countryDTOS = territoryDTOS.stream().map(v -> {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setId(v.getId());
            countryDTO.setName(v.getName().replace(" ", "_"));
            countryDTO.setContinentId(String.valueOf(getContinentId(v.getContinentName())));
            countryDTO.setXCoordinate(v.getXCoordinate());
            countryDTO.setYCoordinate(v.getYCoordinate());
            return countryDTO;
        }).collect(Collectors.toList());
        return countryDTOS;
    }
    /**
     * method to  get Border
     * @return BorderDTO
     */
    @Override
    public List<BorderDTO> getBorderDTOS() {
        List<BorderDTO> borderDTOS = territoryDTOS.stream().map(v -> {
            BorderDTO borderDTO = new BorderDTO();
            borderDTO.setCountryId(v.getId());
            List<Long> adjacentCountries = v.getAdjacentTerritories().stream().map(i -> getCountryId(i)).collect(Collectors.toList());
            borderDTO.setAdjacentCountries(adjacentCountries);
            return borderDTO;
        }).collect(Collectors.toList());
        return borderDTOS;
    }

    /**
     * method to  get getCountryId
     * @return id
     */
    private long getCountryId(String name) {
        for (TerritoryDTO territoryDTO : territoryDTOS) {
            if (territoryDTO.getName().equalsIgnoreCase(name)) {
                return territoryDTO.getId();
            }
        }
        return -1;
    }
    /**
     * method to  get getContinentId
     * @return id
     */
    private long getContinentId(String name) {
        for(ContinentDTO continentDTO: continentDTOS) {
            if (continentDTO.getName().equalsIgnoreCase(name)) {
                return continentDTO.getId();
            }
        }
        return -1;
    }
}
