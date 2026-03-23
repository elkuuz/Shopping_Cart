package com.shoppingcart;

public class CartCalculator {
    public double calculateItemTotal(double price, int quantity) {
        validateNonNegative(price, "price");
        validateNonNegative(quantity, "quantity");
        return price * quantity;
    }

    public double calculateRunningTotal(double currentTotal, double itemTotal) {
        validateNonNegative(currentTotal, "currentTotal");
        validateNonNegative(itemTotal, "itemTotal");
        return currentTotal + itemTotal;
    }

    private void validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be non-negative");
        }
    }
}