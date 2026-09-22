package pkg1core;

/*
 * core2Variables.java
 * --------------
 * Declaring and using variables: local, instance, static, final, and `var`.
 *
 * EXPLANATION:
 *  - Local variables live on the stack and must be initialized before use.
 *  - Instance variables belong to an object (one per instance).
 *  - Static (class) variables are shared across all instances.
 *  - `final` makes a variable a constant (cannot be reassigned).
 *  - `var` (Java 10+) infers the type for LOCAL variables only.
 */
public class core2Variables {

    static int instanceCounter = 0;   // static: shared by all objects
    int id;                            // instance: one per object
    final String label;                // final: assigned once (in constructor)

    core2Variables(String label) {
        this.label = label;            // `this` distinguishes field from param
        this.id = ++instanceCounter;   // shared counter increments per object
    }

    public static void main(String[] args) {
        // Local variables with explicit types
        int age = 30;
        double price = 19.99;
        boolean active = true;
        char grade = 'A';

        // `var` infers the type from the right-hand side (still statically typed)
        var message = "var infers String";
        var pi = 3.14159;              // inferred as double

        final int MAX = 100;           // constant; reassigning is a compile error

        System.out.println("age=" + age + ", price=" + price + ", active=" + active + ", grade=" + grade);
        System.out.println(message + " | pi=" + pi + " | MAX=" + MAX);

        // Instance vs static demonstration
        core2Variables a = new core2Variables("first");
        core2Variables b = new core2Variables("second");
        System.out.println(a.label + " -> id " + a.id);
        System.out.println(b.label + " -> id " + b.id);
        System.out.println("Total created (static counter): " + instanceCounter);
    }
}
