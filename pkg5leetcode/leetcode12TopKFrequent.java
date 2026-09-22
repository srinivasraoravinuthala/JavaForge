package pkg5leetcode;

/*
 * LeetCode 347: Top K Frequent Elements  (Medium)
 * ------------------------------------------------
 * Return the k most frequent elements.
 *
 * APPROACH: count frequencies, then bucket sort by frequency (index = count).
 * COMPLEXITY: Time O(n), Space O(n).  (A heap gives O(n log k).)
 */
import java.util.*;

public class leetcode12TopKFrequent {

    @SuppressWarnings("unchecked")
    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        // buckets[i] = list of numbers that appear i times
        List<Integer>[] buckets = new List[nums.length + 1];
        for (var e : freq.entrySet()) {
            int c = e.getValue();
            if (buckets[c] == null) buckets[c] = new ArrayList<>();
            buckets[c].add(e.getKey());
        }

        int[] res = new int[k];
        int idx = 0;
        for (int c = buckets.length - 1; c >= 0 && idx < k; c--) {
            if (buckets[c] == null) continue;
            for (int num : buckets[c]) {
                if (idx == k) break;
                res[idx++] = num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] r = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(r);
        check(Arrays.equals(r, new int[]{1, 2}), "k=2");
        check(Arrays.equals(topKFrequent(new int[]{1}, 1), new int[]{1}), "single");
        System.out.println("leetcode12TopKFrequent: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
