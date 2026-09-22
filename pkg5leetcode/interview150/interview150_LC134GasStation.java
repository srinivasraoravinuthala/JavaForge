package pkg5leetcode.interview150;

/*
 * Gas Station | LC 134
 * APPROACH: Track total and running tank; start at index after deficit.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC134GasStation {
    static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) { start = i + 1; tank = 0; }
        }
        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        check(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}) == 3, "case1");
        check(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
