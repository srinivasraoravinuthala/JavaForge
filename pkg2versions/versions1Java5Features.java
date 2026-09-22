package pkg2versions;

/*
 * versions1Java5Features.java  (J2SE 5.0, 2004)
 * ------------------------------------
 * Landmark release that modernized the language.
 *
 * FEATURES & WHY:
 *  - Generics        : compile-time type safety, removes casts.
 *  - Enhanced for    : cleaner iteration.
 *  - Autoboxing      : seamless primitive <-> wrapper conversion.
 *  - Varargs         : variable-length argument lists.
 *  - Enums           : type-safe constants (vs int constants).
 *  - Annotations     : metadata (@Override etc.).
 *  - Static import   : import static members.
 */
import static java.lang.Math.PI;
import java.util.*;

public class versions1Java5Features {

    enum Level { LOW, MEDIUM, HIGH }                 // enum

    static <T> List<T> listOf(T... items) {          // generics + varargs
        return new ArrayList<T>(Arrays.asList(items));
    }

    public static void main(String[] args) {
        // Generics + enhanced for
        List<String> names = listOf("Alice", "Bob", "Cara");
        for (String n : names) System.out.print(n + " ");
        System.out.println();

        // Autoboxing: int -> Integer automatically inside the List
        List<Integer> nums = listOf(1, 2, 3);
        int sum = 0;
        for (int n : nums) sum += n;                 // unboxing
        System.out.println("sum=" + sum);

        // Enum
        for (Level l : Level.values()) System.out.print(l + " ");
        System.out.println();

        // Static import
        System.out.println("PI=" + PI);
    }
}
