package pkg5leetcode.blind75;

/*
 * Serialize and Deserialize Binary Tree | LC 297
 * APPROACH: Preorder with 'N' null markers; rebuild via queue.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC297SerializeAndDeserializeBinaryTree {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class Codec {
        String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            build(root, sb);
            return sb.toString();
        }

        void build(TreeNode node, StringBuilder sb) {
            if (node == null) { sb.append("N,"); return; }
            sb.append(node.val).append(',');
            build(node.left, sb);
            build(node.right, sb);
        }

        TreeNode deserialize(String data) {
            Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
            return parse(q);
        }

        TreeNode parse(Queue<String> q) {
            String tok = q.poll();
            if ("N".equals(tok)) return null;
            TreeNode node = new TreeNode(Integer.parseInt(tok));
            node.left = parse(q);
            node.right = parse(q);
            return node;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        TreeNode back = codec.deserialize(codec.serialize(root));
        check(back.val == 1 && back.left.val == 2 && back.right.right.val == 5, "case1");
        check(codec.deserialize(codec.serialize(null)) == null, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
