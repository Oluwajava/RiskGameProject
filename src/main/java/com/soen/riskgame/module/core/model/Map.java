package com.soen.riskgame.module.core.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

    @Getter
    private final List<CountryDTO> countryDTOList;

    @Getter
    private final List<ContinentDTO> continentDTOList;

    @Getter
    private final List<BorderDTO> borderDTOList;

    @Getter
    private final GameFile gameFile;

    public static class Builder {

        // Required parameters
        private final List<CountryDTO> countryDTOList;
        private final List<ContinentDTO> continentDTOList;
        private final List<BorderDTO> borderDTOList;
        private final GameFile gameFile;

        public Builder(GameFile gameFile,
                       List<CountryDTO> countryDTOList,
                       List<ContinentDTO> continentDTOList,
                       List<BorderDTO> borderDTOList
        ) {
            this.gameFile = gameFile;
            this.countryDTOList = countryDTOList;
            this.continentDTOList = continentDTOList;
            this.borderDTOList = borderDTOList;
        }

        public MapData build() {
            Map map = new Map(this);
            return map.processMapData(map);
        }
    }

    private Map(Builder builder) {
        countryDTOList = builder.countryDTOList;
        continentDTOList = builder.continentDTOList;
        borderDTOList = builder.borderDTOList;
        gameFile = builder.gameFile;
    }


    private MapData processMapData(Map map) {
        MapData data = new MapData();
        data.setContinents(processContinents());
        data.setCountries(processCountries(data.getContinents()));
        addCountriesToContinents(data);
        return data;
    }

    private void addCountriesToContinents(MapData data) {
        data.getCountries().forEach((key, value) -> data.addCountryToContinent(value));
    }

    private HashMap<String, Continent> processContinents() {
        HashMap<String, Continent> continents = new HashMap<>();
        for (int i = 0; i < continentDTOList.size(); i++) {
            Continent continent = new Continent(continentDTOList.get(i).getName(), continentDTOList.get(i).getControlValue(), continentDTOList.get(i).getColor());
            continent.setId(continentDTOList.get(i).getId());
            continents.put(String.valueOf(i + 1), continent);
        }
        return continents;
    }

    private HashMap<String, Country> processCountries(HashMap<String, Continent> continentHashMap) {
        HashMap<String, Country> countryHashMap = new HashMap<>();
        countryDTOList.forEach(v -> {
            Country country = new Country(v.getId(), v.getName(), v.getXCoordinate(), v.getYCoordinate());
            country.setContinent(continentHashMap.get(v.getContinentId()));
            country.setContinentId(v.getContinentId());
            countryHashMap.put(String.valueOf(v.getId()), country);
        });

        countryHashMap.forEach((key, country) -> {
            List<Country> adjacentCountries = buildAdjacentCountries(countryHashMap, borderDTOList.get(Integer.parseInt(key) - 1).getAdjacentCountries());
            country.setAdjacentCountries(adjacentCountries);
            countryHashMap.put(key, country);
        });

        return countryHashMap;
    }


    private List<Country> buildAdjacentCountries(HashMap<String, Country> countryHashMap, List<Long> key) {
        List<Country> countries = key.stream().map(v -> {
            Country country = countryHashMap.get(String.valueOf(v));
            return country;
        }).collect(Collectors.toList());
        return countries;
    }


}
