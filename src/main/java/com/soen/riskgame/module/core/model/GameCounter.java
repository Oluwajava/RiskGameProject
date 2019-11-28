package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCounter {

    private String mapFile;

    private int numberOfGames = 0;

    public void increment() {
        numberOfGames++;
    }
}
