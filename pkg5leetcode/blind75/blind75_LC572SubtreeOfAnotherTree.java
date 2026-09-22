package pkg5leetcode.blind75;

/*
 * Subtree of Another Tree | LC 572
 * APPROACH: DFS root; at each node check same-tree match.
 * COMPLEXITY: Time O(mn), Space O(h)
 */
public class blind75_LC572SubtreeOfAnotherTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (same(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    static boolean same(TreeNode a, TreeNode b) {
        if (a == null || b == null) return a == b;
        return a.val == b.val && same(a.left, b.left) && same(a.right, b.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        TreeNode sub = new TreeNode(4);
        sub.left = new TreeNode(1);
        sub.right = new TreeNode(2);
        check(isSubtree(root, sub), "case1");
        TreeNode sub2 = new TreeNode(4);
        sub2.left = new TreeNode(1);
        check(!isSubtree(root, sub2), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
