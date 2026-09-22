# 02 — Variables & Types

**Previous:** [01 Getting Started](01-GettingStarted.md) · **Next:** [03 Operators & Casting](03-OperatorsAndCasting.md)

▶️ `java pkg1core/core2Variables.java` · `java pkg1core/core3DataTypes.java`

---

## Variables store data

A **variable** is a named container for a value.

```java
int age = 30;
double price = 19.99;
boolean active = true;
String name = "Ravi";
```

| Part | Meaning |
|------|---------|
| `int` | **Type** — what kind of data |
| `age` | **Name** — how you refer to it |
| `30` | **Value** — what's stored |

---

## The 8 primitive types

Primitives store raw values directly (fast, no object overhead).

| Type | Size | Example | Use for |
|------|------|---------|---------|
| `byte` | 8-bit | `127` | Rare; binary protocols |
| `short` | 16-bit | `32000` | Rare |
| `int` | 32-bit | `42` | Whole numbers (default choice) |
| `long` | 64-bit | `9_000_000_000L` | Big integers; suffix `L` |
| `float` | 32-bit | `3.14f` | Rare; suffix `f` |
| `double` | 64-bit | `3.14159` | Decimals (default choice) |
| `char` | 16-bit | `'A'` | Single character |
| `boolean` | 1-bit | `true` | true / false |

💡 Use `int` for integers and `double` for decimals unless you have a specific reason not to.

---

## Reference types

Everything that is **not** a primitive is an **object** (stored on the heap; variable holds a **reference**).

```java
String greeting = "Hello";     // String object
int[] numbers = {1, 2, 3};     // array object
Scanner sc = new Scanner(System.in);
```

---

## Wrappers & autoboxing

Each primitive has a **wrapper class** for use in generics/collections:

| Primitive | Wrapper |
|-----------|---------|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |

```java
Integer boxed = 42;    // autoboxing: int → Integer
int unboxed = boxed;   // unboxing: Integer → int
```

⚠️ **Gotcha:** `Integer` cache for -128..127 — `Integer.valueOf(127) == Integer.valueOf(127)` is `true`, but `128` is `false`.

---

## Variable kinds

| Kind | Where | Example |
|------|-------|---------|
| **Local** | Inside a method | `int x = 5;` |
| **Instance** | Per object | `String name;` in a class |
| **Static** | Per class (shared) | `static int count;` |
| **Final** | Cannot reassign | `final int MAX = 100;` |

---

## `var` — type inference (Java 10+)

```java
var message = "Hello";        // inferred as String
var list = new ArrayList<String>();
```

Rules: **local variables only**, must have an initializer, still statically typed.

---

## Casting & overflow

```java
long big = 9_000_000_000L;
int small = (int) big;           // narrowing cast — may lose data

int max = Integer.MAX_VALUE;
System.out.println(max + 1);     // overflows to Integer.MIN_VALUE
```

---

## Practice

1. Run `core2Variables` and `core3DataTypes`.
2. Declare one variable of each primitive type and print them.
3. Try `var` for a `List<String>`.

**Interview drill →** [01 Core Java](../03-interview/01-CoreJava.md#q1) (questions 1–7)

**Next →** [03 Operators & Casting](03-OperatorsAndCasting.md)
