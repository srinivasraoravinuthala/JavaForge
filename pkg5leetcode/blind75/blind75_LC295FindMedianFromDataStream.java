package pkg5leetcode.blind75;

/*
 * Find Median from Data Stream | LC 295
 * APPROACH: Two heaps: max-heap lower half, min-heap upper half balanced.
 * COMPLEXITY: addNum O(log n), findMedian O(1)
 */
import java.util.*;

public class blind75_LC295FindMedianFromDataStream {
    static class MedianFinder {
        PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> hi = new PriorityQueue<>();

        void addNum(int num) {
            lo.offer(num);
            hi.offer(lo.poll());
            if (lo.size() < hi.size()) lo.offer(hi.poll());
        }

        double findMedian() {
            if (lo.size() > hi.size()) return lo.peek();
            return (lo.peek() + hi.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        check(mf.findMedian() == 1.5, "case1");
        mf.addNum(3);
        check(mf.findMedian() == 2.0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
