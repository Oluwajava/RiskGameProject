package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.constants.ActionConstant;
import com.soen.riskgame.module.core.constants.CommandConstant;
import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.MapData;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Lexer {

    public static List<Token> lex(String input) {
        List<Token> result = new ArrayList<>();
        Arrays.asList(input.split(MapDelimiters.SPACE_DELIMITER)).forEach(s -> {
            if (StringUtils.isNotBlank(s)) {
                switch (s) {
                    case CommandConstant.EDIT_CONTINENT:
                        result.add(new Token(TokenType.EDIT_CONTINENT, CommandConstant.EDIT_CONTINENT));
                        break;
                    case CommandConstant.EDIT_COUNTRY:
                        result.add(new Token(TokenType.EDIT_COUNTRY, CommandConstant.EDIT_CONTINENT));
                        break;
                    case CommandConstant.EDIT_NEIGHBOR:
                        result.add(new Token(TokenType.EDIT_NEIGHBOUR, CommandConstant.EDIT_NEIGHBOR));
                    case ActionConstant.ADD:
                        result.add(new Token(TokenType.ADD, ActionConstant.ADD));
                        break;
                    case ActionConstant.REMOVE:
                        result.add(new Token(TokenType.REMOVE, ActionConstant.REMOVE));
                        break;
                    case CommandConstant.SHOW_MAP:
                        result.add(new Token(TokenType.SHOWMAP, CommandConstant.SHOW_MAP));
                        break;
                    default:
                        result.add(new Token(TokenType.ATOM, s));
                        break;
                }
            }

        });
        return result;
    }
}
