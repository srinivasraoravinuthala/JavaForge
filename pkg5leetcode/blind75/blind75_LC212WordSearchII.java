package pkg5leetcode.blind75;

/*
 * Word Search II | LC 212
 * APPROACH: Trie of words + DFS on board pruning by trie paths.
 * COMPLEXITY: Time O(mn * 4^L), Space O(total word chars)
 */
import java.util.*;

public class blind75_LC212WordSearchII {
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    static TrieNode root = new TrieNode();

    static void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.child[i] == null) node.child[i] = new TrieNode();
            node = node.child[i];
        }
        node.word = word;
    }

    static List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        for (String w : words) insert(w);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, i, j, root, res);
        return res;
    }

    static void dfs(char[][] b, int r, int c, TrieNode node, List<String> res) {
        if (node.word != null) { res.add(node.word); node.word = null; }
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length) return;
        char ch = b[r][c];
        if (ch == '#' || node.child[ch - 'a'] == null) return;
        TrieNode next = node.child[ch - 'a'];
        b[r][c] = '#';
        dfs(b, r + 1, c, next, res);
        dfs(b, r - 1, c, next, res);
        dfs(b, r, c + 1, next, res);
        dfs(b, r, c - 1, next, res);
        b[r][c] = ch;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        List<String> r = findWords(board, new String[]{"oath","pea","eat","rain"});
        check(r.contains("eat") && r.contains("oath"), "case1");
        List<String> r2 = findWords(new char[][]{{'a'}}, new String[]{"a"});
        check(r2.equals(Collections.singletonList("a")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
