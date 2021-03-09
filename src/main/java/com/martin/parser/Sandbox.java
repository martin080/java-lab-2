package com.martin.parser;

import com.martin.parser.tokenizer.Tokenizer;
import com.martin.parser.tokenizer.token.Token;
import com.martin.parser.tokenizer.token.TokenType;
import com.martin.parser.tokenizer.token.VariableToken;

import java.util.*;


public class Sandbox {
    private static String wrap(Object obj){
        return "("  + obj + ")";
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        VariableCalculator calculator = new VariableCalculator();

        while (run){
            System.out.printf("1 - enter math expression%n2 - exit%n>> ");
            int op;
            try {
                op = scanner.nextInt(); scanner.nextLine();
            }  catch (Exception e){
                System.out.println("Invalid option"); scanner.nextLine();
                continue;
            }

            switch(op){
                case 1: {
                    String expression = scanner.nextLine();
                    Tokenizer tokenizer = new Tokenizer(expression, calculator.getSupportedOperations());
                    Map<String, Double> variables = new HashMap<>();

                    while (tokenizer.hasNext()){
                        Token<?> token = tokenizer.next();
                        if (token.getType() == TokenType.VARIABLE && !variables.containsKey(token.getValue())){

                            System.out.printf("Enter variable '" + token.getValue() + "'%n>> ");
                            double variable = 0d;

                            while (true){
                                try {
                                    variable = scanner.nextDouble(); scanner.nextLine();
                                    break;
                                } catch (Exception e){
                                    System.out.println("Mismatch type, try again");
                                }
                            }

                            variables.put(((VariableToken) token).getValue(), variable);
                        }
                    }

                    calculator.setVariables(variables);
                    double result = 0d;
                    try {
                        result = calculator.calculate(expression);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(result);
                } break;
                case 2:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
