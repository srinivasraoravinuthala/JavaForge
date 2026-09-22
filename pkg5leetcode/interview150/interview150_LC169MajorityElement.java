package pkg5leetcode.interview150;

/*
 * Majority Element | LC 169
 * APPROACH: Boyer-Moore voting finds candidate majority.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC169MajorityElement {
    static int majorityElement(int[] nums) {
        int cand = 0, count = 0;
        for (int x : nums) {
            if (count == 0) { cand = x; count = 1; }
            else count += x == cand ? 1 : -1;
        }
        return cand;
    }

    public static void main(String[] args) {
        check(majorityElement(new int[]{3, 2, 3}) == 3, "case1");
        check(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
