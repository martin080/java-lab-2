package com.martin.parser.tokenizer.token;

/**
 * Token for storing variable name, but not the value
 */
public class VariableToken implements Token<String>{

    private final String name;

    public VariableToken(String name){
        this.name = name;
    }

    @Override
    public TokenType getType() {
        return TokenType.VARIABLE;
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public String toString() {
        return "VariableToken{" +
                "name='" + name + '\'' +
                '}';
    }
}
