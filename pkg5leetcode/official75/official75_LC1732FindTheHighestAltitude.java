package pkg5leetcode.official75;

/*
 * Find the Highest Altitude | LC 1732
 * APPROACH: Prefix sum track max altitude.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC1732FindTheHighestAltitude {
    static int largestAltitude(int[] gain) {
        int alt = 0, best = 0;
        for (int g : gain) {
            alt += g;
            best = Math.max(best, alt);
        }
        return best;
    }

    public static void main(String[] args) {
        check(largestAltitude(new int[]{-5,1,5,0,-7}) == 1, "case1");
        check(largestAltitude(new int[]{-4,-3,-2,-1}) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
