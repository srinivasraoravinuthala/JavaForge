package pkg5leetcode.official75;

/*
 * Find the Difference of Two Arrays | LC 2215
 * APPROACH: Sets for unique elements in each direction.
 * COMPLEXITY: Time O(n+m), Space O(n+m)
 */
import java.util.*;

public class official75_LC2215FindTheDifferenceOfTwoArrays {
    static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
        for (int n : nums1) s1.add(n);
        for (int n : nums2) s2.add(n);
        List<Integer> a = new ArrayList<>(), b = new ArrayList<>();
        for (int n : s1) if (!s2.contains(n)) a.add(n);
        for (int n : s2) if (!s1.contains(n)) b.add(n);
        return Arrays.asList(a, b);
    }

    public static void main(String[] args) {
        List<List<Integer>> r = findDifference(new int[]{1,2,3}, new int[]{2,4,6});
        check(r.get(0).equals(Arrays.asList(1,3)) && r.get(1).equals(Arrays.asList(4,6)), "case1");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
