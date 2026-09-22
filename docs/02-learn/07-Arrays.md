# 07 — Arrays

**Previous:** [06 Methods](06-Methods.md) · **Next:** [08 Strings](08-Strings.md)

▶️ `java pkg1core/core8ArraysDemo.java`

---

## Declaring arrays

```java
int[] a = {5, 3, 8, 1, 9};       // inline init
int[] b = new int[3];              // default: all zeros
b[0] = 10;
```

- Fixed size once created
- Zero-indexed: first element is `[0]`
- `arr.length` is a **field** (not a method)

---

## 2D arrays (matrix)

```java
int[][] grid = {
    {1, 2, 3},
    {4, 5, 6}
};
System.out.println(grid[1][2]);   // 6
```

An array of arrays — rows can differ in length (jagged).

---

## java.util.Arrays utilities

```java
Arrays.sort(sorted);
Arrays.binarySearch(sorted, 8);    // must be sorted first
Arrays.fill(arr, 7);
Arrays.copyOf(arr, 3);
Arrays.toString(arr);
```

---

## Common patterns

```java
// Reverse in place
for (int i = 0, j = a.length - 1; i < j; i++, j--) {
    int t = a[i]; a[i] = a[j]; a[j] = t;
}

// Sum
int sum = 0;
for (int v : a) sum += v;
```

---

## Arrays vs ArrayList

| | Array | ArrayList |
|---|-------|-----------|
| Size | Fixed | Grows dynamically |
| Primitives | Yes (`int[]`) | No (must box) |
| Performance | Faster, less memory | More flexible |

💡 Use arrays for fixed-size, performance-critical code; `ArrayList` for most application logic.

▶️ Dynamic array implementation: `java pkg3datastructures/datastructures0DynamicArray.java`

**Next →** [08 Strings](08-Strings.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

