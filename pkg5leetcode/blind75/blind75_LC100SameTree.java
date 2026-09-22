package pkg5leetcode.blind75;

/*
 * Same Tree | LC 100
 * APPROACH: Recursive compare values and subtrees.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class blind75_LC100SameTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1); a.left = new TreeNode(2); a.right = new TreeNode(3);
        TreeNode b = new TreeNode(1); b.left = new TreeNode(2); b.right = new TreeNode(3);
        check(isSameTree(a, b), "case1");
        TreeNode c = new TreeNode(1); c.left = new TreeNode(2);
        TreeNode d = new TreeNode(1); d.right = new TreeNode(2);
        check(!isSameTree(c, d), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
