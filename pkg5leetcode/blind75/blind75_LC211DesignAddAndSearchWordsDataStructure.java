package pkg5leetcode.blind75;

/*
 * Design Add and Search Words Data Structure | LC 211
 * APPROACH: Trie insert; DFS search with '.' wildcard branches.
 * COMPLEXITY: Time O(m) insert, O(26^d) search worst case
 */
public class blind75_LC211DesignAddAndSearchWordsDataStructure {
    static class WordDictionary {
        static class Node {
            Node[] child = new Node[26];
            boolean end;
        }

        Node root = new Node();

        void addWord(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) node.child[i] = new Node();
                node = node.child[i];
            }
            node.end = true;
        }

        boolean search(String word) {
            return dfs(root, word, 0);
        }

        boolean dfs(Node node, String word, int idx) {
            if (node == null) return false;
            if (idx == word.length()) return node.end;
            char c = word.charAt(idx);
            if (c == '.') {
                for (Node ch : node.child) if (dfs(ch, word, idx + 1)) return true;
                return false;
            }
            return dfs(node.child[c - 'a'], word, idx + 1);
        }
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        check(!wd.search("pad"), "case1");
        check(wd.search("bad"), "case2");
        check(wd.search(".ad"), "wildcard");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
