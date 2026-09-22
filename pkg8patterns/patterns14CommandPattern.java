package pkg8patterns;

/*
 * Command (Behavioral)
 * --------------------
 * INTENT: encapsulate a request as an object, allowing queuing, logging, and undo.
 * UML: Command + execute()/undo() ; Invoker triggers; Receiver does the work.
 * PROS: decouples invoker from receiver; supports undo/redo and macros.
 * CONS: many command classes.
 * REAL-WORLD: Runnable, GUI actions, transaction logs, undo stacks.
 */
import java.util.*;

public class patterns14CommandPattern {

    interface Command { void execute(); void undo(); }

    // Receiver
    static class Light {
        private boolean on;
        void on()  { on = true;  System.out.println("Light ON"); }
        void off() { on = false; System.out.println("Light OFF"); }
    }

    static class LightOnCommand implements Command {
        private final Light light;
        LightOnCommand(Light l) { light = l; }
        public void execute() { light.on(); }
        public void undo() { light.off(); }
    }

    // Invoker with undo history
    static class RemoteControl {
        private final Deque<Command> history = new ArrayDeque<>();
        void press(Command c) { c.execute(); history.push(c); }
        void undoLast() {
            if (!history.isEmpty()) { System.out.print("undo -> "); history.pop().undo(); }
        }
    }

    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        Command lightOn = new LightOnCommand(new Light());
        remote.press(lightOn);
        remote.press(lightOn);
        remote.undoLast();
        remote.undoLast();
    }
}
