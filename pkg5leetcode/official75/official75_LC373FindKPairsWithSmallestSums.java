package pkg5leetcode.official75;

/*
 * Find K Pairs with Smallest Sums | LC 373
 * APPROACH: Min-heap seed (0,j); expand next column pairs.
 * COMPLEXITY: Time O(k log k), Space O(k)
 */
import java.util.*;

public class official75_LC373FindKPairsWithSmallestSums {
    static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        for (int j = 0; j < Math.min(nums2.length, k); j++) pq.offer(new int[]{0, j});
        while (!pq.isEmpty() && res.size() < k) {
            int[] cur = pq.poll();
            res.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));
            if (cur[0] + 1 < nums1.length) pq.offer(new int[]{cur[0] + 1, cur[1]});
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> r = kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3);
        check(r.equals(Arrays.asList(Arrays.asList(1,2), Arrays.asList(1,4), Arrays.asList(1,6))), "case1");
        List<List<Integer>> r2 = kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2);
        check(r2.equals(Arrays.asList(Arrays.asList(1,1), Arrays.asList(1,1))), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
