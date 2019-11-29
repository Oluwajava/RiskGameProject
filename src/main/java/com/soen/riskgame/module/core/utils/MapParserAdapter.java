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
    /**
     * method GameFile
     * @return GameFile
     */
    GameFile getGameFile();

    /**
     * method getContinentDTOS
     * @return list
     */
    List<ContinentDTO> getContinentDTOS();

    /**
     * method getCountries
     * @return Country
     */
    List<CountryDTO> getCountries();

    /**
     * method getBorderDTOS
     * @return Border
     */
    List<BorderDTO> getBorderDTOS();
}
