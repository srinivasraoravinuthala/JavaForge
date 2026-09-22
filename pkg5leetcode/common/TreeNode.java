/**
 * Shared TreeNode for binary-tree problems.
 *
 * Learn once, reuse across Blind75 / Interview 150 / Top 100.
 *
 * Compile with a problem file:
 *   javac pkg5leetcode/common/TreeNode.java pkg5leetcode/blind75/YourProblem.java
 *   java -cp pkg5leetcode/common;pkg5leetcode/blind75 YourProblem
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
