# 19 — Lambdas & Functional Programming

**Previous:** [18 Generics](18-Generics.md) · **Next:** [20 Streams & Optional](20-StreamsAndOptional.md)

▶️ `java pkg1core/core21FunctionalProgramming.java`

---

## Lambda expressions (Java 8+)

```java
Calculator add = (a, b) -> a + b;
Runnable task = () -> System.out.println("run");
Consumer<String> print = s -> System.out.println(s);
```

A lambda implements a **functional interface** (exactly one abstract method).

---

## Core functional interfaces

| Interface | Method | Use |
|-----------|--------|-----|
| `Supplier<T>` | `get()` | Supply a value |
| `Consumer<T>` | `accept(T)` | Act on a value |
| `Function<T,R>` | `apply(T)` | Transform T → R |
| `Predicate<T>` | `test(T)` | Boolean test |
| `BiFunction<T,U,R>` | `apply(T,U)` | Two-arg function |

---

## Method references

```java
Function<String, Integer> len = String::length;
Supplier<List<String>> factory = ArrayList::new;
list.forEach(System.out::println);
```

Shorthand when lambda just calls one method.

---

## Custom functional interface

```java
@FunctionalInterface
interface Calculator {
    int op(int a, int b);
}
```

`@FunctionalInterface` is optional but catches mistakes.

**Next →** [20 Streams & Optional](20-StreamsAndOptional.md)
**Related →** [06 Streams](../03-interview/06-Streams.md)

