package pkg5leetcode.blind75;

/*
 * Jump Game | LC 55
 * APPROACH: Greedy track farthest reachable index.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC55JumpGame {
    static boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) return false;
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        check(canJump(new int[]{2, 3, 1, 1, 4}), "case1");
        check(!canJump(new int[]{3, 2, 1, 0, 4}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
