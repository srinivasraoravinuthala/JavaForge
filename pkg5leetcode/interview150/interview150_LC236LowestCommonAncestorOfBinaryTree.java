package pkg5leetcode.interview150;

/*
 * Lowest Common Ancestor of Binary Tree | LC 236
 * APPROACH: Recurse; if node is p or q return it; LCA if both subtrees found.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class interview150_LC236LowestCommonAncestorOfBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode l, TreeNode r) { val = val; left = l; right = r; }
    }

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        TreeNode root = new TreeNode(3, p, new TreeNode(4));
        p.left = q;
        p.right = new TreeNode(8);
        check(lowestCommonAncestor(root, p, q).val == 5, "case1");
        q = new TreeNode(4);
        check(lowestCommonAncestor(root, p, q).val == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
