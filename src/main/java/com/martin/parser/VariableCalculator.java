package com.martin.parser;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Class for evaluating value of math expression with variable support (1 symbol preferable)
 */
public class VariableCalculator extends RegularCalculator{

    /**
     * Map stores variable name and its value
     */
    private Map<String, Double> variables;

    public VariableCalculator(Map<String, Double> variables) {
        this.variables = variables;
    }

    public VariableCalculator(){
        this.variables = Collections.emptyMap();
    }

    /**
     * Method for calculating math expression
     * @param expression math expression
     * @return result of math expression
     * @throws Exception when parsing error occurs or some variable is not specified
     */
    @Override
    public double calculate(String expression) throws Exception {
        for (String key: variables.keySet()){
            String pattern = "\\b" + key + "\\b";
            expression = expression.replaceAll(pattern, wrap(variables.get(key)));
        }

        return super.calculate(expression);
    }

    /**
     * Method for getting operations supported by calculator
     * @return collection of operation symbols
     */
    @Override
    public Collection<Character> getSupportedOperations() {
        return super.getSupportedOperations();
    }

    /**
     * Method for setting variables to use in future calculations
     * @param variables map of variables
     */
    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }

    private static String wrap(Object obj){
        return "("  + obj + ")";
    }
}
