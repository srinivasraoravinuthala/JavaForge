package pkg8patterns;

/*
 * Memento (Behavioral)
 * --------------------
 * INTENT: capture and externalize an object's internal state (without violating
 *         encapsulation) so it can be restored later (undo).
 * UML: Originator + save(): Memento + restore(Memento) ; Caretaker stores mementos.
 * PROS: clean undo/redo; encapsulation preserved.
 * CONS: memory cost if state is large/frequent.
 * REAL-WORLD: editor undo, transactions/savepoints, game checkpoints.
 */
import java.util.*;

public class patterns18MementoPattern {

    // Memento: an immutable snapshot
    record Memento(String content) {}

    // Originator
    static class Editor {
        private StringBuilder content = new StringBuilder();
        void type(String text) { content.append(text); }
        String getContent() { return content.toString(); }
        Memento save() { return new Memento(content.toString()); }
        void restore(Memento m) { content = new StringBuilder(m.content()); }
    }

    // Caretaker
    static class History {
        private final Deque<Memento> stack = new ArrayDeque<>();
        void push(Memento m) { stack.push(m); }
        Memento pop() { return stack.pop(); }
        boolean isEmpty() { return stack.isEmpty(); }
    }

    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.type("Hello");
        history.push(editor.save());          // checkpoint 1
        editor.type(", World");
        history.push(editor.save());          // checkpoint 2
        editor.type("!!! oops");

        System.out.println("current: " + editor.getContent());
        editor.restore(history.pop());        // undo to checkpoint 2
        System.out.println("after undo: " + editor.getContent());
        editor.restore(history.pop());        // undo to checkpoint 1
        System.out.println("after undo: " + editor.getContent());
    }
}
