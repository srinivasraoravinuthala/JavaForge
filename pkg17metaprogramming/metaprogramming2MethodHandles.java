package pkg17metaprogramming;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/*
 * metaprogramming2MethodHandles.java
 * ------------------------------------
 * MethodHandles: faster, type-safe reflective invocation (Java 7+).
 *
 * DEFINITION:
 *   MethodHandles (java.lang.invoke) are the low-level building blocks behind
 *   lambdas and invokedynamic. They are more efficient than raw Reflection.
 *
 * KEY POINTS:
 *   - Lookup.unreflect() or findVirtual/findStatic obtain a MethodHandle.
 *   - invoke() / invokeExact() call the target with correct types.
 *   - Used by the JVM for lambda generation and by libraries needing speed.
 */
public class metaprogramming2MethodHandles {

    static class Calculator {
        int add(int a, int b) { return a + b; }
        static int multiply(int a, int b) { return a * b; }
    }

    public static void main(String[] args) throws Throwable {
        Calculator calc = new Calculator();
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // Virtual method handle
        MethodHandle add = lookup.findVirtual(Calculator.class, "add",
                MethodType.methodType(int.class, int.class, int.class));
        int sum = (int) add.invoke(calc, 10, 32);
        System.out.println("add(10,32) via MethodHandle = " + sum);

        // Static method handle
        MethodHandle mul = lookup.findStatic(Calculator.class, "multiply",
                MethodType.methodType(int.class, int.class, int.class));
        int product = (int) mul.invoke(6, 7);
        System.out.println("multiply(6,7) via MethodHandle = " + product);

        // Bound handle (receiver fixed)
        MethodHandle boundAdd = add.bindTo(calc);
        System.out.println("bound add(1,2) = " + boundAdd.invoke(1, 2));
    }
}
