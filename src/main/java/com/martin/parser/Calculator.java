package com.martin.parser;

import java.util.Collection;
import java.util.Collections;

public interface Calculator {

    double calculate(String expression) throws Exception;

    default Collection<Character> getSupportedOperations() {
        return Collections.emptySet();
    }

}
