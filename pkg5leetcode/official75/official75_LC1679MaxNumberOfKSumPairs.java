package pkg5leetcode.official75;

/*
 * Max Number of K-Sum Pairs | LC 1679
 * APPROACH: Hash map counts complements for k-sum pairs.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC1679MaxNumberOfKSumPairs {
    static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int pairs = 0;
        for (int n : nums) {
            int need = k - n;
            if (freq.getOrDefault(need, 0) > 0) {
                freq.put(need, freq.get(need) - 1);
                pairs++;
            } else {
                freq.put(n, freq.getOrDefault(n, 0) + 1);
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        check(maxOperations(new int[]{1,2,3,4}, 5) == 2, "case1");
        check(maxOperations(new int[]{3,1,3,4,3}, 6) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
