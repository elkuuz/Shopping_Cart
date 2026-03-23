package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CartCalculatorTest {
    private final CartCalculator calculator = new CartCalculator();

    @Test
    void calculateItemTotalReturnsPriceTimesQuantity() {
        assertEquals(59.97, calculator.calculateItemTotal(19.99, 3), 0.000001);
    }

    @Test
    void calculateRunningTotalAddsValues() {
        assertEquals(35.5, calculator.calculateRunningTotal(20.0, 15.5), 0.000001);
    }

    @Test
    void calculateItemTotalRejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateItemTotal(-1.0, 2));
    }

    @Test
    void calculateItemTotalRejectsNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateItemTotal(4.0, -2));
    }

    @Test
    void calculateRunningTotalRejectsNegativeCurrentTotal() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateRunningTotal(-1.0, 2.0));
    }

    @Test
    void calculateRunningTotalRejectsNegativeItemTotal() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateRunningTotal(5.0, -2.0));
    }
}

