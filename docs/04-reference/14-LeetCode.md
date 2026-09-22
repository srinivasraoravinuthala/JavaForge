# LeetCode — Complete Study Plan Coverage

**249 self-tested Java solutions** in [`pkg5leetcode`](../../pkg5leetcode). Each file compiles and runs with built-in assertions.

```powershell
# One file
java pkg5leetcode/blind75/blind75_LC1TwoSum.java

# All LeetCode (smoke test — takes several minutes)
./run-leetcode-all.ps1
```

## Study plan tracks

| Track | Folder | Problems | Source |
|-------|--------|----------|--------|
| Quick start | `pkg5leetcode/` (root) | 12 | Curated pattern intro |
| **Blind 75** | `blind75/` | 75 | [Blind / NeetCode 75](https://neetcode.io/practice?tab=blind75) |
| **LeetCode 75** | `official75/` | 75 | [leetcode.com/studyplan/leetcode-75](https://leetcode.com/studyplan/leetcode-75/) |
| **Interview 150** | `interview150/` | 62 | Extras from [Top Interview 150](https://leetcode.com/studyplan/top-interview-150/) not in Blind 75 |
| **Top 100 Liked** | `top100/` | 25 | Extras from [Top 100 Liked](https://leetcode.com/studyplan/top-100-liked/) |

> **Note:** Official **LeetCode 75** and **Blind 75** are *different* lists. Blind 75 focuses on classic interview patterns (Two Sum, 3Sum, Course Schedule). Official LC75 focuses on trending essentials (Merge Strings Alternately, Asteroid Collision, Dota2 Senate). Do both.

## Blind 75 index (`blind75/`)

| Category | LC # | File prefix |
|----------|------|-------------|
| **Array** | 1, 121, 217, 238, 53, 152, 153, 33, 15, 11 | `blind75_LC*` |
| **Binary** | 371, 191, 338, 268, 190 | |
| **DP** | 70, 322, 300, 1143, 139, 39, 198, 213, 91, 62, 55 | |
| **Graph** | 133, 207, 417, 200, 128, 269*, 261*, 323* | *premium |
| **Interval** | 57, 56, 435, 252*, 253* | |
| **Linked List** | 206, 141, 21, 23, 19, 143 | |
| **Matrix** | 73, 54, 48, 79 | |
| **String** | 3, 424, 76, 242, 49, 20, 125, 5, 647, 271* | |
| **Tree** | 104, 100, 226, 124, 102, 297, 572, 105, 98, 230, 235, 208, 211, 212 | |
| **Heap** | 347, 295 | |

## Official LeetCode 75 topics (`official75/`)

| Topic | Example problems |
|-------|------------------|
| Array / String | 1768, 1071, 1431, 605, 151, 443, 238, 334 |
| Two Pointers | 283, 392, 11, 1679 |
| Sliding Window | 643, 1456, 1004, 1493 |
| Prefix Sum | 1732, 724 |
| Hash Map / Set | 2215, 1207, 1657, 2352 |
| Stack | 2390, 735, 394 |
| Queue | 933, 649 |
| Linked List | 2095, 206, 2130, 328 |
| Tree DFS | 104, 872, 1448, 1372, 437, 236 |
| Tree BFS | 199, 1161 |
| BST | 700, 450 |
| Graph DFS | 841, 797, 1466, 399 |
| Graph BFS | 1926, 994 |
| Heap / PQ | 215, 502, 373, 2336 |
| Binary Search | 374, 230, 162, 74 |
| Backtracking | 17, 216 |
| DP 1D | 746, 790, 714, 72 |
| DP Multidim | 123, 188, 221, 63 |
| Bit Manipulation | 338, 1318, 2419 |
| Trie | 208, 1268 |
| Intervals | 435, 452 |
| Monotonic Stack | 739, 901 |

## Interview 150 extras (`interview150/` — 62 problems)

Problems from Top Interview 150 **not** in Blind 75, including:
2, 4, 7, 9, 14, 26, 27, 35, 36, 42, 45, 58, 66, 69, 74, 83, 88, 94, 101, 118, 136, 146, 150, 155, 167, 173, 189, 202, 205, 219, 234, 287, 290, 344, 383, 399, 409, 438, 509, 560, 704, 733, 746, 739, 763, 767, 787, 844, 852, 875, 876, 977, 989, 994, 1004, 116, 120, 134, 236, 283, …

## Top 100 liked extras (`top100/` — 25 problems)

Famous liked problems not in Blind 75 or Interview 150 extras, including:
17, 22, 31, 34, 46, 51, 64, 72, 75, 78, 84, 85, 86, 96, 114, 148, 215, 222, 239, 240, 448, 461, 494, 503, 516, …

## Root starter set (12)

| File | LC | Pattern |
|------|-----|---------|
| `leetcode1TwoSum` | 1 | Hashing |
| `leetcode2ValidParentheses` | 20 | Stack |
| `leetcode3ValidAnagram` | 242 | Counting |
| `leetcode4BestTimeToBuySellStock` | 121 | Greedy |
| `leetcode5MaxSubArray` | 53 | Kadane |
| `leetcode6ProductExceptSelf` | 238 | Prefix/suffix |
| `leetcode7LongestSubstringNoRepeat` | 3 | Sliding window |
| `leetcode8MergeIntervals` | 56 | Intervals |
| `leetcode9ReverseLinkedList` | 206 | Linked list |
| `leetcode10ClimbingStairs` | 70 | DP |
| `leetcode11NumberOfIslands` | 200 | DFS |
| `leetcode12TopKFrequent` | 347 | Heap |

## Patterns you must master

1. Hashing · 2. Two pointers · 3. Sliding window · 4. Binary search · 5. Stack
6. Linked list · 7. Trees (DFS/BFS) · 8. Graphs · 9. Heap / Top-K · 10. Backtracking
11. Dynamic programming · 12. Greedy / intervals · 13. Bit manipulation · 14. Trie

## How to practice

1. Read problem on LeetCode (link from LC number).
2. Solve on paper / whiteboard first.
3. Compare with solution here; re-implement from memory.
4. Run the file — `all tests passed` means your re-implementation matches.
5. State time/space complexity out loud.

See [Interview Guide](../06-career/02-InterviewGuide.md) for the full interview framework.
