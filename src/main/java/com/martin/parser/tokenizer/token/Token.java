package com.martin.parser.tokenizer.token;

public interface Token<T> {
    TokenType getType();
    T getValue();
}
