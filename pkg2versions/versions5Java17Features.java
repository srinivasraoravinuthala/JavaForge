package pkg2versions;

/*
 * versions5Java17Features.java  (requires Java 17)
 * --------------------------------
 * Cumulative Java 12-17 example. The whole file requires Java 17.
 * It is not a Java 12, 13, 14, 15, or 16 program.
 * Switch expressions are the final form (Java 14), not the Java 12 preview.
 * Pattern matching for switch was still a preview in Java 17 and is not used.
 *
 * FEATURES & WHY (cumulative across 12-17):
 *  - Records (16)            : concise immutable data carriers.
 *  - Sealed classes (17)     : control the type hierarchy; exhaustive switches.
 *  - Pattern matching for instanceof (16): bind + test in one step.
 *  - Text blocks (15)        : multi-line string literals.
 *  - Switch expressions (14) : value-returning, arrow form.
 *  - Helpful NullPointerExceptions, new GCs (ZGC/Shenandoah prod-ready).
 */
public class versions5Java17Features {

    sealed interface Vehicle permits Car, Truck {}
    record Car(int seats) implements Vehicle {}
    record Truck(double tons) implements Vehicle {}

    static String classify(Vehicle v) {
        // Pattern matching for switch over a sealed type (preview in 17, final in 21)
        if (v instanceof Car c) return "Car with " + c.seats() + " seats";
        if (v instanceof Truck t) return "Truck carrying " + t.tons() + " tons";
        return "unknown";
    }

    public static void main(String[] args) {
        // Records
        Car car = new Car(4);
        Truck truck = new Truck(12.5);
        System.out.println(car + " / " + truck);

        // Sealed + instanceof pattern
        System.out.println(classify(car));
        System.out.println(classify(truck));

        // Text block
        String html = """
                <html>
                    <body>Hello</body>
                </html>""";
        System.out.println(html);

        // Switch expression
        int code = 2;
        String level = switch (code) {
            case 1 -> "INFO";
            case 2 -> "WARN";
            default -> "ERROR";
        };
        System.out.println("level=" + level);
    }
}
