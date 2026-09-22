package pkg5leetcode.official75;

/*
 * Container With Most Water | LC 11
 * APPROACH: Two pointers move shorter height inward.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC11ContainerWithMostWater {
    static int maxArea(int[] height) {
        int l = 0, r = height.length - 1, best = 0;
        while (l < r) {
            best = Math.max(best, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) l++; else r--;
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxArea(new int[]{1,8,6,2,5,4,8,3,7}) == 49, "case1");
        check(maxArea(new int[]{1,1}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
