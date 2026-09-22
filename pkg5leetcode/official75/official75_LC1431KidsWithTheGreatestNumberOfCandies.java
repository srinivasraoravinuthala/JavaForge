package pkg5leetcode.official75;

/*
 * Kids With the Greatest Number of Candies | LC 1431
 * APPROACH: Compare each kid + extra against max existing.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC1431KidsWithTheGreatestNumberOfCandies {
    static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int c : candies) max = Math.max(max, c);
        List<Boolean> res = new ArrayList<>();
        for (int c : candies) res.add(c + extraCandies >= max);
        return res;
    }

    public static void main(String[] args) {
        check(kidsWithCandies(new int[]{2,3,5,1,3}, 3).equals(Arrays.asList(true,true,true,false,true)), "case1");
        check(kidsWithCandies(new int[]{4,2,1,1,2}, 1).equals(Arrays.asList(true,false,false,false,false)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
