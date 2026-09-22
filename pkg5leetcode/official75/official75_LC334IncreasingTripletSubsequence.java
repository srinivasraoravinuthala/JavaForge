package pkg5leetcode.official75;

/*
 * Increasing Triplet Subsequence | LC 334
 * APPROACH: Track smallest and second smallest seen so far.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC334IncreasingTripletSubsequence {
    static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) first = n;
            else if (n <= second) second = n;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        check(increasingTriplet(new int[]{1,2,3,4,5}), "case1");
        check(!increasingTriplet(new int[]{5,4,3,2,1}), "case2");
        check(increasingTriplet(new int[]{2,1,5,0,4,6}), "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
