# 22 — Data Structures

**Previous:** [21 Java Versions](21-JavaVersions.md) · **Next:** [23 Algorithms](23-Algorithms.md)

▶️ `pkg3datastructures/datastructures0DynamicArray.java` → `datastructures12UnionFind.java`

---

## Learning order

| # | Structure | Complexity highlight | File |
|---|-----------|---------------------|------|
| 0 | Dynamic Array | get O(1), append amortized O(1) | `datastructures0DynamicArray` |
| 1 | Singly Linked List | addFirst O(1), get O(n) | `datastructures1SinglyLinkedList` |
| 2 | Doubly Linked List | remove node O(1) with ref | `datastructures2DoublyLinkedList` |
| 3 | Stack | push/pop O(1) | `datastructures3StackImpl` |
| 4 | Queue | enqueue/dequeue O(1) | `datastructures4QueueImpl` |
| 5 | Min Heap | insert/extract O(log n) | `datastructures5MinHeap` |
| 6 | Hash Table | avg O(1) get/put | `datastructures6HashTableImpl` |
| 7–9 | Binary Tree, BST, AVL | search O(log n) balanced | `datastructures7`–`9` |
| 10 | Trie | prefix search | `datastructures10Trie` |
| 11 | Graph (adjacency list) | BFS/DFS O(V+E) | `datastructures11GraphImpl` |
| 12 | Union-Find | near O(1) amortized | `datastructures12UnionFind` |

---

## How to study each

1. **Read** the file header (definition + complexity + when to use).
2. **Run** it: `java pkg3datastructures/datastructures1SinglyLinkedList.java`
3. **Close the file** and re-implement from memory.
4. **Compare** your version — did you handle edge cases?

---

## When to use what

```
Need fast index access?     → Dynamic array / ArrayList
Need fast insert at head?   → Linked list
Need LIFO?                  → Stack
Need FIFO?                  → Queue
Need min/max repeatedly?    → Heap
Need key lookup?            → Hash table / HashMap
Need sorted keys?           → BST / TreeMap
Need prefix search?         → Trie
Need connections/network?     → Graph
Need connected components?    → Union-Find
```

---

## Practice project

Implement a **LRU cache** using `LinkedHashMap` (access-order) or combine HashMap + doubly linked list.

**Next →** [23 Algorithms](23-Algorithms.md)
**Related →** [17 Print Puzzles](../03-interview/17-PrintPuzzles.md)

