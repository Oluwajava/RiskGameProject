package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.constants.ActionConstant;
import com.soen.riskgame.module.core.constants.CommandConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for class Lexer
 */
class LexerTest {

    /**
     * lexer object intantiation
     */
    Lexer lexer = new Lexer();

    /**
     * Holds the command to be tested
     */
    String tested;

    /**
     * Holds the expected list of tokens
     */
    List<Token> expected = new ArrayList<>();

    /**
     * This method clears expected before each test case
     */
    @BeforeEach
    public void beforeEach() {
        expected.clear();
    }

    /**
     * 1st test case for method lex
     */
    @Test
    public void testLex1() {
        tested = "editcountry -add Finland 3 -remove Japan loadmap my_map";

        expected.add(new Token(TokenType.EDIT_COUNTRY, CommandConstant.EDIT_COUNTRY));
        expected.add(new Token(TokenType.ADD, ActionConstant.ADD));
        expected.add(new Token(TokenType.ATOM, "Finland"));
        expected.add(new Token(TokenType.ATOM, "3"));
        expected.add(new Token(TokenType.REMOVE, ActionConstant.REMOVE));
        expected.add(new Token(TokenType.ATOM, "Japan"));
        expected.add(new Token(TokenType.LOAD_MAP, CommandConstant.LOAD_MAP));
        expected.add(new Token(TokenType.ATOM, "my_map"));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 2nd test case for method lex
     */
    @Test
    public void testLex2() {
        tested = "editcontinent -add Africa 8 editneighbor -remove France Belgium showmap";

        expected.add(new Token(TokenType.EDIT_CONTINENT, CommandConstant.EDIT_CONTINENT));
        expected.add(new Token(TokenType.ADD, ActionConstant.ADD));
        expected.add(new Token(TokenType.ATOM, "Africa"));
        expected.add(new Token(TokenType.ATOM, "8"));
        expected.add(new Token(TokenType.EDIT_NEIGHBOUR, CommandConstant.EDIT_NEIGHBOR));
        expected.add(new Token(TokenType.REMOVE, ActionConstant.REMOVE));
        expected.add(new Token(TokenType.ATOM, "France"));
        expected.add(new Token(TokenType.ATOM, "Belgium"));
        expected.add(new Token(TokenType.SHOWMAP, CommandConstant.SHOW_MAP));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 3rd test case for method lex
     */
    @Test
    public void testLex3() {
        tested = "savemap my_map validatemap editmap my_map";

        expected.add(new Token(TokenType.SAVE_MAP, CommandConstant.SAVE_MAP));
        expected.add(new Token(TokenType.ATOM, "my_map"));
        expected.add(new Token(TokenType.VALIDATEMAP, CommandConstant.VALIDATE_MAP));
        expected.add(new Token(TokenType.EDIT_MAP, CommandConstant.EDIT_MAP));
        expected.add(new Token(TokenType.ATOM, "my_map"));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 4th test case for method lex
     */
    @Test
    public void testLex4() {
        tested = "gameplayer -add Rashford populatecountries placeall";

        expected.add(new Token(TokenType.GAME_PLAYER, CommandConstant.GAME_PLAYER));
        expected.add(new Token(TokenType.ADD, ActionConstant.ADD));
        expected.add(new Token(TokenType.ATOM, "Rashford"));
        expected.add(new Token(TokenType.POPULATE_COUNTRIES, CommandConstant.POPULATE_COUNTRIES));
        expected.add(new Token(TokenType.PLACE_ALL, CommandConstant.PLACE_ALL));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 5th test case for method lex
     */
    @Test
    public void testLex5() {
        tested = "placearmy Iran";

        expected.add(new Token(TokenType.PLACE_ARMY, CommandConstant.PLACE_ARMY));
        expected.add(new Token(TokenType.ATOM, "Iran"));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 6th test case for method lex
     */
    @Test
    public void testLex6() {
        tested = "reinforce Congo 9";

        expected.add(new Token(TokenType.REINFORCE, CommandConstant.REINFORCE));
        expected.add(new Token(TokenType.ATOM, "Congo"));
        expected.add(new Token(TokenType.ATOM, "9"));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 7th test case for method lex
     */
    @Test
    public void testLex7() {
        tested = "fortify Argentina Peru 3";

        expected.add(new Token(TokenType.FORTIFY, CommandConstant.FORTIFY));
        expected.add(new Token(TokenType.ATOM, "Argentina"));
        expected.add(new Token(TokenType.ATOM, "Peru"));
        expected.add(new Token(TokenType.ATOM, "3"));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }

    /**
     * 8th test case for method lex
     */
    @Test
    public void testLex8() {
        tested = "fortify none";

        expected.add(new Token(TokenType.FORTIFY, CommandConstant.FORTIFY));
        expected.add(new Token(TokenType.NONE, ActionConstant.NONE));

        assertEquals(expected.toString(), lexer.lex(tested).toString());
    }
}