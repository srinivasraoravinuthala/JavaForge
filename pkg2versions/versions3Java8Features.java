package pkg2versions;

/*
 * versions3Java8Features.java  (2014)  -- the most important modern release
 * ----------------------------------------------------------------
 * FEATURES & WHY:
 *  - Lambdas            : functions as values; concise behavior.
 *  - Streams API        : declarative bulk data processing.
 *  - Optional           : explicit absence; avoid NullPointerException.
 *  - Default methods     : evolve interfaces without breaking implementers.
 *  - Method references  : shorthand for lambdas.
 *  - java.time          : modern, immutable date/time API.
 */
import java.util.*;
import java.util.stream.*;
import java.time.*;

public class versions3Java8Features {

    interface Named {
        String name();
        default String shout() { return name().toUpperCase(); }  // default method
    }

    public static void main(String[] args) {
        // Lambda implementing a functional interface
        Named n = () -> "java8";
        System.out.println("default method: " + n.shout());

        // Streams
        List<Integer> evens = IntStream.rangeClosed(1, 10)
                .filter(x -> x % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("evens 1..10: " + evens);

        // Method reference + collector
        String joined = Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.joining("-"));
        System.out.println("joined: " + joined);

        // Optional
        Optional<String> first = evens.stream().filter(x -> x > 100).map(String::valueOf).findFirst();
        System.out.println("optional: " + first.orElse("none"));

        // java.time
        LocalDate today = LocalDate.of(2024, 1, 15);
        System.out.println("plus 2 weeks: " + today.plusWeeks(2) + " | day: " + today.getDayOfWeek());
        Duration d = Duration.ofHours(2).plusMinutes(30);
        System.out.println("duration minutes: " + d.toMinutes());
    }
}
