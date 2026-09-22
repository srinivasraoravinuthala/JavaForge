package pkg8patterns;

/*
 * Visitor (Behavioral)
 * --------------------
 * INTENT: represent an operation to be performed on elements of an object
 *         structure; add new operations without changing the element classes.
 * UML: Element + accept(Visitor) ; Visitor + visit(ConcreteElement) per type.
 * PROS: add operations easily (double dispatch); gather related behavior.
 * CONS: adding a new element type requires editing every visitor.
 * REAL-WORLD: AST traversal/compilers, file-system operations, DOM processing.
 *
 * NOTE: Java 21 pattern matching often replaces Visitor for sealed hierarchies.
 */
public class patterns23VisitorPattern {

    interface Shape { <R> R accept(Visitor<R> v); }
    record Circle(double r) implements Shape { public <R> R accept(Visitor<R> v) { return v.visit(this); } }
    record Square(double s) implements Shape { public <R> R accept(Visitor<R> v) { return v.visit(this); } }

    interface Visitor<R> {
        R visit(Circle c);
        R visit(Square s);
    }

    // One operation: compute area
    static class AreaVisitor implements Visitor<Double> {
        public Double visit(Circle c) { return Math.PI * c.r() * c.r(); }
        public Double visit(Square s) { return s.s() * s.s(); }
    }
    // Another operation, added WITHOUT touching the shapes: describe
    static class DescribeVisitor implements Visitor<String> {
        public String visit(Circle c) { return "Circle r=" + c.r(); }
        public String visit(Square s) { return "Square s=" + s.s(); }
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(2), new Square(3) };
        AreaVisitor area = new AreaVisitor();
        DescribeVisitor describe = new DescribeVisitor();
        for (Shape shape : shapes) {
            System.out.printf("%s has area %.2f%n", shape.accept(describe), shape.accept(area));
        }
    }
}
