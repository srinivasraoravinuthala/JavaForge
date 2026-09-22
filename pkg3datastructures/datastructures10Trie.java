package pkg3datastructures;

/*
 * datastructures10Trie.java  (Prefix Tree)
 * ------------------------
 * Stores strings by shared prefixes. Excellent for autocomplete, spell-check,
 * and prefix queries.
 *
 * COMPLEXITY: insert/search/startsWith O(L) where L = word length.
 * SPACE: up to O(alphabet * nodes); great when many words share prefixes.
 */
import java.util.*;

public class datastructures10Trie {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }

    private final TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray())
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        node.isWord = true;
    }

    boolean search(String word) {
        TrieNode node = walk(word);
        return node != null && node.isWord;
    }

    boolean startsWith(String prefix) { return walk(prefix) != null; }

    private TrieNode walk(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return null;
        }
        return node;
    }

    // Collect all words with a given prefix (autocomplete)
    List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode start = walk(prefix);
        if (start != null) dfs(start, new StringBuilder(prefix), results);
        return results;
    }

    private void dfs(TrieNode node, StringBuilder path, List<String> out) {
        if (node.isWord) out.add(path.toString());
        for (Map.Entry<Character, TrieNode> e : node.children.entrySet()) {
            path.append(e.getKey());
            dfs(e.getValue(), path, out);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        datastructures10Trie trie = new datastructures10Trie();
        for (String w : new String[]{"cat", "car", "card", "dog", "do", "done"}) trie.insert(w);

        System.out.println("search 'car': " + trie.search("car"));
        System.out.println("search 'ca':  " + trie.search("ca"));
        System.out.println("startsWith 'ca': " + trie.startsWith("ca"));
        System.out.println("autocomplete 'ca': " + trie.autocomplete("ca"));
        System.out.println("autocomplete 'do': " + trie.autocomplete("do"));
    }
}
