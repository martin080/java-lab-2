package com.martin.parser.tokenizer;

import com.martin.parser.RegularCalculator;
import com.martin.parser.tokenizer.token.DoubleToken;
import com.martin.parser.tokenizer.token.OperationToken;
import com.martin.parser.tokenizer.token.Token;
import com.martin.parser.tokenizer.token.VariableToken;

import java.util.Collection;

/**
 * Class for parsing math expression
 */
public class Tokenizer {

    private int tokenStart = 0;
    private final String expression;
    private final Collection<Character> operations;

    /**
     * Constructor
     * @param expression math expression to tokenize
     * @param operations symbols to treat like operations
     */
    public Tokenizer(String expression, Collection<Character> operations){
        this.expression = expression;
        this.operations = operations;
    }

    /**
     * Method for iterating through math expression elements
     * @return Token that can represent number (DoubleToken), operation (OperationToken) or variable (VariableToken)
     * @throws Exception if unexpected symbol occurred
     */
    public Token<?> next() throws Exception {
        if (tokenStart >= expression.length()){
            return null;
        }

        tokenStart = skipWhitespaces(expression, tokenStart);
        int tokenEnd = tokenStart;
        Token<?> token = null;

        char startSymbol = expression.charAt(tokenStart);

        if (isDigit(startSymbol)){
            tokenEnd = getDoubleEnd(expression, tokenStart);
            double tokenValue = Double.parseDouble(expression.substring(tokenStart, tokenEnd + 1));

            token = new DoubleToken(tokenValue);
        } else if (isOperation(startSymbol)){
            token = new OperationToken(startSymbol);
        } else if (isVariable(startSymbol)){
            tokenEnd = getVariableEnd(expression, tokenStart);
            String tokenValue = expression.substring(tokenStart, getVariableEnd(expression, tokenStart) + 1);

            token = new VariableToken(tokenValue);

        } else
            throw new Exception("Unexpected symbol '" + startSymbol + "' at " + tokenStart);

        tokenStart = tokenEnd + 1;

        return token;
    }

    /**
     * @return true if there are tokens, false otherwise
     */
    public boolean hasNext(){
        return tokenStart < expression.length();
    }

    /**
     * @param expr math expression
     * @param start start of character
     * @return first position with non-whitespace character starting with <code>start</code>
     */
    private int skipWhitespaces(String expr, int start){
        while (start < expr.length() && Character.isWhitespace(expr.charAt(start)))
            start++;

        return start;
    }

    /**
     *
     * @param expr math expression
     * @param start index of number start position
     * @return index that corresponds to the last symbol of double
     */
    private int getDoubleEnd(String expr, int start) throws Exception {
        boolean dot = false;
        int end = start;
        char element = expr.charAt(end);

        while (end < expr.length() && (Character.isDigit(element) || element == '.')){
            if (element == '.'){
                if (dot)
                    throw new RuntimeException();
                dot = true;
            }

            end++;
            element = end < expr.length() ? expr.charAt(end) : ' ';
        }

        if (start == end)
            throw new Exception("Unable to parse expression");

        return end - 1;
    }

    /**
     *
     * @param expr math expression
     * @param start starting index of variable
     * @return index that corresponds to the last symbol of variable element
     */
    private int getVariableEnd(String expr, int start) throws Exception {
        int end = start;

        while (end < expr.length() && expr.charAt(end) >= 'a' && expr.charAt(end) <= 'z'){
            end++;
        }

        if (end == start)
            throw new Exception();

        return end - 1;
    }

    /**
     * Check if symbol is operation
     * @param symbol character to check
     * @return true if symbol is operation, false otherwise
     */
    private boolean isOperation(char symbol){
        return operations.contains(symbol);
    }

    /**
     * Check if symbol is digit
     * @param symbol symbol to check
     * @return true if symbol is digit, false otherwise
     */
    private boolean isDigit(char symbol){
        return Character.isDigit(symbol);
    }

    /**
     * Check if symbol can be part of variable name
     * @param symbol symbol to check
     * @return true if symbol is lowercase letter is , false otherwise
     */
    private boolean isVariable(char symbol){
        return symbol >= 'a' && symbol <= 'z';
    }
}
