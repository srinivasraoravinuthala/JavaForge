package pkg0intro;

/*
 * intro3Objectives.java
 * ---------------------
 * THE DESIGN OBJECTIVES (GOALS) OF JAVA + how this project is organized.
 *
 * The original designers set five primary goals. Java should be:
 *   1. Simple, object-oriented, and familiar.
 *   2. Robust and secure.
 *   3. Architecture-neutral and portable.
 *   4. High performance.
 *   5. Interpreted, threaded, and dynamic.
 *
 * These objectives explain almost every language decision: garbage collection
 * (robustness), bytecode + JVM (portability), JIT (performance), no pointers
 * (security/simplicity), and built-in threads (concurrency).
 */
public class intro3Objectives {
    public static void main(String[] args) {
        System.out.println("=== JAVA'S DESIGN OBJECTIVES ===\n");

        String[][] goals = {
            {"Simple & object-oriented", "Familiar C-like syntax; OOP model; no manual memory/pointers"},
            {"Robust & secure",          "Strong typing, exceptions, GC, bytecode verification, sandbox"},
            {"Architecture-neutral",     "Bytecode runs on any JVM; fixed primitive sizes for portability"},
            {"High performance",         "JIT compiles hot code to native; modern GCs"},
            {"Interpreted/threaded/dynamic", "Fast startup, built-in concurrency, runtime class loading"}
        };
        for (String[] g : goals) System.out.printf("  - %-30s : %s%n", g[0], g[1]);

        System.out.println("\n=== WHY LEARN JAVA? ===");
        String[] reasons = {
            "One of the most used languages in enterprise, Android, big data, cloud",
            "Huge ecosystem & libraries; strong tooling and community",
            "Backward compatible; stable long-term-support (LTS) releases",
            "Great for learning OOP, data structures, and system design",
            "Excellent career & interview demand"
        };
        for (String r : reasons) System.out.println("  - " + r);

        System.out.println("\n=== HOW JAVAMASTERY IS ORGANIZED (numbered packages) ===");
        String[] pkgs = {
            "pkg0intro          : tutorial — about Java, history, objectives",
            "pkg1core           : core1HelloWorld ... core28ComparatorDemo",
            "pkg2versions       : versions1Java5Features ... versions6Java21Features",
            "pkg3datastructures : datastructures0DynamicArray ... 12UnionFind",
            "pkg4algorithms     : algorithms1SortingAlgorithms ... 7DivideAndConquer",
            "pkg5leetcode       : 249 solutions — blind75, official75, interview150, top100",
            "pkg6jvm            : jvm1ClassLoadingDemo ... 3GarbageCollectionDemo",
            "pkg7concurrency    : concurrency1ThreadBasics ... 8LocksAndCoordination",
            "pkg8patterns       : patterns1SingletonPattern ... 23VisitorPattern (23 GoF)",
            "pkg9io–pkg13libs    : I/O, networking, JDBC, REST, std libraries",
            "pkg14–pkg20         : testing, modules, metaprogramming, performance, serialization",
            "docs/              : tutorials, interview Q&A, reference — see README.md"
        };
        for (String p : pkgs) System.out.println("  - " + p);

        System.out.println("\nStart here, then follow the numbers. See README.md for all docs.");
        System.out.println("  java pkg1core/core1HelloWorld.java");
    }
}
