package pkg8patterns;

/*
 * Flyweight (Structural)
 * ----------------------
 * INTENT: share fine-grained objects to support large numbers efficiently by
 *         separating intrinsic (shared) state from extrinsic (context) state.
 * UML: FlyweightFactory caches Flyweights; client passes extrinsic state per call.
 * PROS: huge memory savings when many objects share state.
 * CONS: complexity; must split state carefully.
 * REAL-WORLD: Integer.valueOf cache (-128..127), String pool, glyph rendering.
 */
import java.util.*;

public class patterns11FlyweightPattern {

    // Intrinsic (shared) state: the tree species
    static class TreeType {
        final String name, color;
        TreeType(String name, String color) { this.name = name; this.color = color; }
        String draw(int x, int y) { return name + "(" + color + ") at (" + x + "," + y + ")"; }
    }

    // Factory ensures one TreeType object per unique species
    static class TreeFactory {
        private final Map<String, TreeType> cache = new HashMap<>();
        TreeType get(String name, String color) {
            return cache.computeIfAbsent(name + "-" + color, k -> new TreeType(name, color));
        }
        int distinctTypes() { return cache.size(); }
    }

    public static void main(String[] args) {
        TreeFactory factory = new TreeFactory();
        // Plant 1000 trees but only a few shared TreeType objects exist
        List<String> forest = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TreeType type = factory.get(i % 2 == 0 ? "Oak" : "Pine", i % 2 == 0 ? "green" : "dark-green");
            forest.add(type.draw(i, i));   // extrinsic state (x,y) passed in
        }
        System.out.println("planted " + forest.size() + " trees");
        System.out.println("distinct shared TreeType objects = " + factory.distinctTypes());
        System.out.println("sample: " + forest.get(0));
    }
}
