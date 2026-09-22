package pkg8patterns;

/*
 * Builder (Creational)
 * --------------------
 * INTENT: construct a complex object step by step; the same process can create
 *         different representations. Great for many optional parameters.
 * UML: Product , Builder + setX(): Builder + build(): Product (fluent).
 * PROS: readable construction; immutable result; avoids telescoping constructors.
 * CONS: more code than a plain constructor.
 * REAL-WORLD: StringBuilder, Stream.Builder, HttpRequest.newBuilder().
 */
public class patterns4BuilderPattern {

    static class Pizza {
        private final String size;
        private final boolean cheese, pepperoni, mushrooms;

        private Pizza(Builder b) {
            this.size = b.size;
            this.cheese = b.cheese;
            this.pepperoni = b.pepperoni;
            this.mushrooms = b.mushrooms;
        }
        @Override public String toString() {
            return size + " pizza [cheese=" + cheese + ", pepperoni=" + pepperoni + ", mushrooms=" + mushrooms + "]";
        }

        static class Builder {
            private final String size;            // required
            private boolean cheese, pepperoni, mushrooms;   // optional
            Builder(String size) { this.size = size; }
            Builder cheese()    { this.cheese = true; return this; }
            Builder pepperoni() { this.pepperoni = true; return this; }
            Builder mushrooms() { this.mushrooms = true; return this; }
            Pizza build() { return new Pizza(this); }
        }
    }

    public static void main(String[] args) {
        Pizza p1 = new Pizza.Builder("Large").cheese().pepperoni().build();
        Pizza p2 = new Pizza.Builder("Medium").cheese().mushrooms().build();
        System.out.println(p1);
        System.out.println(p2);
    }
}
