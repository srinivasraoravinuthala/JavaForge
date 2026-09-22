package pkg8patterns;

/*
 * Bridge (Structural)
 * -------------------
 * INTENT: decouple an abstraction from its implementation so the two can vary
 *         independently (composition over inheritance to avoid class explosion).
 * UML: Abstraction --has-a--> Implementor ; both have their own hierarchies.
 * PROS: avoids N*M subclasses; switch implementations at runtime.
 * CONS: more indirection up front.
 * REAL-WORLD: JDBC (API) over drivers; SLF4J over logging backends.
 */
public class patterns7BridgePattern {

    // Implementor hierarchy
    interface Renderer { String renderCircle(double r); }
    static class VectorRenderer implements Renderer {
        public String renderCircle(double r) { return "vector circle r=" + r; }
    }
    static class RasterRenderer implements Renderer {
        public String renderCircle(double r) { return "raster pixels for r=" + r; }
    }

    // Abstraction holds a reference (the "bridge") to an Implementor
    static abstract class Shape {
        protected final Renderer renderer;
        Shape(Renderer renderer) { this.renderer = renderer; }
        abstract String draw();
    }
    static class Circle extends Shape {
        private final double radius;
        Circle(Renderer renderer, double radius) { super(renderer); this.radius = radius; }
        String draw() { return renderer.renderCircle(radius); }
    }

    public static void main(String[] args) {
        System.out.println(new Circle(new VectorRenderer(), 5).draw());
        System.out.println(new Circle(new RasterRenderer(), 5).draw());
    }
}
