package pkg3datastructures;

/*
 * datastructures5MinHeap.java
 * ------------
 * A binary min-heap (array-based) supporting insert (sift-up) and
 * extractMin (sift-down). Foundation of priority queues and heap sort.
 *
 * COMPLEXITY: insert O(log n), extractMin O(log n), peek O(1), build O(n).
 * INDEXING: parent(i)=(i-1)/2, left(i)=2i+1, right(i)=2i+2.
 * WHEN TO USE: top-K, Dijkstra, scheduling, median maintenance.
 */
import java.util.*;

public class datastructures5MinHeap {

    private final List<Integer> heap = new ArrayList<>();

    void insert(int v) {
        heap.add(v);
        siftUp(heap.size() - 1);
    }

    int peek() {
        if (heap.isEmpty()) throw new NoSuchElementException("heap empty");
        return heap.get(0);
    }

    int extractMin() {
        if (heap.isEmpty()) throw new NoSuchElementException("heap empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) { heap.set(0, last); siftDown(0); }
        return min;
    }

    int size() { return heap.size(); }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) >= heap.get(parent)) break;
            swap(i, parent);
            i = parent;
        }
    }

    private void siftDown(int i) {
        int n = heap.size();
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, smallest = i;
            if (left < n && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < n && heap.get(right) < heap.get(smallest)) smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int a, int b) {
        int t = heap.get(a); heap.set(a, heap.get(b)); heap.set(b, t);
    }

    public static void main(String[] args) {
        datastructures5MinHeap h = new datastructures5MinHeap();
        int[] input = {5, 2, 8, 1, 9, 3, 7};
        for (int x : input) h.insert(x);
        System.out.println("min element: " + h.peek());

        StringBuilder sorted = new StringBuilder();
        while (h.size() > 0) sorted.append(h.extractMin()).append(' ');
        System.out.println("extracted ascending: " + sorted.toString().trim());

        // Built-in PriorityQueue for comparison (max-heap via comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : input) maxHeap.offer(x);
        System.out.println("max element (built-in): " + maxHeap.peek());
    }
}
