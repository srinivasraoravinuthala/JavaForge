package pkg1core;

/*
 * core17SealedClassesDemo.java
 * ----------------------
 * Sealed classes/interfaces (Java 17+): restrict which types may extend/implement,
 * enabling exhaustive pattern matching.
 *
 * EXPLANATION:
 *  - `sealed ... permits A, B` lists the only allowed subtypes.
 *  - Subtypes must be `final`, `sealed`, or `non-sealed`.
 *  - With a sealed hierarchy, a switch can be EXHAUSTIVE without a default.
 */
public class core17SealedClassesDemo {

    sealed interface Expr permits Num, Add, Mul {}
    record Num(double value) implements Expr {}
    record Add(Expr left, Expr right) implements Expr {}
    record Mul(Expr left, Expr right) implements Expr {}

    // Exhaustive evaluation via pattern matching + record deconstruction
    static double eval(Expr e) {
        return switch (e) {                       // no default needed: sealed + exhaustive
            case Num(double v)        -> v;
            case Add(Expr l, Expr r)  -> eval(l) + eval(r);
            case Mul(Expr l, Expr r)  -> eval(l) * eval(r);
        };
    }

    public static void main(String[] args) {
        // (2 + 3) * 4
        Expr expr = new Mul(new Add(new Num(2), new Num(3)), new Num(4));
        System.out.println("(2 + 3) * 4 = " + eval(expr));

        Expr nested = new Add(new Num(10), new Mul(new Num(2), new Num(2.5)));
        System.out.println("10 + (2 * 2.5) = " + eval(nested));
    }
}
