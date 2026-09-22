package pkg5leetcode.interview150;

/*
 * Trapping Rain Water | LC 42
 * APPROACH: Two pointers track left/right max; accumulate trapped water.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC42TrappingRainWater {
    static int trap(int[] height) {
        int l = 0, r = height.length - 1, lMax = 0, rMax = 0, water = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                lMax = Math.max(lMax, height[l]);
                water += lMax - height[l];
                l++;
            } else {
                rMax = Math.max(rMax, height[r]);
                water += rMax - height[r];
                r--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        check(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) == 6, "case1");
        check(trap(new int[]{4, 2, 0, 3, 2, 5}) == 9, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
