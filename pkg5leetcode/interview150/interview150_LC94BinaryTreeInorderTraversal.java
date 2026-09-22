package pkg5leetcode.interview150;

/*
 * Binary Tree Inorder Traversal | LC 94
 * APPROACH: Recursive left-root-right traversal.
 * COMPLEXITY: Time O(n), Space O(h)
 */
import java.util.*;

public class interview150_LC94BinaryTreeInorderTraversal {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    static void inorder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        check(inorderTraversal(root).equals(Arrays.asList(1, 3, 2)), "case1");
        check(inorderTraversal(null).isEmpty(), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
