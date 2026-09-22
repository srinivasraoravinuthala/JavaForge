package pkg14testing;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JUnit5BasicsTest {

    @BeforeAll static void beforeAll() { System.out.println("  [JUnit] @BeforeAll"); }
    @AfterAll static void afterAll() { System.out.println("  [JUnit] @AfterAll"); }

    @BeforeEach void beforeEach() { System.out.println("  [JUnit] @BeforeEach"); }
    @AfterEach void afterEach() { System.out.println("  [JUnit] @AfterEach"); }

    @Test
    @DisplayName("addition works")
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(4, calc.add(2, 2));
        assertTrue(calc.add(1, 1) > 1);
    }

    @Test
  void divideByZeroThrows() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
    }

    @Test
    @Disabled("example disabled test")
    void skipped() { }
}
