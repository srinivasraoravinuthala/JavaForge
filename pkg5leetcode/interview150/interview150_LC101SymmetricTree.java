package pkg5leetcode.interview150;

/*
 * Symmetric Tree | LC 101
 * APPROACH: Compare left and right subtrees as mirror images.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class interview150_LC101SymmetricTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static boolean isSymmetric(TreeNode root) {
        return root == null || mirror(root.left, root.right);
    }

    static boolean mirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && mirror(a.left, b.right) && mirror(a.right, b.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        check(isSymmetric(root), "case1");
        TreeNode bad = new TreeNode(1);
        bad.left = new TreeNode(2);
        bad.right = new TreeNode(2);
        bad.left.right = new TreeNode(3);
        bad.right.right = new TreeNode(3);
        check(!isSymmetric(bad), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
