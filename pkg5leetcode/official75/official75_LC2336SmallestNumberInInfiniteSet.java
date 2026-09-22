package pkg5leetcode.official75;

/*
 * Smallest Number in Infinite Set | LC 2336
 * APPROACH: TreeSet for removed numbers; counter for next fresh.
 * COMPLEXITY: Time O(log n) per op, Space O(n)
 */
import java.util.*;

public class official75_LC2336SmallestNumberInInfiniteSet {
    static class SmallestInfiniteSet {
        TreeSet<Integer> removed = new TreeSet<>();
        int next = 1;

        int popSmallest() {
            if (!removed.isEmpty()) {
                int v = removed.pollFirst();
                return v;
            }
            return next++;
        }

        void addBack(int num) {
            if (num < next && removed.add(num)) { /* restored */ }
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet();
        set.addBack(2);
        check(set.popSmallest() == 1, "case1");
        check(set.popSmallest() == 2, "case2");
        check(set.popSmallest() == 3, "case3");
        set.addBack(1);
        check(set.popSmallest() == 1, "case4");
        check(set.popSmallest() == 4, "case5");
        check(set.popSmallest() == 5, "case6");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
