package pkg0intro;

/*
 * intro2HistoryOfJava.java
 * ------------------------
 * THE HISTORY OF JAVA, as a runnable timeline.
 *
 * ORIGIN:
 *   Java began in 1991 at Sun Microsystems in the "Green Project" led by
 *   James Gosling, Mike Sheridan, and Patrick Naughton. It was first called
 *   "Oak" (after a tree outside Gosling's office), then "Green", and finally
 *   "Java" (after Java coffee). It was designed for interactive television /
 *   embedded devices, but found its killer use on the web.
 *
 *   Public release: 1995 (JDK 1.0, 1996). Slogan: "Write Once, Run Anywhere".
 *   Oracle acquired Sun Microsystems in 2010 and now stewards Java.
 *   Since Java 9 (2017), a new version ships every 6 months; LTS releases
 *   (8, 11, 17, 21, 25) are the long-term-support targets for most companies.
 */
public class intro2HistoryOfJava {
    record Milestone(String when, String what) {}

    public static void main(String[] args) {
        System.out.println("=== THE HISTORY OF JAVA ===\n");

        Milestone[] timeline = {
            new Milestone("1991", "'Green Project' starts at Sun; language named 'Oak' by James Gosling"),
            new Milestone("1995", "Renamed 'Java'; publicly announced; 'Write Once, Run Anywhere'"),
            new Milestone("1996", "JDK 1.0 released"),
            new Milestone("1998", "J2SE 1.2 ('Java 2'): Swing, Collections Framework"),
            new Milestone("2004", "J2SE 5.0: generics, enums, autoboxing, varargs, annotations"),
            new Milestone("2006", "Java 6: performance, scripting API; Java open-sourced (OpenJDK)"),
            new Milestone("2010", "Oracle acquires Sun Microsystems"),
            new Milestone("2011", "Java 7: try-with-resources, diamond, NIO.2, Fork/Join"),
            new Milestone("2014", "Java 8: lambdas, Streams, Optional, new Date/Time (huge release)"),
            new Milestone("2017", "Java 9: modules (JPMS), jshell; 6-month release cadence begins"),
            new Milestone("2018", "Java 10 (var) & Java 11 (LTS): HttpClient, run single-file source"),
            new Milestone("2020", "Java 14: records (preview), switch expressions (final)"),
            new Milestone("2021", "Java 17 (LTS): sealed classes, records final, pattern matching"),
            new Milestone("2023", "Java 21 (LTS): virtual threads, record patterns, sequenced collections"),
            new Milestone("2025", "Java 25 (LTS): latest long-term-support release")
        };

        for (Milestone m : timeline)
            System.out.printf("  %-6s  %s%n", m.when(), m.what());

        System.out.println("\nWHY THE NAME CHANGES?  Oak -> Green -> Java (trademark + 'Java coffee').");
        System.out.println("STEWARDSHIP: Sun Microsystems (1991-2010) -> Oracle (2010-present), via OpenJDK.");
        System.out.println("LTS releases: 8, 11, 17, 21, 25  (most production systems target these).");
    }
}
