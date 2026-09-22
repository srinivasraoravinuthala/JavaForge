package pkg1core;

/*
 * core12Polymorphism.java
 * -----------------
 * Compile-time (overloading) vs runtime (overriding / dynamic dispatch).
 *
 * EXPLANATION:
 *  - Overloading: same method name, different parameters; chosen at COMPILE time.
 *  - Overriding: subclass redefines a method; chosen at RUNTIME by actual type.
 *  - Dynamic dispatch enables programming to an abstraction.
 */
public class core12Polymorphism {

    // --- Overloading (static polymorphism) ---
    static String describe(int x)    { return "int: " + x; }
    static String describe(double x) { return "double: " + x; }
    static String describe(String x) { return "String: " + x; }

    // --- Overriding (dynamic polymorphism) ---
    abstract static class Shape { abstract double area(); }
    static class Circle extends Shape {
        double r; Circle(double r){ this.r = r; }
        double area(){ return Math.PI * r * r; }
    }
    static class Square extends Shape {
        double s; Square(double s){ this.s = s; }
        double area(){ return s * s; }
    }

    public static void main(String[] args) {
        // Overloading resolved by argument type at compile time
        System.out.println(describe(10));
        System.out.println(describe(3.14));
        System.out.println(describe("hi"));

        // Overriding: same call, different behavior by runtime type
        Shape[] shapes = { new Circle(2), new Square(3) };
        for (Shape sh : shapes) {
            System.out.printf("%s area = %.2f%n", sh.getClass().getSimpleName(), sh.area());
        }
    }
}
