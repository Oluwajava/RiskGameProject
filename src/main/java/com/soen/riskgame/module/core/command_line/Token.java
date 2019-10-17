package com.soen.riskgame.module.core.command_line;

public class Token {

    public final TokenType tokenType;

    public final String content;

    public Token(TokenType tokenType, String content) {
        this.tokenType = tokenType;
        this.content = content;
    }
    public String toString() {
        if(tokenType == TokenType.ATOM) {
            return "ATOM<" + content + ">";
        }
        return tokenType.toString();
    }

    public String getContent() {
        return content;
    }
}
