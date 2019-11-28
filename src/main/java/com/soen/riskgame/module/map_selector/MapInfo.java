package com.soen.riskgame.module.map_selector;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * class MapInfo implements the getter methods of map name and name
 * @author John
 */
@Data
@AllArgsConstructor
public class MapInfo {
	/**
     * name of the map
     */
    private String name;

    /**
     * image location details
     */
    private String imageUrl;
    /**
     * Constructor of the class
     * @param imageUrl string name of the image
     */
    public MapInfo(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /**
     * getter method to get map name
     * @return imageUrl as a substring
     */
    public String getMapName() {
        return imageUrl.substring(0, imageUrl.length() - 4);
    }
    /**
     * getter method for the name of the map
     * @return name in caps
     */
    public String getName() {
        return StringUtils.capitalize(imageUrl.substring(0, imageUrl.length() - 4)).replace("_", " ");
    }

    @Override
    public String toString() {
        return name;
    }
}
