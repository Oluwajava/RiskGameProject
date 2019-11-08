package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.ActionConstant;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapDataUtil;

/**
 * * Class uses command pattern to abstract Attack command
 * to Game play. This class can be called by either the GUI or the command line to perform
 *
 * @see com.soen.riskgame.module.core.interfaces.Command
 * @see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * * @author John
 */
public class AttackCommand implements Command {
    /**
     * Contains the Map Data
     */
    private MapData mapData;
    /**
     * the country from which attack is done
     */
    private String fromCountry;
    /**
     * the defender country
     */
    private String toCountry;
    /**
     * number of dice rolled
     */
    private int num;
    /**
     * if any extra commands
     */
    private String extendedAction;

    /**
     * constructor of the class
     *
     * @param mapData     Contains the Map Data
     * @param fromCountry the country from which attack is done
     * @param toCountry   the defender country
     * @param num         number of dice rolled
     */
    public AttackCommand(MapData mapData, String fromCountry, String toCountry, int num) {
        this.mapData = mapData;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.num = num;
    }

    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        if (extendedAction != null && extendedAction.equals(ActionConstant.ALL_OUT)) {
            mapData.attack(fromCountry, toCountry);
        } else if (extendedAction != null && extendedAction.equals(ActionConstant.NO_ATTACK)) {
            mapData.attackNone();
        } else {
            mapData.attack(fromCountry, toCountry, num);
        }
    }

    /**
     * method to check the extended command
     * and implement it
     *
     * @param extendedAction if any extra commands -allout or -none
     */
    public void setExtendedAction(String extendedAction) {
        this.extendedAction = extendedAction;
    }
}
