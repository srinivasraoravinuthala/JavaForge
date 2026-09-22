package pkg17metaprogramming;

import java.lang.reflect.Method;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/*
 * metaprogramming5ReflectionVsHandles.java
 * ------------------------------------------
 * Reflection vs MethodHandles: when to use each.
 *
 * DEFINITION:
 *   Reflection (java.lang.reflect) is flexible but slower and less type-safe.
 *   MethodHandles are the JVM's preferred dynamic invocation path — faster and
 *   enforce type signatures at lookup time.
 *
 * KEY POINTS:
 *   - Reflection: Method.invoke() — easy, works everywhere, slower.
 *   - MethodHandles: invoke()/invokeExact() — faster, used by lambdas.
 *   - Security: modules can block deep reflection on non-exported packages.
 *   - For frameworks at scale: prefer MethodHandles + bytecode (ASM/Byte Buddy).
 */
public class metaprogramming5ReflectionVsHandles {

    static class Target {
        String shout(String msg) { return msg.toUpperCase(); }
    }

    public static void main(String[] args) throws Throwable {
        Target t = new Target();
        String input = "hello";

        // Reflection
        long rStart = System.nanoTime();
        Method m = Target.class.getDeclaredMethod("shout", String.class);
        m.setAccessible(true);
        for (int i = 0; i < 100_000; i++) m.invoke(t, input);
        long rNanos = System.nanoTime() - rStart;

        // MethodHandle
        MethodHandle mh = MethodHandles.lookup().findVirtual(
                Target.class, "shout", MethodType.methodType(String.class, String.class));
        long hStart = System.nanoTime();
        for (int i = 0; i < 100_000; i++) mh.invoke(t, input);
        long hNanos = System.nanoTime() - hStart;

        System.out.println("Reflection 100k invokes: " + (rNanos / 1_000_000) + " ms");
        System.out.println("MethodHandle 100k invokes: " + (hNanos / 1_000_000) + " ms");
        System.out.println("(MethodHandle often faster; direct calls are fastest of all)");
    }
}
