# 24 → LeetCode Practice

**Previous:** [23 Algorithms](23-Algorithms.md) → **Next:** [25 JVM & Memory](25-JVMAndMemory.md)

▶️ `pkg5leetcode/` · **249 solutions**

---

## Study order

```
1. Starter 12     pkg5leetcode/leetcode1TwoSum.java · leetcode12
2. Blind 75       pkg5leetcode/blind75/
3. Official 75    pkg5leetcode/official75/
4. Interview 150  pkg5leetcode/interview150/
5. Top 100 extras pkg5leetcode/top100/
```

```bash
java pkg5leetcode/blind75/blind75_LC1TwoSum.java
./run-leetcode-all.ps1    # verify all 249
```

---

## How to practice each problem

1. Read on LeetCode (use LC number in filename).
2. Solve on paper · state approach + complexity.
3. Code without looking.
4. Run the project solution and compare.
5. Re-implement from memory next day.

---

## Shared helpers (learn once → reuse)

Linked-list and tree problems all need the same node types. Study them once in [`pkg5leetcode/common/`](../../pkg5leetcode/common/README.md):

| Type | File |
|------|------|
| `ListNode` | `pkg5leetcode/common/ListNode.java` |
| `TreeNode` | `pkg5leetcode/common/TreeNode.java` |

```bash
javac pkg5leetcode/common/ListNode.java pkg5leetcode/blind75/blind75_LC206ReverseLinkedList.java
java -cp "pkg5leetcode/common;pkg5leetcode/blind75" blind75_LC206ReverseLinkedList
```

Problem files may still nest a copy of the type so `java SomeFile.java` works alone — the **shape** matches `common/`. Prefer compiling against helpers when practicing for interviews.

---

## 14 patterns to master

1. Hashing · 2. Two pointers · 3. Sliding window · 4. Binary search  
5. Stack · 6. Linked list · 7. Trees (DFS/BFS) · 8. Graphs  
9. Heap · 10. Backtracking · 11. DP · 12. Greedy  
13. Intervals · 14. Bit manipulation

**Related →** [LeetCode.md](../04-reference/14-LeetCode.md)

**Related →** [17 Print Puzzles](../03-interview/17-PrintPuzzles.md)


**Next →** [25 JVM & Memory](25-JVMAndMemory.md)
