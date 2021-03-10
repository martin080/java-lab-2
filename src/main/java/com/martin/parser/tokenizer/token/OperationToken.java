package com.martin.parser.tokenizer.token;

/**
 * Token for storing operation symbol (hence it stores only 1 character operations)
 */
public class OperationToken implements Token<Character>{
    private class Some {
        Character character = operation;
    }

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
