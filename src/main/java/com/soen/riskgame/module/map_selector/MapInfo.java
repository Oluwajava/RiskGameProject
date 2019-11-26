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

    private String name;


    private String imageUrl;

    public MapInfo(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMapName() {
        return imageUrl.substring(0, imageUrl.length() - 4);
    }
    public String getName() {
        return StringUtils.capitalize(imageUrl.substring(0, imageUrl.length() - 4)).replace("_", " ");
    }

    @Override
    public String toString() {
        return name;
    }
}
