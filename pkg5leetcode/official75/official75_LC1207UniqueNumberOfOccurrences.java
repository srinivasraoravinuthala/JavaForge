package pkg5leetcode.official75;

/*
 * Unique Number of Occurrences | LC 1207
 * APPROACH: Count frequencies; set size equals max frequency count.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC1207UniqueNumberOfOccurrences {
    static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) freq.put(n, freq.getOrDefault(n, 0) + 1);
        Set<Integer> seen = new HashSet<>();
        for (int c : freq.values()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        check(uniqueOccurrences(new int[]{1,2,2,1,1,3}), "case1");
        check(!uniqueOccurrences(new int[]{1,2}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
