package com.soen.riskgame.module.core.mapper;

import com.soen.riskgame.module.core.model.GameFile;
import com.soen.riskgame.module.core.constants.MapDelimiters;

/**
 * class GameFileMapper parses the GameFileDTO class and contains the methods 
 * mapToGameFile ,which pass a String 
 *
 */
public class GameFileMapper {
    /**
     * Default constructor of the GameFileMapper class
     */
    public GameFileMapper(){

    }
    /**
     * mapToGamefile method  is used to split the map file based  on GameFile and these method uses GameFileDTO has call type
     * @param data
     *
     */
    public static GameFile mapToGameFile(String data)
    {
        String[] gameFileData = data.split(MapDelimiters.SPACE_DELIMITER);
//        GameFile gameFile = new GameFile(gameFileData)
        return null;
    }
}
