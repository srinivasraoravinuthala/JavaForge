package pkg5leetcode.official75;

/*
 * Leaf-Similar Trees | LC 872
 * APPROACH: DFS collect leaf sequences; compare lists.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC872LeafSimilarTrees {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static void leaves(TreeNode node, List<Integer> out) {
        if (node == null) return;
        if (node.left == null && node.right == null) { out.add(node.val); return; }
        leaves(node.left, out);
        leaves(node.right, out);
    }

    static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> a = new ArrayList<>(), b = new ArrayList<>();
        leaves(root1, a);
        leaves(root2, b);
        return a.equals(b);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3); a.left = new TreeNode(5); a.right = new TreeNode(1);
        a.left.left = new TreeNode(6); a.left.right = new TreeNode(2);
        TreeNode b = new TreeNode(3); b.left = new TreeNode(5); b.right = new TreeNode(1);
        b.left.left = new TreeNode(6); b.left.right = new TreeNode(2);
        check(leafSimilar(a, b), "case1");
        TreeNode c = new TreeNode(1); c.left = new TreeNode(2);
        TreeNode d = new TreeNode(1); d.right = new TreeNode(3);
        check(!leafSimilar(c, d), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
