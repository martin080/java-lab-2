package com.martin.parser;

import com.martin.parser.tokenizer.token.VariableToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class VariableCalculator implements Calculator{

    private final Calculator calculator = new RegularCalculator();

    private Map<String, Double> variables;

    public VariableCalculator(Map<String, Double> variables) {
        this.variables = variables;
    }

    public VariableCalculator(){
        this.variables = Collections.emptyMap();
    }

    @Override
    public double calculate(String expression) throws Exception {
        for (String key: variables.keySet()){
            expression = expression.replaceAll(key, wrap(variables.get(key)));
        }

        return calculator.calculate(expression);
    }

    @Override
    public Collection<Character> getSupportedOperations() {
        return calculator.getSupportedOperations();
    }

    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }

    private static String wrap(Object obj){
        return "("  + obj + ")";
    }
}
