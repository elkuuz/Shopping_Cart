package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void greetingReturnsExpectedMessage() {
        assertEquals("Shopping Cart Maven project is ready.", App.greeting());
    }
}

