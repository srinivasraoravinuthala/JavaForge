package pkg1core;

/*
 * core5ControlStatements.java
 * ----------------------
 * if / else if / else, classic switch, modern switch expressions, and
 * pattern matching for switch (Java 21).
 *
 * EXPLANATION:
 *  - switch EXPRESSIONS (->) return a value and don't fall through.
 *  - Pattern matching lets switch branch on the runtime TYPE of an object.
 */
public class core5ControlStatements {
    sealed interface Shape permits Circle, Rectangle {}
    record Circle(double r) implements Shape {}
    record Rectangle(double w, double h) implements Shape {}

    public static void main(String[] args) {
        int score = 82;

        // if / else if / else
        String grade;
        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else grade = "F";
        System.out.println("Grade: " + grade);

        // Classic switch (statement)
        int day = 3;
        switch (day) {
            case 1: System.out.println("Monday"); break;
            case 3: System.out.println("Wednesday"); break;
            default: System.out.println("Other day");
        }

        // Switch EXPRESSION with arrow and yield
        String type = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> {
                yield "Invalid";   // yield returns from a block
            }
        };
        System.out.println("Day type: " + type);

        // Pattern matching for switch (Java 21) + guarded patterns
        for (Shape sh : new Shape[]{ new Circle(2), new Rectangle(3, 4) }) {
            double area = switch (sh) {
                case Circle c -> Math.PI * c.r() * c.r();
                case Rectangle r when r.w() == r.h() -> r.w() * r.w(); // guard
                case Rectangle r -> r.w() * r.h();
            };
            System.out.printf("Area of %s = %.2f%n", sh, area);
        }

        // instanceof pattern matching (binds the variable)
        Object o = "hello";
        if (o instanceof String str && str.length() > 3) {
            System.out.println("String of length " + str.length());
        }
    }
}
