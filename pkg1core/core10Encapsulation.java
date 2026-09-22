package pkg1core;

/*
 * core10Encapsulation.java
 * ------------------
 * Hiding internal state behind methods; validating via setters; immutability.
 *
 * EXPLANATION:
 *  - core10Encapsulation = bundling data + behavior and restricting direct access.
 *  - Make fields `private`; expose controlled getters/setters.
 *  - Benefits: invariants are protected, internals can change freely.
 */
public class core10Encapsulation {

    // A well-encapsulated mutable class with validation
    static class BankAccount {
        private double balance;                 // hidden state
        private final String owner;

        BankAccount(String owner, double opening) {
            if (opening < 0) throw new IllegalArgumentException("opening < 0");
            this.owner = owner;
            this.balance = opening;
        }
        public double getBalance() { return balance; }       // read-only access
        public String getOwner() { return owner; }
        public void deposit(double amt) {
            if (amt <= 0) throw new IllegalArgumentException("deposit must be > 0");
            balance += amt;                                   // invariant guarded
        }
        public void withdraw(double amt) {
            if (amt <= 0 || amt > balance) throw new IllegalArgumentException("invalid withdraw");
            balance -= amt;
        }
    }

    public static void main(String[] args) {
        BankAccount acct = new BankAccount("Alice", 100);
        acct.deposit(50);
        acct.withdraw(30);
        System.out.println(acct.getOwner() + " balance: " + acct.getBalance());

        try {
            acct.withdraw(1000);                 // blocked by validation
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        // We cannot do acct.balance = -999;  -> field is private (compile error)
    }
}
