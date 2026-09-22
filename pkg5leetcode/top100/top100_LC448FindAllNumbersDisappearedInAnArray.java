package pkg5leetcode.top100;

/*
 * Find All Numbers Disappeared in an Array | LC 448
 * APPROACH: Mark indices using negation at value positions.
 * COMPLEXITY: Time O(n), Space O(1) excluding output
 */
import java.util.*;

public class top100_LC448FindAllNumbersDisappearedInAnArray {
    static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) nums[idx] = -nums[idx];
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0) res.add(i + 1);
        return res;
    }

    public static void main(String[] args) {
        check(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}).equals(Arrays.asList(5, 6)), "case1");
        check(findDisappearedNumbers(new int[]{1, 1}).equals(Arrays.asList(2)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
