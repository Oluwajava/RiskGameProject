package com.soen.riskgame.module.core.model;

import lombok.Data;

/**
 * class for player DTO
 * @author Mayokun
 */
@Data
public class PlayerDTO {
    /**
     * name of the player
     */
    private String playerName;
    /**
     * name of the strategy
     */
    private String strategy;

}
