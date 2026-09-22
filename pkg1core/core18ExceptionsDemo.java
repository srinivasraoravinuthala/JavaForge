package pkg1core;

/*
 * core18ExceptionsDemo.java
 * -------------------
 * Checked vs unchecked, try/catch/finally, multi-catch, try-with-resources,
 * custom exceptions, and exception chaining.
 *
 * EXPLANATION:
 *  - Throwable -> Error (don't catch) and Exception.
 *  - Checked exceptions (extend Exception) must be declared/handled.
 *  - Unchecked (extend RuntimeException) are programming errors.
 *  - try-with-resources auto-closes AutoCloseable resources.
 */
public class core18ExceptionsDemo {

    static class InsufficientFundsException extends Exception {  // checked, custom
        InsufficientFundsException(String msg) { super(msg); }
    }

    // A resource that auto-closes
    static class Resource implements AutoCloseable {
        Resource() { System.out.println("  open resource"); }
        void use() { System.out.println("  use resource"); }
        public void close() { System.out.println("  close resource (auto)"); }
    }

    static void withdraw(double balance, double amt) throws InsufficientFundsException {
        if (amt > balance) throw new InsufficientFundsException("need " + amt + " have " + balance);
        System.out.println("withdrew " + amt);
    }

    public static void main(String[] args) {
        // try / catch / finally
        try {
            int[] a = new int[2];
            System.out.println(a[5]);                   // throws ArrayIndexOutOfBounds
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        } finally {
            System.out.println("finally always runs");
        }

        // Multi-catch
        try {
            Object o = "x";
            Integer i = (Integer) o;                    // ClassCastException
        } catch (NullPointerException | ClassCastException e) {
            System.out.println("Multi-catch handled: " + e.getClass().getSimpleName());
        }

        // Checked custom exception
        try {
            withdraw(100, 150);
        } catch (InsufficientFundsException e) {
            System.out.println("Business error: " + e.getMessage());
        }

        // try-with-resources
        try (Resource r = new Resource()) {
            r.use();
        }

        // Exception chaining (preserve the cause)
        try {
            try { throw new java.io.IOException("disk error"); }
            catch (java.io.IOException io) { throw new RuntimeException("wrapping", io); }
        } catch (RuntimeException e) {
            System.out.println("Wrapped: " + e.getMessage() + " caused by " + e.getCause().getMessage());
        }
    }
}
