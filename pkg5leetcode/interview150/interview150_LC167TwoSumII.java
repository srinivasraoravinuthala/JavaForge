package pkg5leetcode.interview150;

/*
 * Two Sum II | LC 167
 * APPROACH: Sorted array two pointers from both ends.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC167TwoSumII {
    static int[] twoSum(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum == target) return new int[]{lo + 1, hi + 1};
            if (sum < target) lo++;
            else hi--;
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2}), "case1");
        check(java.util.Arrays.equals(twoSum(new int[]{2, 3, 4}, 6), new int[]{1, 3}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
