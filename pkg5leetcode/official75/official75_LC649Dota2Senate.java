package pkg5leetcode.official75;

/*
 * Dota2 Senate | LC 649
 * APPROACH: Two queues simulate ban order by index.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC649Dota2Senate {
    static String predictPartyVictory(String senate) {
        int n = senate.length();
        Deque<Integer> r = new ArrayDeque<>(), d = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (senate.charAt(i) == 'R') r.addLast(i); else d.addLast(i);
        while (!r.isEmpty() && !d.isEmpty()) {
            int ri = r.pollFirst(), di = d.pollFirst();
            if (ri < di) r.addLast(ri + n); else d.addLast(di + n);
        }
        return r.isEmpty() ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        check("Radiant".equals(predictPartyVictory("RD")), "case1");
        check("Dire".equals(predictPartyVictory("RDD")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
