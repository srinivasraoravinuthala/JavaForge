package pkg5leetcode.official75;

/*
 * Delete Node in a BST | LC 450
 * APPROACH: BST delete with successor for two-child case.
 * COMPLEXITY: Time O(h), Space O(h)
 */
public class official75_LC450DeleteNodeInABST {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = deleteNode(root.left, key);
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode succ = root.right;
            while (succ.left != null) succ = succ.left;
            root.val = succ.val;
            root.right = deleteNode(root.right, succ.val);
        }
        return root;
    }

    static boolean contains(TreeNode root, int key) {
        while (root != null) {
            if (root.val == key) return true;
            root = key < root.val ? root.left : root.right;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        deleteNode(root, 3);
        check(!contains(root, 3) && contains(root, 2), "case1");
        TreeNode r2 = new TreeNode(5); r2.left = new TreeNode(3); r2.right = new TreeNode(6);
        r2.left.left = new TreeNode(2); r2.left.right = new TreeNode(4);
        deleteNode(r2, 5);
        check(r2.val == 6 && contains(r2, 4), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
