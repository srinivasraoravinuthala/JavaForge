package pkg5leetcode.blind75;

/*
 * Validate Binary Search Tree | LC 98
 * APPROACH: Inorder must be strictly increasing.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class blind75_LC98ValidateBinarySearchTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static Integer prev;

    static boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    static boolean inorder(TreeNode node) {
        if (node == null) return true;
        if (!inorder(node.left)) return false;
        if (prev != null && node.val <= prev) return false;
        prev = node.val;
        return inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        check(isValidBST(root), "case1");
        TreeNode bad = new TreeNode(5);
        bad.left = new TreeNode(1);
        bad.right = new TreeNode(4);
        bad.right.left = new TreeNode(3);
        bad.right.right = new TreeNode(6);
        check(!isValidBST(bad), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
