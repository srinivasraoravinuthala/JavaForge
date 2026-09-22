package pkg5leetcode.blind75;

/*
 * 3Sum | LC 15
 * APPROACH: Sort, fix i, two-pointer scan for triplets summing to zero.
 * COMPLEXITY: Time O(n^2), Space O(1) excluding output
 */
import java.util.*;

public class blind75_LC15ThreeSum {
    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    lo++; hi--;
                    while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
                } else if (sum < 0) lo++;
                else hi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> r = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        check(r.size() == 2, "size");
        check(r.contains(Arrays.asList(-1, -1, 2)), "triplet1");
        check(r.contains(Arrays.asList(-1, 0, 1)), "triplet2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
