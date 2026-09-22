package pkg8patterns;

/*
 * State (Behavioral)
 * ------------------
 * INTENT: allow an object to alter its behavior when its internal state changes;
 *         the object appears to change its class.
 * UML: Context --> State ; concrete states implement transitions.
 * PROS: removes large conditionals; each state is isolated.
 * CONS: more classes; transitions spread across states.
 * REAL-WORLD: TCP connections, vending machines, workflow/order status, UI modes.
 */
public class patterns20StatePattern {

    interface State { State next(); String name(); }

    // A simple traffic light: GREEN -> YELLOW -> RED -> GREEN
    static class Green implements State {
        public State next() { return new Yellow(); }
        public String name() { return "GREEN (go)"; }
    }
    static class Yellow implements State {
        public State next() { return new Red(); }
        public String name() { return "YELLOW (slow)"; }
    }
    static class Red implements State {
        public State next() { return new Green(); }
        public String name() { return "RED (stop)"; }
    }

    static class TrafficLight {
        private State state = new Green();
        void change() { state = state.next(); }
        String status() { return state.name(); }
    }

    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 5; i++) {
            System.out.println(light.status());
            light.change();
        }
    }
}
