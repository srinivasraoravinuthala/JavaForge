package pkg5leetcode.official75;

/*
 * Binary Tree Right Side View | LC 199
 * APPROACH: BFS take last node each level.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC199BinaryTreeRightSideView {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(5); root.right.right = new TreeNode(4);
        check(rightSideView(root).equals(Arrays.asList(1,3,4)), "case1");
        TreeNode r2 = new TreeNode(1); r2.right = new TreeNode(3);
        check(rightSideView(r2).equals(Arrays.asList(1,3)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
