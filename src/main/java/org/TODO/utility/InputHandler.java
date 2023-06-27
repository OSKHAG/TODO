package org.TODO.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getStringInput() {
        String input = "";
        boolean validInput = false;

        while (!validInput) {
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a non-empty string: ");
            } else {
                validInput = true;
            }
        }

        return input;
    }

    public int getIntegerInput() {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer: ");
            }
        }

        return input;
    }
}
