package pkg5leetcode.official75;

/*
 * Online Stock Span | LC 901
 * APPROACH: Monotonic stack of price/index pairs.
 * COMPLEXITY: Time O(1) amortized, Space O(n)
 */
import java.util.*;

public class official75_LC901OnlineStockSpan {
    static class StockSpanner {
        Deque<int[]> st = new ArrayDeque<>();

        int next(int price) {
            int span = 1;
            while (!st.isEmpty() && st.peekLast()[0] <= price) span += st.pollLast()[1];
            st.addLast(new int[]{price, span});
            return span;
        }
    }

    public static void main(String[] args) {
        StockSpanner sp = new StockSpanner();
        check(sp.next(100) == 1, "case1");
        check(sp.next(80) == 1, "case2");
        check(sp.next(60) == 1, "case3");
        check(sp.next(70) == 2, "case4");
        check(sp.next(60) == 1, "case5");
        check(sp.next(75) == 4, "case6");
        check(sp.next(85) == 6, "case7");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
