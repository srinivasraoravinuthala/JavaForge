package pkg5leetcode.official75;

/*
 * Implement Trie (Prefix Tree) | LC 208
 * APPROACH: 26-child nodes per level; mark word ends.
 * COMPLEXITY: Time O(m) per op, Space O(total chars)
 */
public class official75_LC208ImplementTrie {
    static class Trie {
        Trie[] child = new Trie[26];
        boolean end;

        void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) node.child[i] = new Trie();
                node = node.child[i];
            }
            node.end = true;
        }

        boolean search(String word) {
            Trie node = find(word);
            return node != null && node.end;
        }

        boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        Trie find(String s) {
            Trie node = this;
            for (char c : s.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) return null;
                node = node.child[i];
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        check(trie.search("apple"), "case1");
        check(!trie.search("app"), "case2");
        check(trie.startsWith("app"), "prefix");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
