package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.constants.CommandConstant;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for class Lexer
 */
class LexerTest {

    /**
     * Test case for method lex
     */
    @Test
    public void testLex() {
        Lexer lexer = new Lexer();
        String tested = "editcountry -add Finland 3 -remove Japan";
        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenType.EDIT_COUNTRY, CommandConstant.EDIT_COUNTRY));
        expected.add(new Token(TokenType.ADD, ActionConstant.ADD));
        expected.add(new Token(TokenType.ATOM, "Finland"));
        expected.add(new Token(TokenType.ATOM, "3"));
        expected.add(new Token(TokenType.REMOVE, ActionConstant.REMOVE));
        expected.add(new Token(TokenType.ATOM, "Japan"));

        assertEquals(expected, Lexer.lex(tested));
    }
}