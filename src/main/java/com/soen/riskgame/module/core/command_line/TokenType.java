package com.soen.riskgame.module.core.command_line;

import lombok.Getter;

/**
 * Enum TokenType consists of list of the commans that are to be used in the Game
 */
public enum TokenType {

    ATOM(1, CommandType.PARAM), EDIT_CONTINENT(1, CommandType.COMMAND),
    ADD(2, CommandType.ACTION), REMOVE(1, CommandType.ACTION), EDIT_COUNTRY(1, CommandType.COMMAND),
    EDIT_NEIGHBOUR(1, CommandType.COMMAND), SHOWMAP(0, CommandType.COMMAND);

    @Getter
    private int numberOfArugments;

    @Getter
    private CommandType commandType;

    /**
     * Constructor for the Enum
     * @param numberOfArugments
     * @param commandType
     */
    TokenType(int numberOfArugments, CommandType commandType) {
        this.numberOfArugments = numberOfArugments;
        this.commandType = commandType;
    }
}
