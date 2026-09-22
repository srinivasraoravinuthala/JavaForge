# 18 — Generics

**Previous:** [17 Collections](17-Collections.md) · **Next:** [19 Lambdas & Functional](19-LambdasAndFunctional.md)

▶️ `java pkg1core/core20GenericsDemo.java`

---

## Why generics?

```java
List<String> names = new ArrayList<>();
names.add("Ravi");
// names.add(42);   // COMPILE ERROR — type safe!
String s = names.get(0);   // no cast needed
```

Compile-time safety; removes casts; enables better APIs.

---

## Generic class

```java
class Pair<A, B> {
    final A first;
    final B second;
    Pair(A a, B b) { first = a; second = b; }
}
```

---

## Generic method

```java
static <T extends Comparable<T>> T max(List<T> items) {
    T best = items.get(0);
    for (T x : items) if (x.compareTo(best) > 0) best = x;
    return best;
}
```

`<T extends Comparable<T>>` is a **bounded type parameter**.

---

## Wildcards & PECS

| Wildcard | Read as | Use when |
|----------|---------|----------|
| `? extends T` | Producer | **Read** T values out |
| `? super T` | Consumer | **Write** T values in |

```java
double sum(List<? extends Number> nums) { ... }    // read numbers
void addInts(List<? super Integer> sink) { ... }   // write integers
```

**PECS:** Producer Extends, Consumer Super.

---

## Type erasure

At runtime, `List<String>` becomes raw `List` — generic type info is erased.

⚠️ Can't do `new T[]`, can't `instanceof List<String>`, can't overload on generics only.

**Interview drill →** [08 Generics](../03-interview/08-Generics.md)

**Next →** [19 Lambdas & Functional](19-LambdasAndFunctional.md)
