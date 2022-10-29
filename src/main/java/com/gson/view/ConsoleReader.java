package com.gson.view;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleReader {

    private static final Scanner SCANNER = new Scanner(System.in);

    public double readDecimalValue() {
        while (true) {
            String input = SCANNER.nextLine();
            try {
                Double value = Double.parseDouble(input);
                if (value < 0) {
                    throw new IllegalArgumentException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Wrong input!");
            } catch (IllegalArgumentException e) {
                System.err.println("Value can't be less than 0");
            }
        }
    }

    public long readNumericValue() {
        while (true) {
            String value = SCANNER.nextLine();
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                System.err.println("Wrong input!");
            }
        }
    }

    public String readTextValue() {
        while (true) {
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
        }
    }

    public String readUserName() {
        while (true) {
            String value = SCANNER.nextLine();
            if (value.matches("^[a-zA-Z\\-.']*$")) {
                return value;
            }
        }
    }

    public void closeScanner() {
        SCANNER.close();
    }
}
