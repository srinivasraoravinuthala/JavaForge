package pkg19performance;

/*
 * performance4GraalVmNativeImage.java
 * -----------------------------------
 * GraalVM native-image: ahead-of-time compilation to a standalone binary.
 *
 * DEFINITION:
 *   native-image compiles Java to a native executable with fast startup and
 *   lower memory footprint — ideal for CLI tools, serverless, containers.
 *
 * KEY POINTS:
 *   - Trade-off: longer build, reflection/config needed for dynamic features.
 *   - Reachability metadata: reflect-config.json, resource-config.json.
 *   - Not all Java features at runtime (some agents, deep reflection need config).
 *
 * Build (requires GraalVM + native-image):
 *   native-image -jar myapp.jar
 *   ./myapp   # starts in milliseconds
 */
public class performance4GraalVmNativeImage {

    public static void main(String[] args) {
        System.out.println("GraalVM native-image benefits:");
        System.out.println("  - Startup: ms instead of seconds (no JVM warmup phase)");
        System.out.println("  - Memory: smaller RSS (no full HotSpot runtime)");
        System.out.println("  - Single deployable binary");

        System.out.println("\nWhen NOT to use:");
        System.out.println("  - Heavy reflection/dynamic proxies without config");
        System.out.println("  - Apps that rely on JIT peak performance after long warmup");
        System.out.println("  - Frequent dynamic class loading");

        System.out.println("\nJMH benchmarks for micro-benchmarks: see pkg19performance/jmh-demo/");
        System.out.println("Hello from JVM — same code can be compiled with native-image.");
    }
}
