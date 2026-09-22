package pkg5leetcode.top100;

/*
 * Count Complete Tree Nodes | LC 222
 * APPROACH: Compare left/right heights; recurse one side if equal.
 * COMPLEXITY: Time O(log^2 n), Space O(log n)
 */
public class top100_LC222CountCompleteTreeNodes {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = depthLeft(root), right = depthRight(root);
        if (left == right) return (1 << left) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static int depthLeft(TreeNode n) {
        int d = 0;
        while (n != null) { d++; n = n.left; }
        return d;
    }

    static int depthRight(TreeNode n) {
        int d = 0;
        while (n != null) { d++; n = n.right; }
        return d;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        check(countNodes(root) == 6, "case1");
        check(countNodes(null) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
