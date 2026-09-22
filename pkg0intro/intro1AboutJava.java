package pkg0intro;

/*
 * intro1AboutJava.java
 * --------------------
 * WHAT IS JAVA? A definition + the core ideas, as a runnable tutorial.
 *
 * DEFINITION:
 *   Java is a high-level, class-based, object-oriented, statically-typed,
 *   general-purpose programming language designed to have as few implementation
 *   dependencies as possible. Source compiles to bytecode that runs on any
 *   Java Virtual Machine (JVM) -> "Write Once, Run Anywhere" (WORA).
 *
 * KEY CHARACTERISTICS:
 *   - Simple & familiar (C/C++-like syntax, no pointers/manual memory)
 *   - Object-oriented (everything revolves around classes & objects)
 *   - Platform independent (bytecode + JVM)
 *   - Robust (strong typing, exceptions, garbage collection)
 *   - Secure (bytecode verification, sandboxing, no raw pointers)
 *   - Multithreaded (built-in concurrency support)
 *   - Architecture neutral & portable
 *   - High performance (JIT compilation)
 *   - Distributed & dynamic
 */
public class intro1AboutJava {
    public static void main(String[] args) {
        System.out.println("=== WHAT IS JAVA? ===\n");
        System.out.println("Java is a high-level, object-oriented, platform-independent language.");
        System.out.println("Source (.java) -> compiler (javac) -> bytecode (.class) -> JVM -> runs anywhere.\n");

        String[] pillars = {
            "Simple & familiar       : C-like syntax, automatic memory management",
            "Object-oriented         : classes, objects, inheritance, polymorphism",
            "Platform independent    : bytecode runs on any JVM (WORA)",
            "Robust                  : strong typing, exception handling, GC",
            "Secure                  : bytecode verification, no raw pointers",
            "Multithreaded           : first-class threads & concurrency",
            "Portable                : fixed primitive sizes, no platform quirks",
            "High performance        : Just-In-Time (JIT) compilation",
            "Distributed & dynamic   : networking libraries, runtime class loading"
        };
        System.out.println("THE BUZZWORDS (Java's design pillars):");
        for (String p : pillars) System.out.println("  - " + p);

        System.out.println("\nEDITIONS:");
        System.out.println("  - Java SE  (Standard Edition)  : core language + standard library");
        System.out.println("  - Java EE / Jakarta EE         : enterprise APIs (web, persistence)");
        System.out.println("  - Java ME  (Micro Edition)     : constrained/embedded devices");

        System.out.println("\nJDK vs JRE vs JVM:");
        System.out.println("  - JVM : executes bytecode (memory, GC, JIT)");
        System.out.println("  - JRE : JVM + standard libraries (to RUN apps)");
        System.out.println("  - JDK : JRE + compiler & tools (to BUILD apps)");

        System.out.println("\nThis runtime: Java " + System.getProperty("java.version")
                + " on " + System.getProperty("os.name"));
    }
}
