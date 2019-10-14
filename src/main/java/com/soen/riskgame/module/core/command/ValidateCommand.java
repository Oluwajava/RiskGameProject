package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapValidator;

public class ValidateCommand implements Command {

    /**
     * Contains the Map Data
     */
    private MapData mapData;

    private ValidateMapListener validateMapListener;

    public ValidateCommand(MapData mapData, ValidateMapListener validateMapListener) {
        this.mapData = mapData;
        this.validateMapListener = validateMapListener;
    }

    @Override
    public void execute() {
        validateMapListener.onMapValidated(MapValidator.isGraphConnected(mapData));
    }

    public interface ValidateMapListener {

        void onMapValidated(Boolean isMapValid);

    }
}
