package pkg13libs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/*
 * libs7Annotations.java
 * ---------------------
 * Custom annotations + processing them with reflection (mini test framework).
 *
 * DEFINITION:
 *   An annotation is metadata you attach to code. With RetentionPolicy.RUNTIME
 *   it is readable via reflection, which is how JUnit (@Test), Spring (@Component),
 *   and JPA (@Entity) discover and wire your classes.
 *
 * KEY POINTS:
 *   - @Retention controls visibility (SOURCE / CLASS / RUNTIME).
 *   - @Target restricts where it can be placed (METHOD, FIELD, TYPE, ...).
 *   - Annotations can have elements (parameters) with defaults.
 *   - Read them at runtime with getAnnotation()/isAnnotationPresent().
 */
public class libs7Annotations {

    // Define a custom runtime annotation with an element
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Test {
        String name() default "";
    }

    // A "test class" using our annotation
    static class CalculatorTests {
        @Test(name = "addition works")
        public void testAdd() { assertTrue(2 + 2 == 4); }

        @Test(name = "subtraction works")
        public void testSub() { assertTrue(5 - 3 == 2); }

        @Test  // intentionally failing to show the runner
        public void testBroken() { assertTrue(1 == 2); }

        public void notATest() { throw new RuntimeException("should never run"); }

        static void assertTrue(boolean cond) { if (!cond) throw new AssertionError("expected true"); }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Mini test runner (discovers @Test via reflection):\n");
        Object suite = new CalculatorTests();
        int pass = 0, fail = 0;

        for (Method m : CalculatorTests.class.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Test.class)) continue;     // only annotated methods
            Test meta = m.getAnnotation(Test.class);
            String label = meta.name().isEmpty() ? m.getName() : meta.name();
            try {
                m.invoke(suite);
                System.out.println("  PASS  " + label);
                pass++;
            } catch (Exception e) {
                System.out.println("  FAIL  " + label + "  (" + e.getCause() + ")");
                fail++;
            }
        }
        System.out.printf("%nResult: %d passed, %d failed%n", pass, fail);
    }
}
