package pkg5leetcode.official75;

/*
 * Search Suggestions System | LC 1268
 * APPROACH: Trie DFS collect up to 3 words per prefix.
 * COMPLEXITY: Time O(n * avg_len), Space O(total chars)
 */
import java.util.*;

public class official75_LC1268SearchSuggestionsSystem {
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        List<String> words = new ArrayList<>();
    }

    static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        TrieNode root = new TrieNode();
        for (String p : products) {
            TrieNode node = root;
            for (char c : p.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) node.child[i] = new TrieNode();
                node = node.child[i];
                if (node.words.size() < 3) node.words.add(p);
            }
        }
        List<List<String>> res = new ArrayList<>();
        TrieNode node = root;
        for (char c : searchWord.toCharArray()) {
            if (node == null) { res.add(Collections.emptyList()); continue; }
            node = node.child[c - 'a'];
            res.add(node == null ? Collections.emptyList() : node.words);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> r = suggestedProducts(
                new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
        check(r.get(0).equals(Arrays.asList("mobile","moneypot","monitor")), "case1");
        check(r.get(3).equals(Arrays.asList("mouse","mousepad")), "case4");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
