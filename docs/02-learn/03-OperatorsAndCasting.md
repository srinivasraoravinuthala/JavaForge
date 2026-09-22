# 03 — Operators & Casting

**Previous:** [02 Variables & Types](02-VariablesAndTypes.md) · **Next:** [04 Control Flow](04-ControlFlow.md)

▶️ `java pkg1core/core4Operators.java`

---

## Arithmetic operators

| Operator | Meaning | Example |
|----------|---------|---------|
| `+` `-` `*` | Add, subtract, multiply | `5 + 3` → `8` |
| `/` | Division | `7 / 2` → `3` (integer division!) |
| `%` | Remainder (modulo) | `7 % 2` → `1` |

```java
int a = 7, b = 2;
System.out.println(a / b);   // 3  (not 3.5)
System.out.println(a % b);   // 1
System.out.println(7 / 2.0); // 3.5 (double division)
```

⚠️ **Integer division truncates** — `7 / 2` is `3`, not `3.5`.

---

## Assignment & compound operators

```java
int x = 10;
x += 5;    // x = x + 5  → 15
x *= 2;    // x = x * 2  → 30
x++;       // post-increment: use then add 1
++x;       // pre-increment: add 1 then use
```

---

## Comparison operators

Return `boolean`: `==` `!=` `<` `>` `<=` `>=`

```java
int i = 0;
System.out.println(i++ + ++i);  // 0 + 2 = 2 (tricky — trace on paper!)
```

💡 Use `==` for primitives; use `.equals()` for objects (especially `String`).

---

## Logical operators

| Operator | Meaning |
|----------|---------|
| `&&` | AND (short-circuit) |
| `\|\|` | OR (short-circuit) |
| `!` | NOT |

**Short-circuit:** `false && anything` never evaluates `anything`.

```java
if (list != null && !list.isEmpty()) { ... }  // safe — null check first
```

---

## Bitwise operators

`| & ^ ~ << >> >>>` — operate on individual bits. Used in flags, permissions, low-level math.

```java
System.out.println(5 & 3);   // 1  (0101 & 0011 = 0001)
System.out.println(5 | 3);   // 7
System.out.println(~1);      // -2 (two's complement)
```

---

## Ternary operator

```java
String grade = (score >= 60) ? "Pass" : "Fail";
```

Shorthand for simple if/else assignment.

---

## Operator precedence (highest first)

1. `()` grouping
2. `++` `--` `!` `~`
3. `*` `/` `%`
4. `+` `-`
5. `<` `>` `<=` `>=`
6. `==` `!=`
7. `&&`
8. `||`
9. `?:` ternary
10. `=` assignment

When in doubt, **use parentheses**.

---

## Casting recap

| Cast | Name | Safe? |
|------|------|-------|
| `int` → `long` | Widening | Automatic |
| `long` → `int` | Narrowing | Manual `(int)x`; may truncate |
| `double` → `int` | Narrowing | Truncates decimal part |

---

## Practice

1. Run `core4Operators`.
2. Predict output of `i++ + ++i` before running.
3. Write a ternary that picks the larger of two ints.

**Next →** [04 Control Flow](04-ControlFlow.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

