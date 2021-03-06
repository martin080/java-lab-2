package com.martin.parser;

import com.martin.parser.tokenizer.Tokenizer;
import com.martin.parser.tokenizer.token.Token;
import com.martin.parser.tokenizer.token.TokenType;

import java.util.*;

public class RegularCalculator implements Calculator{

    private final Set<Character> operations;
    {
        operations = new HashSet<>();
        operations.addAll(Arrays.asList('(', ')', '+', '-', '/', '*', '^'));
    }

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
    public double calculate(String expression) {

        expression = "(" + expression.trim() + ")";

        Stack<Character> operations = new Stack<>();
        Stack<Double> operands = new Stack<>();

        Tokenizer tokenizer = new Tokenizer(expression, this.operations);

        while (tokenizer.hasNext()){

            Token<?> token = tokenizer.next();

            if (token.getType().equals(TokenType.OPERAND)){
                operands.push((Double) token.getValue());
            } else if (token.getType().equals(TokenType.OPERATION)){
                char operation = (char) token.getValue();
                processOperation(operation, operands, operations);
            }
        }

        if (operands.size() > 1 || operations.size() > 0)
            throw new RuntimeException("Unable to parse expression");

        return operands.peek();
    }

    public void processOperation(char operation, Stack<Double> operands, Stack<Character> operations){
        if (operation == ')'){
            while (!operations.empty() && operations.peek() != '('){
                popOperation(operands, operations);
            }

            if (operations.empty()){
                throw new RuntimeException("Unable to parse expression");
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
}
