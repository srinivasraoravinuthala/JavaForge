package pkg5leetcode.blind75;

/*
 * Lowest Common Ancestor of a BST | LC 235
 * APPROACH: Walk from root using BST ordering to split p and q.
 * COMPLEXITY: Time O(h), Space O(1)
 */
public class blind75_LC235LowestCommonAncestorOfBST {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) root = root.left;
            else if (p.val > root.val && q.val > root.val) root = root.right;
            else return root;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        TreeNode p = root.left, q = root.right;
        check(lowestCommonAncestor(root, p, q).val == 6, "case1");
        TreeNode p2 = root.left.right, q2 = root.left.right.right;
        check(lowestCommonAncestor(root, p2, q2).val == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
