package pkg1core;

/*
 * core23OptionalDemo.java
 * -----------------
 * Optional<T>: model "value or absent" without null, and avoid NullPointerException.
 *
 * EXPLANATION:
 *  - Optional is a container that holds a value or nothing.
 *  - Prefer map/filter/flatMap + orElse/orElseThrow over isPresent()/get().
 *  - Use it as a RETURN type; don't use it for fields or method parameters.
 */
import java.util.*;

public class core23OptionalDemo {

    record User(String name, Optional<String> email) {}

    // A repository that may not find a user
    static Optional<User> findUser(int id) {
        Map<Integer, User> db = Map.of(
                1, new User("Alice", Optional.of("alice@x.com")),
                2, new User("Bob", Optional.empty()));
        return Optional.ofNullable(db.get(id));
    }

    public static void main(String[] args) {
        // Creating
        Optional<String> present = Optional.of("value");
        Optional<String> empty = Optional.empty();
        Optional<String> maybe = Optional.ofNullable(null);
        System.out.println("present=" + present + " empty=" + empty + " maybe=" + maybe);

        // orElse / orElseGet / orElseThrow
        System.out.println("orElse: " + empty.orElse("default"));
        System.out.println("orElseGet: " + empty.orElseGet(() -> "computed-default"));

        // map / filter chaining (no null checks)
        String upper = present.map(String::toUpperCase).filter(s -> s.length() > 2).orElse("n/a");
        System.out.println("mapped+filtered: " + upper);

        // ifPresent / ifPresentOrElse
        findUser(1).ifPresentOrElse(
                u -> System.out.println("found: " + u.name()),
                () -> System.out.println("no user"));

        // Nested Optional -> flatMap to avoid Optional<Optional<...>>
        String email = findUser(1).flatMap(User::email).orElse("no-email");
        System.out.println("user 1 email: " + email);
        System.out.println("user 2 email: " + findUser(2).flatMap(User::email).orElse("no-email"));
        System.out.println("user 9 email: " + findUser(9).flatMap(User::email).orElse("no-user/email"));

        // orElseThrow for required values
        try {
            findUser(99).orElseThrow(() -> new NoSuchElementException("user 99 missing"));
        } catch (NoSuchElementException e) {
            System.out.println("threw: " + e.getMessage());
        }
    }
}
