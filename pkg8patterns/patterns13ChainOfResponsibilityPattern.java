package pkg8patterns;

/*
 * Chain of Responsibility (Behavioral)
 * ------------------------------------
 * INTENT: pass a request along a chain of handlers; each decides to handle it or
 *         forward it. Decouples sender from receiver.
 * UML: Handler + setNext() + handle() ; concrete handlers form a linked chain.
 * PROS: flexible ordering; single responsibility per handler.
 * CONS: request may go unhandled; harder to debug flow.
 * REAL-WORLD: servlet filters, logging levels, exception handling, middleware.
 */
public class patterns13ChainOfResponsibilityPattern {

    static abstract class Approver {
        protected Approver next;
        Approver setNext(Approver next) { this.next = next; return next; }
        void handle(double amount) {
            if (canApprove(amount)) System.out.println(name() + " approved $" + amount);
            else if (next != null) next.handle(amount);
            else System.out.println("No one could approve $" + amount);
        }
        abstract boolean canApprove(double amount);
        abstract String name();
    }

    static class TeamLead extends Approver {
        boolean canApprove(double a) { return a <= 1_000; }
        String name() { return "TeamLead"; }
    }
    static class Manager extends Approver {
        boolean canApprove(double a) { return a <= 10_000; }
        String name() { return "Manager"; }
    }
    static class Director extends Approver {
        boolean canApprove(double a) { return a <= 100_000; }
        String name() { return "Director"; }
    }

    public static void main(String[] args) {
        Approver chain = new TeamLead();
        chain.setNext(new Manager()).setNext(new Director());

        for (double amt : new double[]{500, 5_000, 50_000, 500_000}) chain.handle(amt);
    }
}
