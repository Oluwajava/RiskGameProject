package com.soen.riskgame.module.core.command_line;

import lombok.Data;

import java.util.List;

@Data
/**
 * Class represent the tokenCommand
 */
public class TokenCommand {

    private TokenType actionCommand;

    private List<String> values;

}
