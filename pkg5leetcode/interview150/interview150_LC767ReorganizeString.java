package pkg5leetcode.interview150;

/*
 * Reorganize String | LC 767
 * APPROACH: Max heap by frequency; place most frequent with gap.
 * COMPLEXITY: Time O(n log k), Space O(k)
 */
import java.util.*;

public class interview150_LC767ReorganizeString {
    static String reorganizeString(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) if (cnt[i] > 0) pq.offer(new int[]{i, cnt[i]});
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == (char) ('a' + a[0])) {
                if (pq.isEmpty()) return "";
                int[] b = pq.poll();
                sb.append((char) ('a' + b[0]));
                if (--b[1] > 0) pq.offer(b);
                pq.offer(a);
            } else {
                sb.append((char) ('a' + a[0]));
                if (--a[1] > 0) pq.offer(a);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        check(reorganizeString("aab").equals("aba"), "case1");
        check(reorganizeString("aaab").equals(""), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
