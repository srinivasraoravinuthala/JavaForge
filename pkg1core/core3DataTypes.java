package pkg1core;

/*
 * core3DataTypes.java
 * --------------
 * The 8 primitive types, their ranges, wrappers, casting, and overflow.
 *
 * EXPLANATION:
 *  - Primitives store raw values directly (fast, no object overhead).
 *  - Each primitive has a wrapper class (Integer, Double, ...) used in generics/collections.
 *  - Autoboxing converts primitive <-> wrapper automatically.
 *  - Widening (int->long) is implicit; narrowing (long->int) needs a cast and may lose data.
 */
public class core3DataTypes {
    public static void main(String[] args) {
        byte   b = 127;                 // 8-bit
        short  s = 32_000;              // 16-bit (underscores improve readability)
        int    i = 2_000_000_000;       // 32-bit
        long   l = 9_000_000_000L;      // 64-bit (L suffix)
        float  f = 3.14f;               // 32-bit (f suffix)
        double d = 3.141592653589793;   // 64-bit
        char   c = 'J';                 // 16-bit UTF-16
        boolean flag = true;

        System.out.println("byte=" + b + " short=" + s + " int=" + i + " long=" + l);
        System.out.println("float=" + f + " double=" + d + " char=" + c + " boolean=" + flag);

        // Ranges via wrapper constants
        System.out.println("int range: " + Integer.MIN_VALUE + " .. " + Integer.MAX_VALUE);
        System.out.println("long max:  " + Long.MAX_VALUE);

        // Overflow: wraps around silently (a classic interview gotcha)
        int max = Integer.MAX_VALUE;
        System.out.println("MAX_VALUE + 1 overflows to: " + (max + 1));

        // Widening (implicit) and narrowing (explicit cast)
        long widened = i;               // int -> long, safe
        int narrowed = (int) l;         // long -> int, may lose data
        System.out.println("widened=" + widened + " narrowed=" + narrowed);

        // Autoboxing / unboxing
        Integer boxed = i;              // int -> Integer
        int unboxed = boxed;           // Integer -> int
        System.out.println("boxed=" + boxed + " unboxed=" + unboxed);

        // Integer cache gotcha: values -128..127 are cached
        Integer x = 127, y = 127, p = 128, q = 128;
        System.out.println("127==127 (cached): " + (x == y));
        System.out.println("128==128 (not cached): " + (p == q) + "  use .equals(): " + p.equals(q));
    }
}
