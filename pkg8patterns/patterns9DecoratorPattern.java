package pkg8patterns;

/*
 * Decorator (Structural)
 * ----------------------
 * INTENT: attach additional responsibilities to an object dynamically, as a
 *         flexible alternative to subclassing.
 * UML: Component <|-- ConcreteComponent and <|-- Decorator (wraps a Component).
 * PROS: add behavior at runtime; compose features; avoids subclass explosion.
 * CONS: many small wrapper objects; order can matter.
 * REAL-WORLD: java.io streams (BufferedReader wraps Reader), Collections.unmodifiableList.
 */
public class patterns9DecoratorPattern {

    interface Coffee { String desc(); double cost(); }

    static class Espresso implements Coffee {
        public String desc() { return "Espresso"; }
        public double cost() { return 2.0; }
    }

    // Base decorator wraps another Coffee
    static abstract class CoffeeDecorator implements Coffee {
        protected final Coffee inner;
        CoffeeDecorator(Coffee inner) { this.inner = inner; }
    }
    static class Milk extends CoffeeDecorator {
        Milk(Coffee c) { super(c); }
        public String desc() { return inner.desc() + " + Milk"; }
        public double cost() { return inner.cost() + 0.5; }
    }
    static class Sugar extends CoffeeDecorator {
        Sugar(Coffee c) { super(c); }
        public String desc() { return inner.desc() + " + Sugar"; }
        public double cost() { return inner.cost() + 0.25; }
    }

    public static void main(String[] args) {
        Coffee order = new Sugar(new Milk(new Espresso()));   // stack decorators
        System.out.printf("%s = $%.2f%n", order.desc(), order.cost());
    }
}
