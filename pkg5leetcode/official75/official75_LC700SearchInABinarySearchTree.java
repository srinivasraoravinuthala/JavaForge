package pkg5leetcode.official75;

/*
 * Search in a Binary Search Tree | LC 700
 * APPROACH: BST property walk left or right.
 * COMPLEXITY: Time O(h), Space O(1)
 */
public class official75_LC700SearchInABinarySearchTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        check(searchBST(root, 2).val == 2, "case1");
        check(searchBST(root, 5) == null, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
