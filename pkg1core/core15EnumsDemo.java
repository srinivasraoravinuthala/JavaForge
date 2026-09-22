package pkg1core;

/*
 * core15EnumsDemo.java
 * --------------
 * Enums: fixed sets of constants with fields, constructors, methods, and
 * per-constant behavior. Also enums in switch.
 *
 * EXPLANATION:
 *  - An enum is a type-safe set of named constants (each is a singleton).
 *  - Enums can have fields, constructors, and abstract methods overridden per
 *    constant (a clean alternative to switch-on-type).
 */
public class core15EnumsDemo {

    enum Planet {
        EARTH(5.976e24, 6.37814e6),
        MARS (6.421e23, 3.3972e6);

        private final double mass, radius;
        Planet(double mass, double radius) { this.mass = mass; this.radius = radius; }
        double gravity() { return 6.67300e-11 * mass / (radius * radius); }
    }

    // Per-constant behavior (constant-specific method bodies)
    enum Operation {
        ADD { int apply(int a, int b){ return a + b; } },
        SUB { int apply(int a, int b){ return a - b; } },
        MUL { int apply(int a, int b){ return a * b; } };
        abstract int apply(int a, int b);
    }

    public static void main(String[] args) {
        for (Planet p : Planet.values()) {
            System.out.printf("%s gravity = %.2f m/s^2%n", p, p.gravity());
        }

        for (Operation op : Operation.values()) {
            System.out.println("6 " + op + " 3 = " + op.apply(6, 3));
        }

        // Enum in switch + useful methods
        Planet here = Planet.EARTH;
        String note = switch (here) {
            case EARTH -> "home";
            case MARS  -> "the red planet";
        };
        System.out.println(here + " is " + note + " | ordinal=" + here.ordinal() + " name=" + here.name());
        System.out.println("valueOf(\"MARS\") = " + Planet.valueOf("MARS"));
    }
}
