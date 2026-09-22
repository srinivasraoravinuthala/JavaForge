package pkg5leetcode.official75;

/*
 * Kth Largest Element in an Array | LC 215
 * APPROACH: Min-heap of size k.
 * COMPLEXITY: Time O(n log k), Space O(k)
 */
import java.util.*;

public class official75_LC215KthLargestElementInAnArray {
    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        check(findKthLargest(new int[]{3,2,1,5,6,4}, 2) == 5, "case1");
        check(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
