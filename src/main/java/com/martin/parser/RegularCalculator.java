package com.martin.parser;

import com.martin.parser.tokenizer.Tokenizer;
import com.martin.parser.tokenizer.token.OperationToken;
import com.martin.parser.tokenizer.token.Token;
import com.martin.parser.tokenizer.token.TokenType;

import java.util.*;

public class RegularCalculator implements Calculator{

    public static final Collection<Character> supportedOperations = Set.of('(', ')', '+', '-', '/', '*', '^');

    private int getPriority(char operation) {
        switch (operation) {
            case '(':
                return -1;
            case '*': case '/': case '^':
                return 1;
            case '+': case '-':
                return 2;
            default:
                throw new RuntimeException("Bad operation");
        }
    }

    @Override
    public double calculate(String expression) throws Exception {

        expression = "(" + expression.trim() + ")";

        Stack<Character> operations = new Stack<>();
        Stack<Double> operands = new Stack<>();

        Tokenizer tokenizer = new Tokenizer(expression, RegularCalculator.supportedOperations);
        Token<?> token, prevToken = null;

        while (tokenizer.hasNext()){
            token = tokenizer.next();

            // process signed number in brackets
            if (prevToken != null && prevToken.getType() == TokenType.OPERATION && token.getType() == TokenType.OPERATION){
                char prevOperation = ((OperationToken) prevToken).getValue();
                char operation = ((OperationToken) token).getValue();

                if (prevOperation == '(' && (operation == '+' || operation == '-')){
                    operands.push(0d);
                }
            }

            if (token.getType() == TokenType.OPERAND){
                operands.push((Double) token.getValue());
            } else if (token.getType() == TokenType.OPERATION){
                char operation = (char) token.getValue();
                processOperation(operation, operands, operations);
            } else
                throw new Exception("Type of token '" + token.getType() + "' is not supported");

            prevToken = token;
        }

        if (operands.size() > 1 || operations.size() > 0)
            throw new Exception("Unable to parse expression");

        return operands.peek();
    }

    public void processOperation(char operation, Stack<Double> operands, Stack<Character> operations) throws Exception {
        if (operation == ')'){
            while (!operations.empty() && operations.peek() != '('){
                popOperation(operands, operations);
            }

            if (operations.empty()){
                throw new Exception("'(' was expected");
            }

            operations.pop();
        } else {
            while (canPop(operation, operations)) {
                popOperation(operands, operations);
            }

            operations.push(operation);
        }
    }

    public void popOperation(Stack<Double> operands, Stack<Character> operations){
        double b = operands.pop();
        double a = operands.pop();

        switch (operations.pop()){
            case '+':
                operands.push(a + b);
                break;
            case '-':
                operands.push(a - b);
                break;
            case '*':
                operands.push(a * b);
                break;
            case '/':
                operands.push(a / b);
                break;
            case '^':
                operands.push(Math.pow(a, b));
                break;
        }
    }

    public boolean canPop(char operation, Stack<Character> operations){
        if (operations.isEmpty())
            return false;

        int firstPriority = getPriority(operation);
        int secondPriority = getPriority(operations.peek());

        return secondPriority >= 0 && firstPriority >= secondPriority;
    }

    @Override
    public Collection<Character> getSupportedOperations() {
        return supportedOperations;
    }
}
