package pkg5leetcode.official75;

/*
 * Lowest Common Ancestor of a Binary Tree | LC 236
 * APPROACH: Post-order return node if p/q found in subtree.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class official75_LC236LowestCommonAncestorOfBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5), q = new TreeNode(1);
        root.left = p; root.right = q;
        p.left = new TreeNode(6); p.right = new TreeNode(2);
        q.left = new TreeNode(0); q.right = new TreeNode(8);
        p.right.left = new TreeNode(7); p.right.right = new TreeNode(4);
        check(lowestCommonAncestor(root, p, q) == root, "case1");
        TreeNode p2 = p.right, q2 = p.right.right;
        check(lowestCommonAncestor(root, p2, q2) == p2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
