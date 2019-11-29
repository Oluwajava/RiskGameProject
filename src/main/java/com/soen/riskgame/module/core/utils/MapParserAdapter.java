package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.model.BorderDTO;
import com.soen.riskgame.module.core.model.ContinentDTO;
import com.soen.riskgame.module.core.model.CountryDTO;
import com.soen.riskgame.module.core.model.GameFile;

import java.util.List;

/**
 * interface for MapParserAdapter
 * @author Mayo
 */
public interface MapParserAdapter {

    GameFile getGameFile();
    /**
     * list of continents
     */
    List<ContinentDTO> getContinentDTOS();
    /**
     * list of countries
     */
    List<CountryDTO> getCountries();
    /**
     * list of borders
     */
    List<BorderDTO> getBorderDTOS();
}
