package com.soen.riskgame.module.core.command_line;

import lombok.Getter;

/**
 * Enum TokenType defining all the values of Token Type
 * @author hitan
 *
 */
public enum TokenType {

    ATOM(1, CommandType.PARAM), EDIT_CONTINENT(1, CommandType.COMMAND),
    ADD(2, CommandType.ACTION), REMOVE(1, CommandType.ACTION), EDIT_COUNTRY(1, CommandType.COMMAND),
    EDIT_NEIGHBOUR(1, CommandType.COMMAND), SHOWMAP(0, CommandType.COMMAND), SAVE_MAP(1, CommandType.COMMAND),
    EDIT_MAP(1, CommandType.COMMAND), VALIDATEMAP(0, CommandType.COMMAND), LOAD_MAP(1, CommandType.COMMAND),
    GAME_PLAYER(1, CommandType.COMMAND), POPULATE_COUNTRIES(0, CommandType.COMMAND), PLACE_ARMY(1, CommandType.COMMAND),
    PLACE_ALL(0, CommandType.COMMAND), REINFORCE(2, CommandType.COMMAND), FORTIFY(3, CommandType.COMMAND),
    NONE(0, CommandType.COMMAND), ATTACK(4, CommandType.COMMAND), DEFEND(1, CommandType.COMMAND),
    ATTACK_MOVE(1, CommandType.COMMAND), ALL_OUT(0, CommandType.ACTION), NO_ATTACK(0, CommandType.ACTION);

    /**
     * number of arguments
     */
    @Getter
    private int numberOfArugments;

    /**
     * Object of the type CommandType
     */
    @Getter
    private CommandType commandType;

    /**
     * parameterized constructor
     * @param numberOfArugments number of arguments
     * @param commandType type of command
     */
    TokenType(int numberOfArugments, CommandType commandType) {
        this.numberOfArugments = numberOfArugments;
        this.commandType = commandType;
    }
}