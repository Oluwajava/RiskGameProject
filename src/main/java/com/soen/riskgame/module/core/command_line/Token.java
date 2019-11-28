package com.soen.riskgame.module.core.command_line;

/**
 * Class Token is the entity for Tokens
 * @author hitansh
 *
 */
public class Token {

    /**
     * type of token
     */
    public final TokenType tokenType;

    /**
     * content of token
     */
    public final String content;

    /**
     * parameterized constructor
     * @param tokenType type of the token
     * @param content content
     */
    public Token(TokenType tokenType, String content) {
        this.tokenType = tokenType;
        this.content = content;
    }
    /**
     * method toString to print the object
     */
    public String toString() {
        if(tokenType == TokenType.ATOM) {
            return "ATOM<" + content + ">";
        }
        return tokenType.toString();
    }

    /**
     * Getter for Content
     * @return content
     */
    public String getContent() {
        return content;
    }
}