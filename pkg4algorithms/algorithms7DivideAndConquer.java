package pkg4algorithms;

/*
 * algorithms7DivideAndConquer.java
 * --------------------------------
 * Divide-and-conquer template: split problem, solve subproblems, combine.
 *
 * Covered: binary search, merge sort, quickselect (kth smallest), max subarray (Kadane
 * is DP but divide-and-conquer variant shown), and power(x,n).
 *
 * COMPLEXITY: typically O(n log n) when halving + linear combine; binary search O(log n).
 */
import java.util.Arrays;

public class algorithms7DivideAndConquer {

    static int binarySearch(int[] a, int target, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == target) return mid;
        return a[mid] < target ? binarySearch(a, target, mid + 1, hi)
                               : binarySearch(a, target, lo, mid - 1);
    }

    static void mergeSort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        int[] tmp = new int[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) tmp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, 0, a, lo, tmp.length);
    }

    // Quickselect: average O(n), worst O(n^2)
    static int quickSelect(int[] a, int k) {
        return select(a, 0, a.length - 1, k);
    }

    static int select(int[] a, int lo, int hi, int k) {
        if (lo == hi) return a[lo];
        int p = partition(a, lo, hi);
        if (k == p) return a[p];
        if (k < p) return select(a, lo, p - 1, k);
        return select(a, p + 1, hi, k);
    }

    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi], i = lo - 1;
        for (int j = lo; j < hi; j++) if (a[j] <= pivot) swap(a, ++i, j);
        swap(a, i + 1, hi);
        return i + 1;
    }

    static long power(long x, int n) {
        if (n == 0) return 1;
        if (n % 2 == 0) {
            long half = power(x, n / 2);
            return half * half;
        }
        return x * power(x, n - 1);
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(a);
        System.out.println("binarySearch(5): index " + binarySearch(a, 5, 0, a.length - 1));

        int[] b = {9, 3, 7, 1, 8, 2, 6, 5, 4};
        mergeSort(b, 0, b.length - 1);
        System.out.println("mergeSort: " + Arrays.toString(b));

        int[] c = {9, 3, 7, 1, 8, 2, 6, 5, 4};
        System.out.println("quickSelect k=3 (4th smallest): " + quickSelect(c.clone(), 3));

        System.out.println("2^10 = " + power(2, 10));
    }
}
