package pkg8patterns;

/*
 * Mediator (Behavioral)
 * ---------------------
 * INTENT: define an object that encapsulates how a set of objects interact,
 *         promoting loose coupling by keeping objects from referring to each other.
 * UML: Mediator <-- Colleagues ; colleagues talk through the mediator.
 * PROS: centralizes complex communication; reduces many-to-many coupling.
 * CONS: mediator can become a complex "god" object.
 * REAL-WORLD: chat rooms, air traffic control, UI form field coordination.
 */
import java.util.*;

public class patterns17MediatorPattern {

    interface ChatRoom { void register(User u); void send(String from, String msg); }

    static class ChatRoomImpl implements ChatRoom {
        private final Map<String, User> users = new HashMap<>();
        public void register(User u) { users.put(u.name, u); }
        public void send(String from, String msg) {
            for (User u : users.values())
                if (!u.name.equals(from)) u.receive(from, msg);
        }
    }

    static class User {
        final String name;
        private final ChatRoom room;
        User(String name, ChatRoom room) { this.name = name; this.room = room; room.register(this); }
        void send(String msg) { System.out.println(name + " sends: " + msg); room.send(name, msg); }
        void receive(String from, String msg) { System.out.println("  " + name + " got from " + from + ": " + msg); }
    }

    public static void main(String[] args) {
        ChatRoom room = new ChatRoomImpl();
        User alice = new User("Alice", room);
        new User("Bob", room);
        new User("Cara", room);
        alice.send("Hello everyone!");
    }
}
