# 05 — Loops

**Previous:** [04 Control Flow](04-ControlFlow.md) · **Next:** [06 Methods](06-Methods.md)

▶️ `java pkg1core/core6Loops.java`

---

## for loop — when you need an index

```java
for (int i = 0; i < 5; i++) {
    System.out.print(i + " ");
}
// 0 1 2 3 4
```

Three parts: **init** → **condition** → **update**.

---

## Enhanced for (for-each)

```java
int[] nums = {10, 20, 30};
for (int n : nums) {
    System.out.println(n);
}
```

💡 Use for-each when you need **each element**, not the index.

---

## while loop

```java
int count = 3;
while (count > 0) {
    System.out.println(count);
    count--;
}
```

Checks condition **before** each iteration. May run **zero** times.

---

## do-while loop

```java
int x = 0;
do {
    System.out.println("Runs at least once");
} while (x > 0);
```

Body runs **at least once**, then checks condition.

---

## break and continue

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) continue;   // skip evens
    if (i > 7) break;           // stop loop
    System.out.print(i + " ");
}
// 1 3 5 7
```

| Keyword | Effect |
|---------|--------|
| `break` | Exit the loop entirely |
| `continue` | Skip to next iteration |

---

## Labeled break (nested loops)

```java
outer:
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= 5; j++) {
        if (i + j == 7) {
            break outer;   // exits both loops
        }
    }
}
```

Use sparingly — often a method extraction is clearer.

---

## Which loop to choose?

| Loop | Best for |
|------|----------|
| `for` | Known iterations, need index |
| `for-each` | Iterate every element |
| `while` | Unknown iterations, condition-driven |
| `do-while` | Must run at least once (menus, input validation) |

---

## Practice

1. Run `core6Loops`.
2. Print multiplication table 1–10 with nested for loops.
3. Sum array elements with for-each.

**Next →** [06 Methods](06-Methods.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

