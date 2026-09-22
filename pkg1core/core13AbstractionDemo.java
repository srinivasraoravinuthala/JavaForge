package pkg1core;

/*
 * core13AbstractionDemo.java
 * --------------------
 * Abstract classes: partial implementation + enforced contract.
 *
 * EXPLANATION:
 *  - An abstract class cannot be instantiated; it may hold state and concrete
 *    methods plus abstract methods that subclasses MUST implement.
 *  - Use an abstract class (vs interface) when you need shared state/behavior.
 *  - Template Method pattern: a concrete method orchestrates abstract steps.
 */
public class core13AbstractionDemo {

    abstract static class Payment {
        protected final double amount;
        Payment(double amount) { this.amount = amount; }

        // Template method: fixed algorithm, variable steps
        final void process() {
            validate();
            System.out.println("Charging " + amount + " via " + method());
            authorize();
            System.out.println("Done.\n");
        }
        void validate() { if (amount <= 0) throw new IllegalArgumentException("amount<=0"); }

        // Steps subclasses must define
        abstract String method();
        abstract void authorize();
    }

    static class CardPayment extends Payment {
        CardPayment(double a){ super(a); }
        String method() { return "Credit Card"; }
        void authorize() { System.out.println("  contacting card network..."); }
    }
    static class UpiPayment extends Payment {
        UpiPayment(double a){ super(a); }
        String method() { return "UPI"; }
        void authorize() { System.out.println("  verifying VPA..."); }
    }

    public static void main(String[] args) {
        Payment[] payments = { new CardPayment(120.0), new UpiPayment(45.5) };
        for (Payment p : payments) p.process();
        // new Payment(10);  // ERROR: cannot instantiate abstract class
    }
}
