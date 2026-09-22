package pkg5leetcode.official75;

/*
 * Kth Smallest Element in a BST | LC 230
 * APPROACH: Inorder traversal count nodes.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class official75_LC230KthSmallestElementInABST {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int kthSmallest(TreeNode root, int k) {
        java.util.Deque<TreeNode> st = new java.util.ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !st.isEmpty()) {
            while (cur != null) { st.push(cur); cur = cur.left; }
            cur = st.pop();
            if (--k == 0) return cur.val;
            cur = cur.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
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
