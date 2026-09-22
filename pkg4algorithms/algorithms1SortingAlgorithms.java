package pkg4algorithms;

/*
 * algorithms1SortingAlgorithms.java
 * ----------------------
 * Bubble, selection, insertion, merge, quick, heap, and counting sort.
 *
 *  Algorithm     Best      Avg       Worst     Space   Stable
 *  ---------     ----      ---       -----     -----   ------
 *  Bubble        O(n)      O(n^2)    O(n^2)    O(1)    yes
 *  Selection     O(n^2)    O(n^2)    O(n^2)    O(1)    no
 *  Insertion     O(n)      O(n^2)    O(n^2)    O(1)    yes
 *  Merge         O(nlogn)  O(nlogn)  O(nlogn)  O(n)    yes
 *  Quick         O(nlogn)  O(nlogn)  O(n^2)    O(logn) no
 *  Heap          O(nlogn)  O(nlogn)  O(nlogn)  O(1)    no
 *  Counting      O(n+k)    O(n+k)    O(n+k)    O(k)    yes  (k = value range)
 */
import java.util.Arrays;

public class algorithms1SortingAlgorithms {

    static void bubble(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j] > a[j + 1]) { swap(a, j, j + 1); swapped = true; }
            if (!swapped) break;   // already sorted -> early exit
        }
    }

    static void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) if (a[j] < a[min]) min = j;
            swap(a, i, min);
        }
    }

    static void insertion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }

    static void mergeSort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        int[] tmp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) tmp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= m) tmp[k++] = a[i++];
        while (j <= r) tmp[k++] = a[j++];
        System.arraycopy(tmp, 0, a, l, tmp.length);
    }

    static void quickSort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, lo, hi);
        quickSort(a, lo, p - 1);
        quickSort(a, p + 1, hi);
    }
    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi], i = lo - 1;          // Lomuto partition
        for (int j = lo; j < hi; j++) if (a[j] < pivot) swap(a, ++i, j);
        swap(a, i + 1, hi);
        return i + 1;
    }

    static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, n, i);   // build max-heap
        for (int end = n - 1; end > 0; end--) {                   // repeatedly extract max
            swap(a, 0, end);
            siftDown(a, end, 0);
        }
    }
    static void siftDown(int[] a, int n, int i) {
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2, largest = i;
            if (l < n && a[l] > a[largest]) largest = l;
            if (r < n && a[r] > a[largest]) largest = r;
            if (largest == i) break;
            swap(a, i, largest); i = largest;
        }
    }

    /** Counting sort for non-negative integers in range [0, max]. */
    static void countingSort(int[] a, int max) {
        int[] count = new int[max + 1];
        for (int v : a) count[v]++;
        int i = 0;
        for (int v = 0; v <= max; v++)
            for (int c = 0; c < count[v]; c++)
                a[i++] = v;
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void main(String[] args) {
        int[] base = {9, 3, 7, 1, 8, 2, 6, 5, 4};
        int[] b;
        b = base.clone(); bubble(b);        System.out.println("bubble:    " + Arrays.toString(b));
        b = base.clone(); selection(b);     System.out.println("selection: " + Arrays.toString(b));
        b = base.clone(); insertion(b);     System.out.println("insertion: " + Arrays.toString(b));
        b = base.clone(); mergeSort(b, 0, b.length - 1); System.out.println("merge:     " + Arrays.toString(b));
        b = base.clone(); quickSort(b, 0, b.length - 1); System.out.println("quick:     " + Arrays.toString(b));
        b = base.clone(); heapSort(b);      System.out.println("heap:      " + Arrays.toString(b));
        b = new int[]{4, 2, 2, 8, 3, 3, 1};
        countingSort(b, 8);                  System.out.println("counting:  " + Arrays.toString(b));
    }
}
