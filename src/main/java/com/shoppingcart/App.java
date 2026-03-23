package com.shoppingcart;

public final class App {
    private App() {
        // Utility class
    }

    public static String greeting() {
        return "Shopping Cart Maven project is ready.";
    }

    public static void main(String[] args) {
        System.out.println(greeting());
    }
}

