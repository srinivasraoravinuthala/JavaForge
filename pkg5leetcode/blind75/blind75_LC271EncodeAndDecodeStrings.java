package pkg5leetcode.blind75;

/*
 * Encode and Decode Strings | LC 271
 * APPROACH: Length-prefix encoding: len#payload for each string.
 * COMPLEXITY: Time O(total chars), Space O(total chars)
 */
import java.util.*;

public class blind75_LC271EncodeAndDecodeStrings {
    static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(s.length()).append('#').append(s);
        return sb.toString();
    }

    static List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.indexOf('#', i);
            int len = Integer.parseInt(s.substring(i, j));
            i = j + 1;
            res.add(s.substring(i, i + len));
            i += len;
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> in = Arrays.asList("hello", "world", "leet#code");
        List<String> out = decode(encode(in));
        check(out.equals(in), "case1");
        List<String> empty = Arrays.asList("");
        check(decode(encode(empty)).equals(empty), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
