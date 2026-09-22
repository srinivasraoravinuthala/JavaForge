package pkg4algorithms;

/*
 * algorithms2SearchingAlgorithms.java
 * ------------------------
 * Linear search, binary search (iterative + recursive), and two classic
 * "binary search on answer" variants: first/last occurrence.
 *
 * COMPLEXITY: linear O(n); binary O(log n) but requires a SORTED array.
 */
import java.util.Arrays;

public class algorithms2SearchingAlgorithms {

    static int linear(int[] a, int target) {
        for (int i = 0; i < a.length; i++) if (a[i] == target) return i;
        return -1;
    }

    static int binary(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;     // avoids overflow vs (lo+hi)/2
            if (a[mid] == target) return mid;
            if (a[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    static int binaryRecursive(int[] a, int target, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == target) return mid;
        return a[mid] < target ? binaryRecursive(a, target, mid + 1, hi)
                               : binaryRecursive(a, target, lo, mid - 1);
    }

    // First index where a[i] == target (handles duplicates)
    static int firstOccurrence(int[] a, int target) {
        int lo = 0, hi = a.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) { res = mid; hi = mid - 1; }   // keep searching left
            else if (a[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return res;
    }

    static int lastOccurrence(int[] a, int target) {
        int lo = 0, hi = a.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) { res = mid; lo = mid + 1; }   // keep searching right
            else if (a[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 3, 3, 5, 7, 9, 11};
        System.out.println("array: " + Arrays.toString(a));
        System.out.println("linear find 7: index " + linear(a, 7));
        System.out.println("binary find 9: index " + binary(a, 9));
        System.out.println("recursive find 1: index " + binaryRecursive(a, 1, 0, a.length - 1));
        System.out.println("find 13 (absent): " + binary(a, 13));
        System.out.println("first occ of 3: " + firstOccurrence(a, 3));
        System.out.println("last occ of 3:  " + lastOccurrence(a, 3));
    }
}
