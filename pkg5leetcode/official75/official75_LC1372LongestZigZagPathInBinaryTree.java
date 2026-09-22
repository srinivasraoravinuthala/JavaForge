package pkg5leetcode.official75;

/*
 * Longest ZigZag Path in a Binary Tree | LC 1372
 * APPROACH: DFS track length by direction left/right.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class official75_LC1372LongestZigZagPathInBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int best = 0;

    static int longestZigZag(TreeNode root) {
        best = 0;
        dfs(root);
        return best;
    }

    static int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] L = dfs(node.left), R = dfs(node.right);
        int left = 1 + L[1], right = 1 + R[0];
        best = Math.max(best, Math.max(left, right));
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1); root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1); root.right.right.left = new TreeNode(1);
        root.right.right.right = new TreeNode(1); root.right.right.right.left = new TreeNode(1);
        root.right.right.right.right = new TreeNode(1);
        check(longestZigZag(root) == 3, "case1");
        TreeNode r2 = new TreeNode(1); r2.left = new TreeNode(1); r2.right = new TreeNode(1);
        r2.left.right = new TreeNode(1); r2.left.right.right = new TreeNode(1);
        r2.left.right.right.right = new TreeNode(1); r2.left.right.right.right.right = new TreeNode(1);
        check(longestZigZag(r2) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
