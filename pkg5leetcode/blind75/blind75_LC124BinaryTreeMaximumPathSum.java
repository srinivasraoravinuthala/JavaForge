package pkg5leetcode.blind75;

/*
 * Binary Tree Maximum Path Sum | LC 124
 * APPROACH: Post-order gain through node vs global max path.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class blind75_LC124BinaryTreeMaximumPathSum {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int best;

    static int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    static int gain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        best = Math.max(best, node.val + left + right);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        check(maxPathSum(root) == 42, "case1");
        TreeNode r2 = new TreeNode(1); r2.left = new TreeNode(2); r2.right = new TreeNode(3);
        check(maxPathSum(r2) == 6, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
