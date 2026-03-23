package com.shoppingcart;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale locale = chooseLocale(scanner);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        CartCalculator calculator = new CartCalculator();
        double runningTotal = 0;

        int itemCount = readPositiveInt(scanner, bundle.getString("prompt.itemCount"), bundle);

        for (int i = 1; i <= itemCount; i++) {
            System.out.println(MessageFormat.format(bundle.getString("label.itemIndex"), i));

            double price = readNonNegativeDouble(scanner, bundle.getString("prompt.itemPrice"), bundle);
            int quantity = readPositiveInt(scanner, bundle.getString("prompt.itemQuantity"), bundle);

            double itemTotal = calculator.calculateItemTotal(price, quantity);
            runningTotal = calculator.calculateRunningTotal(runningTotal, itemTotal);

            System.out.println(MessageFormat.format(bundle.getString("message.itemTotal"), itemTotal));
            System.out.println(MessageFormat.format(bundle.getString("message.runningTotal"), runningTotal));
        }

        System.out.println(MessageFormat.format(bundle.getString("message.finalTotal"), runningTotal));
    }

    private static Locale chooseLocale(Scanner scanner) {
        while (true) {
            System.out.println("Choose language / Valitse kieli / V\u00e4lj spr\u00e5k / \u8a00\u8a9e\u3092\u9078\u629e:");
            System.out.println("1. English");
            System.out.println("2. Suomi");
            System.out.println("3. Svenska");
            System.out.println("4. \u65e5\u672c\u8a9e");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return Locale.ENGLISH;
                case "2":
                    return Locale.forLanguageTag("fi");
                case "3":
                    return Locale.forLanguageTag("sv");
                case "4":
                    return Locale.JAPANESE;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private static double readNonNegativeDouble(Scanner scanner, String prompt, ResourceBundle bundle) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println(bundle.getString("error.invalidNumber"));
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(bundle.getString("error.invalidNumber"));
            }
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt, ResourceBundle bundle) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println(bundle.getString("error.invalidNumber"));
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(bundle.getString("error.invalidNumber"));
            }
        }

    }
}
