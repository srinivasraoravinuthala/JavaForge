# Effective Java & Best Practices — Interview Questions (82+)

Idioms inspired by Joshua Bloch's *Effective Java* and modern Java style.

---

## Detailed Questions

### 1. Prefer static factory methods over constructors?
- **Short:** Named methods (`valueOf`, `of`) clarify intent; can cache, return subtypes.
- **Detailed:** `Integer.valueOf` caches; `Collections.emptyList()` returns singleton. Constructors must be `new` each time unless private for factories. Can't add names to constructors easily.
- **Example:** `List.of()` vs `new ArrayList<>(Arrays.asList(...))`.

### 2. Builder pattern for many optional parameters?
- **Short:** Telescoping constructors are unreadable; Builder scales with fluency and validation.
- **Detailed:** Validate in `build()`. Immutable object from builder. Lombok `@Builder` generates boilerplate. Consider records + factory for simple cases.
- **Example:** `new Pizza.Builder().cheese(true).build();`

### 3. Enforce singleton with enum?
- **Short:** `enum Instance` is best singleton — one instance, serialization-safe, reflection-safe.
- **Detailed:** JVM guarantees single instance. `readResolve` not needed. Avoid double-checked locking unless you understand memory model.
- **Example:** `enum Config { INSTANCE; void load() {} }`

### 4. Eliminate obsolete references?
- **Short:** Null out or remove entries so objects aren't accidentally retained (memory leaks).
- **Detailed:** Static collections, listeners, `ThreadLocal`, caches hold objects alive. Weak references for caches; remove listeners on destroy.
- **Example:** `element = null` after pop from custom stack (usually GC handles; matters if reused array).

### 5. Obey equals/hashCode contract?
- **Short:** Equal objects → equal hash codes; consistent with `equals`.
- **Detailed:** Use same fields in both. `Objects.equals/hash` helpers. For entities, business key vs surrogate ID decision affects ORM.
- **Example:** `Objects.hash(name, dob)` with matching `equals`.

### 6. Always override toString?
- **Short:** Helps logging and debugging; keep concise, no secrets.
- **Detailed:** Records auto-generate. Include identifying fields. Don't throw from `toString`.
- **Example:** `User[id=42, email=masked]`.

### 7. Minimize mutability?
- **Short:** Immutable classes are simpler, thread-safe, freely shareable.
- **Detailed:** `final` fields, no setters, defensive copies on getters for mutable components. `List.copyOf` in constructor.
- **Example:** `record` with `List.copyOf(items)` in compact ctor.

### 8. Prefer composition to inheritance?
- **Short:** Inheritance couples to superclass implementation; composition is flexible.
- **Detailed:** Inheritance is "is-a" when true subtype behavior holds (LSP). Otherwise wrap (`ForwardingSet` delegating to inner `Set`).
- **Example:** `class InstrumentedSet implements Set` wrapping delegate.

### 9. Design for inheritance or prohibit it?
- **Short:** `@Final` or document hooks; subclasses can break invariants.
- **Detailed:** If allowing subclassing, make self-use of overridable methods safe or make methods `final`. Prefer `sealed` for controlled hierarchies.
- **Example:** `AbstractCollection` carefully documents override points.

### 10. Prefer interfaces to abstract classes?
- **Short:** Multiple inheritance of type; easier mocking; default methods bridge gap.
- **Detailed:** Abstract class when shared state/implementation needed. Interface for capability contracts (`Comparable`, `Runnable`).
- **Example:** `List` interface + multiple implementations.

### 11. Check parameters validity?
- **Short:** Fail fast with `Objects.requireNonNull`, `IllegalArgumentException`.
- **Detailed:** Validate in constructors and public methods. Document in javadoc `@throws`. Don't rely on assert for public API.
- **Example:** `Objects.requireNonNull(name, "name")`.

### 12. Return empty collections, not null?
- **Short:** `Collections.emptyList()` or `List.of()` — callers skip null checks.
- **Detailed:** Null return forces defensive code everywhere. Optional for truly absent single values. Empty is not absent semantically for collections.
- **Example:** `return matches.isEmpty() ? List.of() : matches;`

---

## Rapid-Fire (Q → A)

1. Private constructor + static factory? → Hide construction.
2. Utility class pattern? → Private ctor, static methods only.
3. Constant interface anti-pattern? → Don't implement constants interface.
4. Use interface only for types? → Yes; not constant holder.
5. Favor immutability in public API? → Yes.
6. Defensive copy on getter? → For mutable internal state.
7. Defensive copy on setter/ctor? → Before storing mutable param.
8. Date class legacy? → Use java.time.
9. Instant vs ZonedDateTime? → Instant = UTC point; Zoned = timezone rules.
10. Period vs Duration? → Period calendar-based; Duration time-based.
11. Don't use float for money? → BigDecimal.
12. BigDecimal from String? → Yes; from double imprecise.
13. RoundingMode HALF_UP? → Common commercial rounding.
14. try-with-resources item 9? → Always for Closeable.
15. Close failure handling? → Suppressed exception.
16. Prefer standard exceptions? → IAE, ISE, NPE (requireNonNull).
17. Document unchecked exceptions? → @throws in javadoc anyway.
18. Include failure-capture in detail message? → Key ids/context.
19. Override annotate @Override? → Catches typos.
20. Override hashCode when equals? → Mandatory contract.
21. Compare floats? → Float.compare / epsilon.
22. Compare doubles? → Double.compare.
23. Avoid strings for enums switches? → Use enum constants.
24. Enum singleton vs static field? → Enum preferred.
25. EnumSet for enum collections? → Bit vector fast.
26. EnumMap? → Array-backed by ordinal.
27. WeakHashMap use? → Cache with GC-friendly keys.
28. IdentityHashMap when? → Reference equality semantics.
29. Collections.sort mutates? → Yes, in-place.
30. List.sort vs Collections.sort? → List.sort default method.
31. Arrays.sort objects? → TimSort stable.
32. Arrays.parallelSort when? → Large arrays, comparable.
33. Prefer for-each? → Unless need remove via Iterator.
34. Iterable custom? → Implement iterator.
35. Stream not reusable? → New stream per pipeline.
36. Optional return from method? → Not for fields/params usually.
37. Optional.get without check? → NoSuchElementException.
38. Optional.orElse vs orElseGet? → orElseGet lazy supplier.
39. Optional stream flatMap? → Chaining optional steps.
40. Don't use Optional as field? → Nullable or absent sentinel debate.
41. Lazy initialization holder? → Static holder idiom.
42. Double-checked locking volatile? → Required for correctness.
43. Prefer java.util.concurrent? → Over wait/notify hand-rolled.
44. Concurrent utilities over synchronized? → When higher-level fits.
45. Document thread safety? → State in class javadoc.
46. Synchronize entire method? → Same as sync(this) instance method.
47. Avoid excessive sync? → Lock contention.
48. ReadWriteLock when? → Read-heavy mutable structure.
49. Stale data vs consistency? → Trade-off document.
50. Serialization proxy pattern? → Control deserialized instance.
51. readResolve? → Replace deserialized object.
52. Avoid serialize inner class? → static nested preferred.
53. Custom serialized form? → writeReplace/readResolve.
54. Consider copy constructor? → Alternative to clone.
55. Cloneable broken? → Prefer factories/copy methods.
56. toString for debugging only? → Not parseable contract.
57. compareTo consistent with equals? → Strongly recommended.
58. Comparator natural order? → compareTo.
59. TimSort requirement? → Stable sort needs stability.
60. List.of immutable? → No add/remove/set.
61. copyOf defensive? → Immutable snapshot.
62. Map.entry? → Immutable entry pair.
63. Factory List.of null? → NPE.
64. Prefer integration tests scope? → Test behavior not implementation.
65. Test one concept per test? → Clear failure diagnosis.
66. Given-when-then? → Arrange-act-assert structure.
67. Don't test private methods? → Test public contract.
68. Parameterized tests? → JUnit5 @ParameterizedTest.
69. Property-based testing? → jqwik random inputs.
70. Avoid magic numbers? → Named constants.
