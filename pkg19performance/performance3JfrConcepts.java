package pkg19performance;

/*
 * performance3JfrConcepts.java
 * ------------------------------
 * Java Flight Recorder (JFR): low-overhead production profiling built into the JDK.
 *
 * DEFINITION:
 *   JFR records JVM and application events (CPU, locks, GC, I/O, custom) with
 *   minimal overhead. Analyze recordings in JDK Mission Control (JMC) or jfr print.
 *
 * KEY POINTS:
 *   - Start: -XX:StartFlightRecording=... or jcmd <pid> JFR.start
 *   - Events: jdk.CPULoad, jdk.GarbageCollection, jdk.ThreadStart, custom @Name events
 *   - jfr print recording.jfr — CLI summary
 *   - Prefer JFR over ad-hoc logging for performance investigations.
 */
public class performance3JfrConcepts {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("JFR is built into the JDK (no extra install).");
        System.out.println("\nStart a 10s recording from CLI:");
        System.out.println("  java -XX:StartFlightRecording=duration=10s,filename=demo.jfr performance3JfrConcepts.java");
        System.out.println("\nOr attach to running process:");
        System.out.println("  jcmd <pid> JFR.start name=demo settings=profile duration=30s filename=demo.jfr");
        System.out.println("  jfr print --events jdk.GarbageCollection demo.jfr");

        // Simulate some work while a recording might run
        long sum = 0;
        for (int i = 0; i < 10_000_000; i++) sum += i;
        System.out.println("\nComputed sum (work sample): " + sum);
    }
}
