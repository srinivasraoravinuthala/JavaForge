package pkg5leetcode.blind75;

/*
 * Longest Consecutive Sequence | LC 128
 * APPROACH: HashSet; start streak only from sequence minimum.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC128LongestConsecutiveSequence {
    static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int best = 0;
        for (int x : set) {
            if (set.contains(x - 1)) continue;
            int len = 1;
            while (set.contains(x + len)) len++;
            best = Math.max(best, len);
        }
        return best;
    }

    public static void main(String[] args) {
        check(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}) == 4, "case1");
        check(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}) == 9, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
