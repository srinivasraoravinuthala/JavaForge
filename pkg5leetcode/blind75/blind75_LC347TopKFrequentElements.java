package pkg5leetcode.blind75;

/*
 * Top K Frequent Elements | LC 347
 * APPROACH: Count frequencies; min-heap of size k by frequency.
 * COMPLEXITY: Time O(n log k), Space O(n)
 */
import java.util.*;

public class blind75_LC347TopKFrequentElements {
    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            pq.offer(new int[]{e.getKey(), e.getValue()});
            if (pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = pq.poll()[0];
        return res;
    }

    public static void main(String[] args) {
        int[] r = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(r);
        check(Arrays.equals(r, new int[]{1, 2}), "case1");
        check(topKFrequent(new int[]{1}, 1)[0] == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
