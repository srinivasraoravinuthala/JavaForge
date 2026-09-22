package pkg14testing;

/*
 * testing1TestingOverview.java
 * ----------------------------
 * Testing fundamentals: why test, pyramid, and what this Maven module covers.
 *
 * Run: mvn -q exec:java -f pkg14testing/pom.xml
 * Test: mvn -q test -f pkg14testing/pom.xml
 */
public class testing1TestingOverview {

    public static void main(String[] args) {
        System.out.println("Testing pyramid:");
        System.out.println("  Unit (many)  — fast, isolated, JUnit + Mockito");
        System.out.println("  Integration  — DB/API with Testcontainers (SpringMastery)");
        System.out.println("  E2E (few)    — full system paths");

        System.out.println("\nThis module demonstrates:");
        System.out.println("  JUnit5BasicsTest       — assertions, lifecycle, @DisplayName");
        System.out.println("  MockitoDemoTest        — mocks, stubs, verify");
        System.out.println("  AssertJDemoTest        — fluent assertions");
        System.out.println("  ParameterizedTestDemo  — @ParameterizedTest");
        System.out.println("  TddCalculatorTest      — red-green-refactor TDD");
        System.out.println("  PropertyBasedDemoTest  — jqwik property-based");

        System.out.println("\nRun all: mvn test -f pkg14testing/pom.xml");
    }
}
