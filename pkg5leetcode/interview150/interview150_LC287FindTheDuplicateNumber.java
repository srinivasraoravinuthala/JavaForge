package pkg5leetcode.interview150;

/*
 * Find the Duplicate Number | LC 287
 * APPROACH: Floyd cycle on index-as-next pointer graph.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC287FindTheDuplicateNumber {
    static int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        check(findDuplicate(new int[]{1, 3, 4, 2, 2}) == 2, "case1");
        check(findDuplicate(new int[]{3, 1, 3, 4, 2}) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
