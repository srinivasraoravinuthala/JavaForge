# 06 — Methods

**Previous:** [05 Loops](05-Loops.md) · **Next:** [07 Arrays](07-Arrays.md)

▶️ `java pkg1core/core7Methods.java`

---

## What is a method?

A **method** is a named block of code that performs one task. It avoids duplication and organizes logic.

```java
static int add(int a, int b) {
    return a + b;
}

public static void main(String[] args) {
    System.out.println(add(3, 4));   // 7
}
```

| Part | Meaning |
|------|---------|
| `static` | Belongs to class, not an object |
| `int` | Return type (`void` = nothing) |
| `add` | Method name |
| `(int a, int b)` | Parameters |
| `return` | Sends value back to caller |

---

## Pass-by-value

Java is **always pass-by-value**.

- Primitives: the **value** is copied.
- Objects: the **reference** is copied (you can mutate the object, but reassigning the parameter doesn't affect the caller).

```java
static void tryReassign(int[] arr) {
    arr[0] = 99;              // mutates caller's array ✓
    arr = new int[]{0};       // reassigns local copy only ✗
}
```

---

## Method overloading

Same name, **different parameter lists** — resolved at compile time.

```java
static int add(int a, int b) { return a + b; }
static double add(double a, double b) { return a + b; }
```

Not overloading: different return type only (compiler can't distinguish).

---

## Varargs

```java
static int sum(int... values) {
    int total = 0;
    for (int v : values) total += v;
    return total;
}
// sum(1, 2, 3, 4) → 10
```

`int...` is treated as `int[]` inside the method.

---

## Recursion

A method that calls itself. Needs a **base case** to stop.

```java
static long factorial(int n) {
    if (n <= 1) return 1;           // base case
    return n * factorial(n - 1);    // recursive step
}
```

⚠️ Deep recursion → `StackOverflowError`. Iteration or tail-recursion awareness for large inputs.

---

## Practice

1. Run `core7Methods`.
2. Write an overloaded `max` for `int` and `double`.
3. Write recursive `fibonacci(n)` and trace `fib(5)` on paper.

**Next →** [07 Arrays](07-Arrays.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

