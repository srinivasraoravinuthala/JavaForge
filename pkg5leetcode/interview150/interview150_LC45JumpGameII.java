package pkg5leetcode.interview150;

/*
 * Jump Game II | LC 45
 * APPROACH: Greedy BFS layers; count jumps when reaching current layer end.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC45JumpGameII {
    static int jump(int[] nums) {
        int jumps = 0, end = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) { jumps++; end = farthest; }
        }
        return jumps;
    }

    public static void main(String[] args) {
        check(jump(new int[]{2, 3, 1, 1, 4}) == 2, "case1");
        check(jump(new int[]{2, 1, 1, 1, 1}) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
