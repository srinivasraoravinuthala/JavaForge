package pkg5leetcode.blind75;

/*
 * Construct Binary Tree from Preorder and Inorder | LC 105
 * APPROACH: Root from pre[0]; split inorder by root index recursively.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC105ConstructBinaryTreeFromPreorderAndInorder {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) idx.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, idx);
    }

    static TreeNode build(int[] pre, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> idx) {
        if (pl > pr) return null;
        int rootVal = pre[pl];
        TreeNode root = new TreeNode(rootVal);
        int mid = idx.get(rootVal);
        int leftSize = mid - il;
        root.left = build(pre, pl + 1, pl + leftSize, in, il, mid - 1, idx);
        root.right = build(pre, pl + leftSize + 1, pr, in, mid + 1, ir, idx);
        return root;
    }

    public static void main(String[] args) {
        TreeNode t = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        check(t.val == 3 && t.left.val == 9 && t.right.left.val == 15, "case1");
        TreeNode t2 = buildTree(new int[]{-1}, new int[]{-1});
        check(t2.val == -1 && t2.left == null, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
