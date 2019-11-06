package com.soen.riskgame.module.core.command_line;

import lombok.Data;

import java.util.List;

/**
 * Class TokenCommand for processing various commands
 * @author hitan
 *
 */
@Data
public class TokenCommand {

    /**
     * object of TokenType
     */
    private TokenType actionCommand;

    /**
     * list of values of type String
     */
    private List<String> values;

}