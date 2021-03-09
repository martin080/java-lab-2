package com.martin.parser.tokenizer.token;

public class VariableToken implements Token<String>{

    private String name;

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
