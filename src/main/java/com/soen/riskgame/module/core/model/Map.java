package com.soen.riskgame.module.core.model;

import lombok.Getter;

import java.util.List;

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

}
