package com.sob;

import java.util.Scanner;

public class Main {

    public static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 43;
    private static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        int num;

        while (true) {
            System.out.println("Enter number from Fibonacci sequence (0-43 range):");
            String input = reader.nextLine().trim();

            if (validateInput(input)) {
                num = Integer.parseInt(input);
                break;
            }
            System.err.println("Invalid input! Expression must be a number from range 0-43\n");
        }
        vote(num);
    }

    private static boolean validateInput(String input) {
        try {
            int number = Integer.parseInt(input);
            return number >= MIN_NUMBER && number <= MAX_NUMBER;
        } catch (Exception e) {
            return false;
        }
    }

    private static void vote(int num) {
        Fibonacci fib = new Fibonacci();
        fib.vote(num);
    }
}
