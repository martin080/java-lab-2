package com.martin.parser.tokenizer;

import com.martin.parser.RegularCalculator;
import com.martin.parser.tokenizer.token.DoubleToken;
import com.martin.parser.tokenizer.token.OperationToken;
import com.martin.parser.tokenizer.token.Token;

import java.util.Collection;

public class Tokenizer {

    private int tokenStart = 0;
    private final String expression;
    private final Collection<Character> operations;

    public Tokenizer(String expression, Collection<Character> operations){
        this.expression = expression;
        this.operations = operations;
    }

    public Token<?> next(){
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
        } else
            throw new RuntimeException("Unexpected symbol '" + startSymbol + "' at " + tokenStart);

        tokenStart = tokenEnd + 1;

        return token;
    }

    public boolean hasNext(){
        return tokenStart < expression.length();
    }

    private int skipWhitespaces(String expr, int start){
        while (start < expr.length() && Character.isWhitespace(expr.charAt(start)))
            start++;

        return start;
    }

    private int getDoubleEnd(String expr, int start){
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

        return end - 1;
    }

    private boolean isOperation(char symbol){
        return operations.contains(symbol);
    }

    private boolean isDigit(char symbol){
        return Character.isDigit(symbol);
    }
}
