package pkg19performance;

/*
 * performance2JvmFlagsGuide.java
 * ------------------------------
 * Essential JVM flags for performance tuning and diagnostics.
 *
 * DEFINITION:
 *   JVM flags control heap size, GC algorithm, logging, and diagnostics.
 *   Architects choose flags based on workload: latency vs throughput vs memory.
 */
public class performance2JvmFlagsGuide {

    public static void main(String[] args) {
        System.out.println("=== Heap ===");
        System.out.println("-Xms512m -Xmx2g          initial / max heap");
        System.out.println("-XX:MaxMetaspaceSize=256m class metadata cap");

        System.out.println("\n=== GC selection (pick ONE collector) ===");
        System.out.println("-XX:+UseG1GC              G1 (default on most JDKs, balanced)");
        System.out.println("-XX:+UseZGC               ZGC (low latency, large heaps)");
        System.out.println("-XX:+UseShenandoahGC      Shenandoah (low pause, concurrent)");
        System.out.println("-XX:+UseParallelGC         throughput-oriented");

        System.out.println("\n=== GC / JVM logging (Java 9+) ===");
        System.out.println("-Xlog:gc:stdout            GC events to console");
        System.out.println("-Xlog:gc*:file=gc.log      detailed GC log file");

        System.out.println("\n=== Diagnostics ===");
        System.out.println("-XX:+HeapDumpOnOutOfMemoryError");
        System.out.println("-XX:HeapDumpPath=./dumps   heap dump on OOM");
        System.out.println("-XX:StartFlightRecording=duration=60s,filename=app.jfr");

        System.out.println("\n=== Current runtime ===");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Processors : " + rt.availableProcessors());
        System.out.println("Max heap   : " + rt.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("Free heap  : " + rt.freeMemory() / 1024 / 1024 + " MB");
    }
}
