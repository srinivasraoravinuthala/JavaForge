package pkg1core;

/*
 * core14InterfacesDemo.java
 * -------------------
 * Interfaces: contracts, default & static methods, multiple inheritance of type,
 * private interface methods, and functional interfaces.
 *
 * EXPLANATION:
 *  - An interface defines a contract (abstract methods).
 *  - `default` methods provide an implementation (added in Java 8) so interfaces
 *    can evolve without breaking implementers.
 *  - A class can implement MANY interfaces (multiple inheritance of TYPE).
 */
public class core14InterfacesDemo {

    interface Greeter {
        String name();                                  // abstract
        default String greet() {                        // default method
            return prefix() + name();                   // can call private helper
        }
        private String prefix() { return "Hello, "; }   // private interface method
        static Greeter of(String n) { return () -> n; } // static factory; lambda impl
    }

    interface Swimmer { default String act(){ return "swims"; } }
    interface Flyer   { default String fly(){ return "flies"; } }

    // Multiple interfaces
    static class Duck implements Swimmer, Flyer {
        String describe() { return "Duck " + act() + " and " + fly(); }
    }

    public static void main(String[] args) {
        Greeter g = Greeter.of("World");        // implemented via lambda (functional)
        System.out.println(g.greet());

        System.out.println(new Duck().describe());

        // Interfaces enable programming to an abstraction
        Greeter custom = () -> "Custom";
        System.out.println(custom.greet());
    }
}
