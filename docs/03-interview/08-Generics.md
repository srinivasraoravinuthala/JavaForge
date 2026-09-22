# Generics — Interview Questions (60+)

## Detailed Questions

### 1. Why generics?
- **Short:** Compile-time type safety and fewer casts.
- **Detailed:** Generics let you parameterize types, catching type errors at compile time and removing explicit casts. They make APIs self-documenting and reusable.
- **Example:** `List<String>` prevents adding an Integer.

### 2. What is type erasure?
- **Short:** Generic type info is removed at runtime.
- **Detailed:** The compiler erases type parameters to their bounds (or Object), inserting casts. So `List<String>` and `List<Integer>` share one runtime class. Consequences: no `new T[]`, no runtime `instanceof List<String>`, no overloads differing only by type args.
- **Example:** `list.getClass() == List.class` regardless of `<T>`.

### 3. Explain PECS.
- **Short:** Producer Extends, Consumer Super.
- **Detailed:** Use `? extends T` when you only READ Ts from a structure (it produces Ts). Use `? super T` when you only WRITE Ts into it (it consumes Ts). This maximizes flexibility.
- **Example:** `copy(List<? super T> dst, List<? extends T> src)`.

### 4. Bounded type parameters?
- **Short:** Constrain `<T>` to a supertype.
- **Detailed:** `<T extends Number>` lets you call Number methods and accept Number subtypes. Multiple bounds: `<T extends A & B>` (class first).
- **Example:** `<T extends Comparable<T>> T max(List<T> xs)`.

### 5. Wildcards vs type parameters?
- **Short:** Wildcards for flexibility at use-site; type params to relate types.
- **Detailed:** Use a type parameter when multiple positions must be the same type or the return type depends on it. Use a wildcard for one-off flexibility without naming the type.
- **Example:** `void printAll(List<?> list)` vs `<T> T first(List<T> list)`.

### 6. Why can't you create a generic array?
- **Short:** Erasure makes array store checks unsound.
- **Detailed:** `new T[]` is illegal because the runtime can't enforce the component type (arrays are reified, generics are erased). Workarounds: `(T[]) new Object[n]` with `@SuppressWarnings`, or `Array.newInstance`.
- **Example:** Collections use `Object[]` internally.

### 7. What is a generic method?
- **Short:** A method with its own type parameters.
- **Detailed:** Declared before the return type: `<T> T identity(T x)`. The compiler infers `T` from arguments.
- **Example:** `Collections.<String>emptyList()`.

### 8. What is heap pollution?
- **Short:** A parameterized variable refers to an object of a different type.
- **Detailed:** Often via unchecked casts or varargs of generics; can cause `ClassCastException` later. `@SafeVarargs` suppresses the warning when the method is provably safe.
- **Example:** Mixing raw and generic types.

---

## Rapid-Fire (Q → A)

1. Diamond operator? → `<>` infers type args.
2. Raw type? → Generic used without args (legacy).
3. `List` vs `List<Object>` vs `List<?>`? → Raw / any-object / unknown-type.
4. Can you add to `List<?>`? → Only null.
5. Can you add to `List<? extends Number>`? → No (read-only producer).
6. Can you add to `List<? super Integer>`? → Yes, Integers.
7. Upper bound syntax? → `? extends T`.
8. Lower bound syntax? → `? super T`.
9. Unbounded wildcard? → `?`.
10. Multiple bounds order? → Class first, then interfaces.
11. Generic class syntax? → `class Box<T> {}`.
12. Generic interface? → `interface Repo<T> {}`.
13. Generic method syntax? → `<T> T m(T x)`.
14. Bounded method? → `<T extends Number>`.
15. Recursive bound? → `<T extends Comparable<T>>`.
16. Type inference source? → Arguments/target type.
17. Var with generics? → Infers full type.
18. Erasure replaces T with? → Its bound or Object.
19. instanceof with generics? → Only unbounded `List<?>`.
20. Cast to generic warning? → Unchecked.
21. SuppressWarnings("unchecked")? → Silence unchecked.
22. Generic array creation? → Illegal.
23. Varargs + generics? → Heap pollution warning.
24. @SafeVarargs? → Asserts safe varargs.
25. Static generic field? → Not allowed (no class type param in static).
26. Generic exception? → Can't extend Throwable generically.
27. Overload by type arg? → Not allowed (erasure).
28. Bridge methods? → Synthetic methods for erasure/overriding.
29. Reifiable type? → Type fully available at runtime.
30. Non-reifiable type? → Erased generic type.
31. Why no primitives in generics? → Only reference types; use wrappers.
32. Generic + autoboxing cost? → Boxing overhead.
33. Comparable<T> reason? → Self-type comparison.
34. Comparator generic? → `Comparator<? super T>`.
35. Collections.sort signature? → Uses `? super T` comparator.
36. PECS for Collections.copy? → dst super, src extends.
37. Function<? super T,? extends R>? → Flexible function params.
38. Wildcard capture? → Helper method infers the unknown type.
39. Why capture helper? → To modify a `List<?>` safely.
40. Generic constructor? → `<T> Box(T x)`.
41. Diamond with anonymous class? → Allowed (Java 9+).
42. Type token? → `Class<T>` to retain type.
43. Super type token? → `TypeReference` trick for generics.
44. Generic singleton factory? → `Collections.emptyList()`.
45. Covariance in arrays? → Arrays covariant (unsafe).
46. Covariance in generics? → Invariant by default.
47. Make generics covariant? → Use `? extends`.
48. Contravariance? → `? super`.
49. Invariance meaning? → `List<String>` not a `List<Object>`.
50. Why generics invariant? → Type safety on writes.
51. Self-referential generic? → CRTP-style bounds.
52. Builder with generics? → Fluent typed builder.
53. Generic enum? → Not allowed.
54. Generic with bounds + multiple interfaces? → `<T extends A & B>`.
55. Inferred return type? → From target/assignment.
56. Generic default method? → Allowed.
57. Erasure and reflection? → Some info via signatures.
58. getGenericSuperclass? → Reflect parameterized supertype.
59. Wildcard in return type? → Discouraged (hard to consume).
60. Golden rule? → Use bounded wildcards on input params (PECS), exact types on outputs.
