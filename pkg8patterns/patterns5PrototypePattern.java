package pkg8patterns;

/*
 * Prototype (Creational)
 * ----------------------
 * INTENT: create new objects by copying an existing instance (the prototype),
 *         instead of instantiating from scratch.
 * UML: Prototype + clone(): Prototype ; concrete prototypes implement copy.
 * PROS: cheap creation of complex objects; avoids subclassing factories.
 * CONS: deep vs shallow copy is tricky for nested mutable state.
 * REAL-WORLD: Object.clone(), copying configured templates.
 */
import java.util.*;

public class patterns5PrototypePattern {

    static class Document implements Cloneable {
        String title;
        List<String> sections;     // mutable -> needs deep copy

        Document(String title, List<String> sections) {
            this.title = title;
            this.sections = sections;
        }

        // Deep copy so clones don't share the sections list
        @Override public Document clone() {
            return new Document(this.title, new ArrayList<>(this.sections));
        }
        @Override public String toString() { return title + " " + sections; }
    }

    public static void main(String[] args) {
        Document original = new Document("Template", new ArrayList<>(List.of("Intro", "Body")));
        Document copy = original.clone();
        copy.title = "Copy";
        copy.sections.add("Conclusion");      // does NOT affect original (deep copy)

        System.out.println("original: " + original);
        System.out.println("copy:     " + copy);
        System.out.println("independent lists? " + (original.sections != copy.sections));
    }
}
