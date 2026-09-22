package pkg5leetcode.top100;

/*
 * Flatten Binary Tree to Linked List | LC 114
 * APPROACH: Morris traversal flatten right then left into preorder tail.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class top100_LC114FlattenBinaryTreeToLinkedList {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pre = cur.left;
                while (pre.right != null) pre = pre.right;
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    static int[] toRightChain(TreeNode root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (root != null) {
            list.add(root.val);
            if (root.left != null) throw new AssertionError("left not null");
            root = root.right;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flatten(root);
        check(java.util.Arrays.equals(toRightChain(root), new int[]{1, 2, 3, 4, 5, 6}), "case1");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
