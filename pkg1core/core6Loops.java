package pkg1core;

/*
 * core6Loops.java
 * ----------
 * for, enhanced for (for-each), while, do-while, labels, break, continue.
 *
 * EXPLANATION:
 *  - Use a classic `for` when you need the index.
 *  - Use for-each to iterate elements cleanly.
 *  - `break`/`continue` can target a labeled loop to control nesting.
 */
public class core6Loops {
    public static void main(String[] args) {
        // Classic for
        System.out.print("for: ");
        for (int i = 1; i <= 5; i++) System.out.print(i + " ");
        System.out.println();

        // for-each over an array
        int[] nums = {10, 20, 30};
        System.out.print("for-each: ");
        for (int n : nums) System.out.print(n + " ");
        System.out.println();

        // while
        System.out.print("while countdown: ");
        int c = 3;
        while (c > 0) { System.out.print(c + " "); c--; }
        System.out.println();

        // do-while (body runs at least once)
        int x = 0;
        do { System.out.println("do-while runs once even though x=" + x); } while (x > 0);

        // continue: skip even numbers
        System.out.print("odd numbers: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();

        // Labeled break: exit nested loops at once
        System.out.println("labeled break (find first pair summing to 7):");
        outer:
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (i + j == 7) {
                    System.out.println("  found i=" + i + " j=" + j);
                    break outer;
                }
            }
        }
    }
}
