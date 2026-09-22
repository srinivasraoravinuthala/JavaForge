package pkg8patterns;

/*
 * Interpreter (Behavioral)
 * ------------------------
 * INTENT: given a language, define a representation for its grammar and an
 *         interpreter that uses the representation to evaluate sentences.
 * UML: Expression + interpret(ctx) ; Terminal and NonTerminal expressions.
 * PROS: easy to extend the grammar; each rule is a class.
 * CONS: complex grammars become hard to maintain (use a parser generator instead).
 * REAL-WORLD: regular expressions, SQL parsers, rule engines.
 */
public class patterns15InterpreterPattern {

    interface Expr { int interpret(); }

    static class Num implements Expr {
        private final int value;
        Num(int value) { this.value = value; }
        public int interpret() { return value; }
    }
    static class Add implements Expr {
        private final Expr l, r;
        Add(Expr l, Expr r) { this.l = l; this.r = r; }
        public int interpret() { return l.interpret() + r.interpret(); }
    }
    static class Mul implements Expr {
        private final Expr l, r;
        Mul(Expr l, Expr r) { this.l = l; this.r = r; }
        public int interpret() { return l.interpret() * r.interpret(); }
    }

    public static void main(String[] args) {
        // (3 + 4) * 5
        Expr expr = new Mul(new Add(new Num(3), new Num(4)), new Num(5));
        System.out.println("(3 + 4) * 5 = " + expr.interpret());
    }
}
