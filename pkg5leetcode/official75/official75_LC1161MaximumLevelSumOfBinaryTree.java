package pkg5leetcode.official75;

/*
 * Maximum Level Sum of a Binary Tree | LC 1161
 * APPROACH: BFS sum each level; track max level.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC1161MaximumLevelSumOfBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int maxLevelSum(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int level = 1, bestLevel = 1, bestSum = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int size = q.size(), sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            if (sum > bestSum) { bestSum = sum; bestLevel = level; }
            level++;
        }
        return bestLevel;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7); root.right = new TreeNode(0);
        root.left.left = new TreeNode(7); root.left.right = new TreeNode(-8);
        check(maxLevelSum(root) == 2, "case1");
        TreeNode r2 = new TreeNode(989);
        r2.right = new TreeNode(10250); r2.right.right = new TreeNode(98693);
        r2.right.right.left = new TreeNode(-89388); r2.right.right.right = new TreeNode(69081);
        r2.right.right.right.left = new TreeNode(12131);
        check(maxLevelSum(r2) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
