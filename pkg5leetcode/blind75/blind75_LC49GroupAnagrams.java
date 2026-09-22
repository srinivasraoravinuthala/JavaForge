package pkg5leetcode.blind75;

/*
 * Group Anagrams | LC 49
 * APPROACH: HashMap keyed by sorted char signature.
 * COMPLEXITY: Time O(n k log k), Space O(nk)
 */
import java.util.*;

public class blind75_LC49GroupAnagrams {
    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] a = s.toCharArray();
            Arrays.sort(a);
            String key = new String(a);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        List<List<String>> r = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        check(r.size() == 3, "size");
        check(r.stream().anyMatch(g -> g.size() == 3 && g.contains("eat")), "group");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
