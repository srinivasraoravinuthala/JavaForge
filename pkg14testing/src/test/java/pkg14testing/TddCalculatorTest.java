package pkg14testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD example: tests written first, then Calculator (already implemented).
 * Red -> Green -> Refactor cycle.
 */
class TddCalculatorTest {

    @Test void addTwoNumbers() { assertEquals(5, new Calculator().add(2, 3)); }
    @Test void divideNormally() { assertEquals(2, new Calculator().divide(6, 3)); }
    @Test void divideByZero() {
        assertThrows(IllegalArgumentException.class, () -> new Calculator().divide(1, 0));
    }
}
