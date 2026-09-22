# Shared helpers — learn once, reuse

Beginners often redefine `ListNode` / `TreeNode` in every LeetCode file. Use these shared types instead.

| Type | File | Used for |
|------|------|----------|
| `ListNode` | [`ListNode.java`](ListNode.java) | Linked list problems |
| `TreeNode` | [`TreeNode.java`](TreeNode.java) | Binary tree / BST problems |

## How to run a problem with helpers

```bash
# From repo root (Windows classpath uses ;)
javac pkg5leetcode/common/ListNode.java pkg5leetcode/blind75/blind75_LC206ReverseLinkedList.java
java -cp "pkg5leetcode/common;pkg5leetcode/blind75" blind75_LC206ReverseLinkedList
```

Single-file `java SomeProblem.java` still works if the problem keeps a nested copy of the type for demos — but prefer compiling against `common/` so you practice the real interview setup.

## Step-by-step learning

1. Read `ListNode` / `TreeNode` here — fields, constructors, `ListNode.of(...)`.
2. Solve linked-list / tree problems using these types.
3. In interviews, you will sketch the same 5–10 lines on the whiteboard.
