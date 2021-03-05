package com.martin.parser;

import java.util.Scanner;


public class Sandbox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        Calculator calculator = new RegularCalculator();

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
                case 1:
                    String expression = scanner.nextLine();
                    System.out.println(calculator.calculate(expression));
                    break;
                case 2:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
