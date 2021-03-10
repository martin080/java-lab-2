package com.martin.parser;

import java.util.Collection;
import java.util.Collections;

public interface Calculator {

    double calculate(String expression) throws Exception;

    /**
     * @return collection of operations supported by calculator
     */
    default Collection<Character> getSupportedOperations() {
        return Collections.emptySet();
    }
}
