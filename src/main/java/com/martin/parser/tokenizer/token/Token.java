package com.martin.parser.tokenizer.token;

/**
 * Interface which represent element of math expression
 * @param <T> type of object that token stores
 */
public interface Token<T> {
    TokenType getType();
    T getValue();
}
