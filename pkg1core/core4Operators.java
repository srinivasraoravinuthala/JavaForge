package pkg1core;

/*
 * core4Operators.java
 * --------------
 * Arithmetic, relational, logical, bitwise, assignment, and ternary operators.
 *
 * EXPLANATION:
 *  - Integer division truncates; modulo gives the remainder.
 *  - Logical && and || short-circuit (right side may not evaluate).
 *  - Bitwise operators work on the binary representation (great for flags/masks).
 */
public class core4Operators {
    public static void main(String[] args) {
        int a = 17, b = 5;

        // Arithmetic
        System.out.println("a+b=" + (a + b) + " a-b=" + (a - b) + " a*b=" + (a * b));
        System.out.println("a/b=" + (a / b) + " (integer division truncates)");
        System.out.println("a%b=" + (a % b) + " (remainder)");

        // Relational
        System.out.println("a>b=" + (a > b) + " a==b=" + (a == b));

        // Logical with short-circuit demo
        System.out.println("short-circuit && : " + (b != 0 && a / b > 2));

        // Bitwise / shifts
        System.out.println("a & b = " + (a & b));   // AND
        System.out.println("a | b = " + (a | b));   // OR
        System.out.println("a ^ b = " + (a ^ b));   // XOR
        System.out.println("~a    = " + (~a));      // NOT
        System.out.println("a<<1  = " + (a << 1) + " (multiply by 2)");
        System.out.println("a>>1  = " + (a >> 1) + " (divide by 2)");

        // Increment / decrement (pre vs post)
        int n = 5;
        System.out.println("n++ returns " + (n++) + ", then n=" + n);
        System.out.println("++n returns " + (++n) + ", n=" + n);

        // Compound assignment
        int total = 10;
        total += 5; total *= 2;
        System.out.println("compound assignment total=" + total);

        // Ternary
        String parity = (a % 2 == 0) ? "even" : "odd";
        System.out.println("a is " + parity);
    }
}
