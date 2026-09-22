package pkg1core;

/*
 * core7Methods.java
 * ------------
 * Defining methods: parameters, return values, overloading, varargs,
 * recursion, and pass-by-value semantics.
 *
 * EXPLANATION:
 *  - Java is ALWAYS pass-by-value. For objects, the VALUE passed is the
 *    reference (so you can mutate the object, but reassigning the param
 *    doesn't affect the caller).
 *  - Overloading = same name, different parameter lists (compile-time).
 *  - Varargs (Type...) accept zero or more arguments as an array.
 */
public class core7Methods {

    static int add(int a, int b) { return a + b; }              // basic
    static double add(double a, double b) { return a + b; }     // overload
    static int sum(int... values) {                             // varargs
        int total = 0;
        for (int v : values) total += v;
        return total;
    }
    static long factorial(int n) {                              // recursion
        if (n <= 1) return 1;                                   // base case
        return n * factorial(n - 1);                            // recursive step
    }
    static void tryReassign(int[] arr) {
        arr[0] = 99;          // mutates the caller's array (same object)
        arr = new int[]{0};   // reassigning the local param does NOT affect caller
    }

    public static void main(String[] args) {
        System.out.println("add(2,3)=" + add(2, 3));
        System.out.println("add(2.5,3.5)=" + add(2.5, 3.5));
        System.out.println("sum()=" + sum() + " sum(1,2,3,4)=" + sum(1, 2, 3, 4));
        System.out.println("factorial(5)=" + factorial(5));

        int[] data = {1, 2, 3};
        tryReassign(data);
        System.out.println("after tryReassign data[0]=" + data[0] + " (mutated), length=" + data.length + " (unchanged)");
    }
}
