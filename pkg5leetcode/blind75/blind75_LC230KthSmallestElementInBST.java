package pkg5leetcode.blind75;

/*
 * Kth Smallest Element in a BST | LC 230
 * APPROACH: Inorder traversal returns kth visited node.
 * COMPLEXITY: Time O(h+k), Space O(h)
 */
public class blind75_LC230KthSmallestElementInBST {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int k, ans;

    static int kthSmallest(TreeNode root, int kVal) {
        k = kVal;
        inorder(root);
        return ans;
    }

    static void inorder(TreeNode node) {
        if (node == null || k == 0) return;
        inorder(node.left);
        if (--k == 0) ans = node.val;
        inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        check(kthSmallest(root, 1) == 1, "case1");
        check(kthSmallest(root, 3) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
