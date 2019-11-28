package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

/**
 *  * Class uses command pattern to abstract Attack command
 *  to Game play. This class can be called by either the GUI or the command line to perform
 *  @see com.soen.riskgame.module.core.interfaces.Command
 *  @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 *  * @author Mayokun
 */
public class DefendCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * defender dice number
     */
    private int num;

    /**
     * constructor of the class
     * @param mapData Contains the Map Data
     * @param num defender  number of dice number
     */
    public DefendCommand(MapData mapData, int num) {
        this.mapData = mapData;
        this.num = num;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        mapData.defend(num);
    }

}
