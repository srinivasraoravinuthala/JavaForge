package pkg5leetcode.official75;

/*
 * Path Sum III | LC 437
 * APPROACH: Prefix sum on tree paths with hash map.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC437PathSumIII {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, 0L, targetSum, prefix);
    }

    static int dfs(TreeNode node, long cur, int target, Map<Long, Integer> prefix) {
        if (node == null) return 0;
        cur += node.val;
        int count = prefix.getOrDefault(cur - target, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        count += dfs(node.left, cur, target, prefix);
        count += dfs(node.right, cur, target, prefix);
        prefix.put(cur, prefix.get(cur) - 1);
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3); root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        check(pathSum(root, 8) == 3, "case1");
        TreeNode r2 = new TreeNode(5);
        check(pathSum(r2, 5) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
