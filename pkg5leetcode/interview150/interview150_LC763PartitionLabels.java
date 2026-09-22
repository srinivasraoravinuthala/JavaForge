package pkg5leetcode.interview150;

/*
 * Partition Labels | LC 763
 * APPROACH: Track last index of each char; extend partition end.
 * COMPLEXITY: Time O(n), Space O(1)
 */
import java.util.*;

public class interview150_LC763PartitionLabels {
    static List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) last[s.charAt(i) - 'a'] = i;
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        check(partitionLabels("ababcbacaldefegdehijhklij").equals(Arrays.asList(9, 7, 8)), "case1");
        check(partitionLabels("eccbbbbdec").equals(Arrays.asList(10)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
