package pkg5leetcode.official75;

/*
 * Count Good Nodes in Binary Tree | LC 1448
 * APPROACH: DFS count nodes >= max on path from root.
 * COMPLEXITY: Time O(n), Space O(h)
 */
public class official75_LC1448CountGoodNodesInBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    static int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int count = node.val >= maxSoFar ? 1 : 0;
        maxSoFar = Math.max(maxSoFar, node.val);
        return count + dfs(node.left, maxSoFar) + dfs(node.right, maxSoFar);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.left.left = new TreeNode(3); root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        check(goodNodes(root) == 4, "case1");
        TreeNode r2 = new TreeNode(3); r2.left = new TreeNode(3);
        r2.right = new TreeNode(4); r2.right.left = new TreeNode(2);
        check(goodNodes(r2) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
