package pkg2versions;

/*
 * versions8Java24Gatherers.java
 * Stream gatherers (JEP 485), final in Java 24.
 * Preview in Java 22 (JEP 461) and Java 23 (JEP 473). This file uses the final API.
 */
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class versions8Java24Gatherers {
    public static void main(String[] args) {
        var windows = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.windowFixed(2))
                .toList();
        System.out.println(windows);
    }
}
