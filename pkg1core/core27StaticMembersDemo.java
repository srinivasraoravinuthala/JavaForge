package pkg1core;

/*
 * core27StaticMembersDemo.java
 * ----------------------------
 * Static fields, static methods, static blocks, and method hiding.
 *
 * EXPLANATION:
 *  - `static` belongs to the CLASS, not any single object.
 *  - Static methods cannot use `this` or access instance fields directly.
 *  - Static initializer blocks run once when the class is first loaded.
 *  - Subclass static methods HIDE (not override) parent static methods.
 */
public class core27StaticMembersDemo {

    static class Counter {
        static int totalCreated = 0;          // shared across all instances
        final int instanceId;

        static {                              // runs once at class load
            System.out.println("  [static block] Counter class loaded");
        }

        Counter() {
            totalCreated++;
            instanceId = totalCreated;
        }

        static int getTotal() { return totalCreated; }

        static void reset() { totalCreated = 0; }  // affects all instances' shared state
    }

    static class Parent  { static String greet() { return "Parent"; } }
    static class Child extends Parent { static String greet() { return "Child"; } }  // hides, not overrides

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        System.out.println("instance ids: " + c1.instanceId + ", " + c2.instanceId);
        System.out.println("total created: " + Counter.getTotal());

        Parent p = new Child();
        System.out.println("static hiding: p.greet()=" + p.greet() + " Child.greet()=" + Child.greet());

        Counter.reset();
        System.out.println("after reset: " + Counter.getTotal());
    }
}
