package com.martin.parser.tokenizer.token;

public class OperationToken implements Token<Character>{

    private final Character operation;

    public OperationToken(Character operation){
        this.operation = operation;
    }

    @Override
    public TokenType getType() {
        return TokenType.OPERATION;
    }

    @Override
    public Character getValue() {
        return operation;
    }

    @Override
    public String toString() {
        return "OperationToken{" +
                "operation=" + operation +
                '}';
    }
}
