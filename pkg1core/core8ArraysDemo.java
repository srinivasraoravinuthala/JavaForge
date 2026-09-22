package pkg1core;

/*
 * core8ArraysDemo.java
 * ---------------
 * 1D and 2D arrays, initialization, iteration, and java.util.Arrays utilities.
 *
 * EXPLANATION:
 *  - Arrays are fixed-size, zero-indexed, and store one type.
 *  - `arr.length` is a field (not a method).
 *  - java.util.Arrays provides sort, binarySearch, fill, copyOf, toString, etc.
 */
import java.util.Arrays;

public class core8ArraysDemo {
    public static void main(String[] args) {
        // Declaration + initialization
        int[] a = {5, 3, 8, 1, 9, 2};
        int[] b = new int[3];          // defaults to all zeros
        b[0] = 10; b[1] = 20; b[2] = 30;

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b) + " (length " + b.length + ")");

        // Sorting + binary search (array must be sorted for search)
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        System.out.println("sorted = " + Arrays.toString(sorted));
        System.out.println("index of 8 = " + Arrays.binarySearch(sorted, 8));

        // Fill and copy
        int[] filled = new int[4];
        Arrays.fill(filled, 7);
        System.out.println("filled = " + Arrays.toString(filled));
        System.out.println("copyOf(a,3) = " + Arrays.toString(Arrays.copyOf(a, 3)));

        // 2D array (array of arrays)
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("2D grid:");
        for (int[] row : grid) {
            System.out.println("  " + Arrays.toString(row));
        }
        System.out.println("grid[1][2] = " + grid[1][2]);

        // Manual reverse in place
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        System.out.println("reversed a = " + Arrays.toString(a));
    }
}
