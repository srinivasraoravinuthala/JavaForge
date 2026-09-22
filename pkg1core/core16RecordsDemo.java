package pkg1core;

/*
 * core16RecordsDemo.java
 * ----------------
 * Records (Java 16+): immutable data carriers with auto-generated
 * constructor, accessors, equals, hashCode, and toString.
 *
 * EXPLANATION:
 *  - `record Point(int x, int y)` generates everything for a value object.
 *  - Compact canonical constructors let you validate/normalize.
 *  - Records are implicitly final and their components are final.
 */
import java.util.List;
import java.util.Objects;

public class core16RecordsDemo {

    record Point(int x, int y) {
        // Compact constructor: validation without re-listing params
        Point {
            if (x < 0 || y < 0) throw new IllegalArgumentException("negative coords");
        }
        // You can add extra methods
        double distanceTo(Point o) {
            return Math.hypot(x - o.x, y - o.y);
        }
        // And static factory helpers
        static Point origin() { return new Point(0, 0); }
    }

    record Range(int lo, int hi) {
        Range { if (lo > hi) throw new IllegalArgumentException("lo>hi"); }
        boolean contains(int v) { return v >= lo && v <= hi; }
    }

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);

        // Auto-generated accessors (no get prefix), toString, equals, hashCode
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("b.x() = " + b.x() + ", b.y() = " + b.y());
        System.out.println("distance a->b = " + a.distanceTo(b));
        System.out.println("a.equals(origin)? " + a.equals(Point.origin()));
        System.out.println("hashCode equal? " + (a.hashCode() == Point.origin().hashCode()));

        // Records work great as immutable elements in collections
        List<Range> ranges = List.of(new Range(1, 5), new Range(10, 20));
        ranges.forEach(r -> System.out.println(r + " contains 3? " + r.contains(3)));

        // Validation in action
        try { new Point(-1, 0); } catch (Exception e) { System.out.println("Rejected: " + e.getMessage()); }

        System.out.println("Objects.equals check: " + Objects.equals(b, new Point(3, 4)));
    }
}
