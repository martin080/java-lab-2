package com.martin.parser.tokenizer.token;

public class DoubleToken implements Token<Double>{

    private final Double value;

    public DoubleToken(Double value){
        this.value = value;
    }

    @Override
    public TokenType getType() {
        return TokenType.OPERAND;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DoubleToken{" +
                "value=" + value +
                '}';
    }
}
