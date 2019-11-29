package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.ActionConstant;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
/**
 *  Class uses command pattern to abstract Attack command
 *  to Game play. This class can be called by either the GUI or the command line to perform
 *  @see com.soen.riskgame.module.core.interfaces.Command
 *  @see <a href="https://refactoring.guru/design-patterns/command"> Command Pattern Tutorial</a>
 *  @author Hitansh
 */
public class ExchangeCardCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * type 1 card
     */
    private int num1;
    /**
     * type 2 card
     */
    private int num2;
    /**
     * type 3 card
     */
    private int num3;
    /**
     * String extra action
     */
    private String extendedAction;

    /**
     * Constructor of th class
     * @param mapData data in map
     * @param num1 card type
     * @param num2 card type
     * @param num3 card type
     */
    public ExchangeCardCommand(MapData mapData, int num1, int num2, int num3) {
        this.mapData = mapData;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        if (extendedAction != null && extendedAction.equals(ActionConstant.EXCHANGE_NONE)) {
            mapData.exchangeNone();
        } else {
            mapData.exchange(num1, num2, num3);
        }
    }

    /**
     * method for extra added command "-none"
     * @param extendedAction String extra action
     */
    public void setExtendedAction(String extendedAction) {
        this.extendedAction = extendedAction;
    }
}
