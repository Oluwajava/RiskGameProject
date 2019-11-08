package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.ActionConstant;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;

public class ExchangeCardCommand implements Command {

    private MapData mapData;

    private int num1;

    private int num2;

    private int num3;

    private String extendedAction;

    public ExchangeCardCommand(MapData mapData, int num1, int num2, int num3) {
        this.mapData = mapData;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
    @Override
    public void execute() {
        if (extendedAction != null && extendedAction.equals(ActionConstant.EXCHANGE_NONE)) {
            mapData.exchangeNone();
        } else {
            mapData.exchange(num1, num2, num3);
        }
    }

    public void setExtendedAction(String extendedAction) {
        this.extendedAction = extendedAction;
    }
}
