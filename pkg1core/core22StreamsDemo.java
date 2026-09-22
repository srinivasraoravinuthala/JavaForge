package pkg1core;

/*
 * core22StreamsDemo.java
 * ----------------
 * The Streams API: building pipelines of intermediate + terminal operations,
 * and Collectors for grouping/joining/summarizing.
 *
 * EXPLANATION:
 *  - Streams are LAZY: intermediate ops (filter/map/sorted) build a pipeline;
 *    a terminal op (collect/reduce/forEach/count) triggers execution.
 *  - Streams don't mutate the source; prefer pure, stateless functions.
 */
import java.util.*;
import java.util.stream.*;

public class core22StreamsDemo {

    record Person(String name, String city, int age) {}

    public static void main(String[] args) {
        List<Integer> nums = List.of(5, 2, 8, 1, 9, 3, 8, 2);

        // filter -> map -> sorted -> distinct -> collect
        List<Integer> result = nums.stream()
                .filter(n -> n > 2)
                .map(n -> n * 10)
                .distinct()
                .sorted()
                .toList();
        System.out.println("pipeline: " + result);

        // Numeric streams + statistics
        IntSummaryStatistics stats = nums.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.printf("count=%d sum=%d min=%d max=%d avg=%.2f%n",
                stats.getCount(), stats.getSum(), stats.getMin(), stats.getMax(), stats.getAverage());

        // reduce
        int product = Stream.of(1, 2, 3, 4).reduce(1, (a, b) -> a * b);
        System.out.println("product 1..4 = " + product);

        // Collectors: grouping, partitioning, joining
        List<Person> people = List.of(
                new Person("Alice", "NYC", 30),
                new Person("Bob", "LA", 25),
                new Person("Cara", "NYC", 35),
                new Person("Dan", "LA", 40));

        Map<String, List<String>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::city,
                         Collectors.mapping(Person::name, Collectors.toList())));
        System.out.println("grouped by city: " + byCity);

        Map<Boolean, List<String>> partition = people.stream()
                .collect(Collectors.partitioningBy(p -> p.age() >= 30,
                         Collectors.mapping(Person::name, Collectors.toList())));
        System.out.println("age>=30 partition: " + partition);

        Map<String, Double> avgAgeByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city, Collectors.averagingInt(Person::age)));
        System.out.println("avg age by city: " + avgAgeByCity);

        String names = people.stream().map(Person::name).collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joined names: " + names);

        // Generate / iterate (infinite streams with limit)
        List<Integer> firstFiveSquares = Stream.iterate(1, x -> x + 1).map(x -> x * x).limit(5).toList();
        System.out.println("first 5 squares: " + firstFiveSquares);

        // flatMap: flatten nested
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        List<Integer> flat = nested.stream().flatMap(List::stream).toList();
        System.out.println("flattened: " + flat);

        // anyMatch / allMatch / findFirst
        System.out.println("any > 8? " + nums.stream().anyMatch(n -> n > 8));
        System.out.println("all > 0? " + nums.stream().allMatch(n -> n > 0));
        System.out.println("first even: " + nums.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));
    }
}
