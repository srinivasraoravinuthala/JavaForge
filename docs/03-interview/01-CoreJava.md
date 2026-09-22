# Core Java — Interview Questions (200+)

Format: **detailed** questions have Short / Detailed / Example; the **rapid-fire** section packs many more.

---

## Detailed Questions

<a id="q1"></a>

### 1. What is the difference between JDK, JRE, and JVM?
- **Short:** JVM runs bytecode; JRE = JVM + libraries; JDK = JRE + dev tools.
- **Detailed:** The **JVM** is the abstract machine that loads/verifies/executes `.class` bytecode and manages memory/GC. The **JRE** packages the JVM with the standard class library so you can *run* programs. The **JDK** adds development tools (`javac`, `jar`, `javadoc`, `jshell`) so you can *build* programs.
- **Example:** `javac App.java` (JDK) produces `App.class`, which `java App` (JRE/JVM) executes.

<a id="q2"></a>

### 2. Why is Java "platform independent"?
- **Short:** Compiles to bytecode that any JVM can run.
- **Detailed:** Source compiles once to platform-neutral bytecode; each OS has its own JVM that interprets/JITs that bytecode. "Write once, run anywhere." The JVM itself is platform-dependent.
- **Example:** The same `App.class` runs on Windows, Linux, and macOS JVMs.

<a id="q3"></a>

### 3. `==` vs `.equals()`?
- **Short:** `==` compares references/primitives; `equals` compares logical value.
- **Detailed:** For objects `==` checks identity (same reference). `equals` is overridable for value equality; if you override `equals` you must override `hashCode`. Watch the Integer cache (-128..127).
- **Example:** `new String("a") == new String("a")` is `false`, but `.equals` is `true`.

<a id="q4"></a>

### 4. What is the contract between `equals` and `hashCode`?
- **Short:** Equal objects must have equal hash codes.
- **Detailed:** If `a.equals(b)` then `a.hashCode() == b.hashCode()`. The reverse is not required (collisions allowed). Breaking this corrupts hash-based collections (`HashMap`, `HashSet`).
- **Example:** Use `Objects.hash(field1, field2)` consistently with the fields used in `equals`.

<a id="q5"></a>

<a id="q5"></a>

### 5. Why are Strings immutable?
- **Short:** Safety, caching (string pool), thread-safety, and hashcode caching.
- **Detailed:** Immutability allows the string pool to share literals safely, makes Strings safe to use as map keys, enables hashcode caching, and avoids accidental mutation across references (security: file paths, class loading).
- **Example:** `s.toUpperCase()` returns a new String; `s` is unchanged.

<a id="q6"></a>

### 6. `String` vs `StringBuilder` vs `StringBuffer`?
- **Short:** String immutable; StringBuilder mutable (not thread-safe, fast); StringBuffer mutable (synchronized).
- **Detailed:** Concatenating Strings in a loop creates many objects (O(n²)). Use `StringBuilder` for single-threaded building, `StringBuffer` only if multiple threads mutate the same buffer.
- **Example:** `StringBuilder sb=new StringBuilder(); for(...) sb.append(x);`

<a id="q7"></a>

### 7. What is autoboxing and a common pitfall?
- **Short:** Auto conversion primitive↔wrapper; pitfall is the Integer cache and NPE on unboxing null.
- **Detailed:** `Integer i = 5;` boxes; `int j = i;` unboxes. Unboxing a `null` Integer throws NPE. `==` on Integers compares references except cached -128..127.
- **Example:** `Integer a=1000,b=1000; a==b` is `false`; use `a.equals(b)`.

### 8. Checked vs unchecked exceptions?
- **Short:** Checked must be declared/handled; unchecked (RuntimeException) need not be.
- **Detailed:** Checked = recoverable conditions the caller should handle (`IOException`). Unchecked = programming errors (`NullPointerException`, `IllegalArgumentException`). `Error` (e.g. `OutOfMemoryError`) should not be caught.
- **Example:** `void read() throws IOException` (checked); `list.get(99)` throws unchecked `IndexOutOfBounds`.

### 8b. final vs finally vs finalize?
- **Short:** `final` = constant/non-overridable; `finally` = always-run block; `finalize` = deprecated GC hook.
- **Detailed:** `final` applies to variables (constant), methods (no override), classes (no subclass). `finally` runs after try/catch for cleanup. `finalize()` was called before GC—unreliable and removed/deprecated; use try-with-resources or `Cleaner`.
- **Example:** `try{...}finally{conn.close();}`

### 9. Overloading vs overriding?
- **Short:** Overloading = same name, different params (compile-time); overriding = redefine inherited method (runtime).
- **Detailed:** Overloading is resolved statically by argument types. Overriding uses dynamic dispatch on the runtime type; signature must match; `@Override` recommended; can't reduce visibility or broaden checked exceptions.
- **Example:** `add(int,int)` vs `add(double,double)` (overload); `Dog.sound()` overrides `Animal.sound()`.

### 10. Abstract class vs interface?
- **Short:** Abstract class = partial impl + state, single inheritance; interface = contract, multiple inheritance, default methods.
- **Detailed:** Use an abstract class for shared state/behavior and an "is-a" with common base. Use interfaces for capabilities and to allow a type to play many roles. Since Java 8 interfaces have `default`/`static`; since 9 `private` methods.
- **Example:** `abstract class Payment` (shared amount) vs `interface Comparable`.

### 11. Can you override a static method?
- **Short:** No — static methods are hidden, not overridden.
- **Detailed:** Statics belong to the class, resolved at compile time by reference type (method hiding). There's no dynamic dispatch.
- **Example:** A subclass `static m()` hides the parent's; calling via parent reference uses parent's.

### 12. What does `static` mean and when does a static block run?
- **Short:** Belongs to the class; static block runs once at class init.
- **Detailed:** Static fields/methods are shared; no instance needed. Static initializer blocks run once when the class is first actively used, top-to-bottom.
- **Example:** Caches/constants; `static { LOOKUP = build(); }`

### 13. What is the `this` and `super` keyword?
- **Short:** `this` = current object; `super` = parent.
- **Detailed:** `this.field` disambiguates; `this(...)` calls another constructor. `super.method()` calls parent's version; `super(...)` calls parent constructor (must be first statement).
- **Example:** `Dog(String n){ super(n); }`

### 14. What is a marker interface?
- **Short:** An empty interface signaling a capability.
- **Detailed:** Has no methods; used by JVM/libraries to tag classes (`Serializable`, `Cloneable`). Annotations are the modern alternative.
- **Example:** `class X implements Serializable {}`

### 15. What is the difference between `throw` and `throws`?
- **Short:** `throw` raises an exception; `throws` declares possible exceptions.
- **Detailed:** `throw new X()` actually throws; `throws X` in a method signature declares that callers must handle/declare it.
- **Example:** `void f() throws IOException { throw new IOException(); }`

### 16. What are records and when to use them?
- **Short:** Immutable data carriers (Java 16+).
- **Detailed:** Generate constructor, accessors, `equals`/`hashCode`/`toString`. Implicitly final; components final. Use for DTOs, value objects, tuple-like returns. Add compact constructors to validate.
- **Example:** `record Point(int x,int y){}`

### 17. What are sealed classes?
- **Short:** Restrict which types can extend/implement (Java 17+).
- **Detailed:** `sealed ... permits A, B`. Subtypes must be `final`, `sealed`, or `non-sealed`. Enables exhaustive switches without `default`. Pairs with records for algebraic data types.
- **Example:** `sealed interface Shape permits Circle, Square {}`

### 18. What is the difference between `==` for floating point and `BigDecimal`?
- **Short:** Floating point is inexact; use `BigDecimal` for money.
- **Detailed:** `0.1 + 0.2 != 0.3` due to binary representation. `BigDecimal` gives exact decimal arithmetic (with scale/rounding). Never use `double` for currency.
- **Example:** `new BigDecimal("0.1").add(new BigDecimal("0.2"))` is exactly 0.3.

### 19. Pass-by-value or pass-by-reference?
- **Short:** Always pass-by-value (for objects, the reference value is copied).
- **Detailed:** You can mutate the object a parameter points to, but reassigning the parameter doesn't affect the caller's variable.
- **Example:** Passing a `List` lets you `add` to it; setting `list = new ...` inside the method doesn't change the caller.

### 20. What is the diamond problem and how does Java handle default methods?
- **Short:** Conflict when two interfaces provide the same default method.
- **Detailed:** Java forces the implementing class to override and can call `Interface.super.method()` to disambiguate.
- **Example:** `class C implements A,B { public void m(){ A.super.m(); } }`

### 21. What is the difference between `Comparable` and `Comparator`?
- **Short:** `Comparable` = natural order (`compareTo`); `Comparator` = external/custom order.
- **Detailed:** Implement `Comparable` for the class's default ordering. Use `Comparator` for multiple/alternate orderings without modifying the class; compose with `comparing().thenComparing().reversed()`.
- **Example:** `list.sort(Comparator.comparing(Person::age).thenComparing(Person::name));`

### 22. What is the `transient` keyword?
- **Short:** Excludes a field from serialization.
- **Detailed:** A `transient` field is skipped during default serialization (restored as default value). Use for sensitive or derived data.
- **Example:** `private transient String password;`

### 23. What is a varargs method and a caveat?
- **Short:** `Type...` accepts 0+ args as an array; only one, last.
- **Detailed:** Internally an array; ambiguity with overloads and heap-pollution warnings with generics (`@SafeVarargs`).
- **Example:** `int sum(int... xs)`

### 24. What's the difference between `Iterator` and `ListIterator` and fail-fast?
- **Short:** `ListIterator` is bidirectional and list-only; fail-fast iterators throw `ConcurrentModificationException`.
- **Detailed:** Modifying a collection during iteration (except via the iterator) triggers `ConcurrentModificationException` in fail-fast collections. Concurrent collections are fail-safe.
- **Example:** Use `it.remove()` to delete during iteration safely.

### 25. What is the Object class and its key methods?
- **Short:** Root of all classes; `equals`, `hashCode`, `toString`, `getClass`, `clone`, `wait/notify`.
- **Detailed:** Every class implicitly extends `Object`. Override `equals/hashCode/toString` for value semantics and debugging.
- **Example:** `@Override public String toString(){...}`

---

## Rapid-Fire (Q → A)

1. Is Java pure OOP? → No; it has primitives (not objects).
2. Default value of `int`? → 0. Of `boolean`? → false. Of object reference? → null.
3. Can `main` be overloaded? → Yes, but JVM only calls `String[]` version.
4. Can `main` be `final`? → Yes.
5. Can we run a class without `main`? → Not as an app entry (since static-init-only is gone).
6. Size of `int`? → 32-bit. Of `char`? → 16-bit.
7. Is `char` signed? → No, it's unsigned (0..65535).
8. What is unicode in Java? → `char` is a UTF-16 code unit.
9. Can a constructor be private? → Yes (singletons, factories).
10. Can a constructor be `final`/`static`/`abstract`? → No.
11. Does a constructor return a value? → No (not even void).
12. What is constructor chaining? → `this()`/`super()` calls among constructors.
13. Default constructor provided when? → When you declare no constructor.
14. Can interfaces have constructors? → No.
15. Can interfaces have fields? → Yes, implicitly `public static final`.
16. Can interface methods be private? → Yes (Java 9+, for helper default methods).
17. Multiple inheritance of classes? → No; of interfaces/type → yes.
18. What is method hiding? → Static method "override" resolved by type.
19. Covariant return types? → Override can return a subtype.
20. Can you reduce visibility when overriding? → No.
21. Can overriding method throw broader checked exceptions? → No.
22. What is the `instanceof` operator? → Tests runtime type; supports pattern binding.
23. What is autounboxing NPE? → Unboxing a null wrapper throws NPE.
24. Ternary operator? → `cond ? a : b`.
25. Labeled break? → `break label;` exits an outer loop.
26. Difference `>>` and `>>>`? → Arithmetic (sign-extending) vs logical (zero-fill) right shift.
27. What is the comma in for? → Multiple init/update expressions.
28. Enhanced for limitation? → No index, can't modify the collection structurally.
29. Is `switch` fall-through? → Classic `:` falls through; arrow `->` does not.
30. `switch` on what types? → int, char, byte, short, enum, String, and patterns (21).
31. What is a text block? → `"""..."""` multi-line string (Java 15+).
32. What is `var`? → Local variable type inference (Java 10+).
33. Where can't `var` be used? → Fields, params, return types, without initializer.
34. Is `var` dynamic typing? → No, still static.
35. What is `final` variable? → Assign once.
36. Blank final? → `final` field assigned in constructor.
37. Effectively final? → Not reassigned; usable in lambdas.
38. What is shadowing? → Local var hiding a field/outer var.
39. Static vs instance initializer? → `static {}` runs at class load; `{}` per instance before constructor.
40. Order of init? → static fields/blocks (once) → instance fields/blocks → constructor.
41. Can we overload by return type? → No.
42. Can we overload main? → Yes.
43. Are arrays objects? → Yes; have `length` field.
44. Array covariance issue? → `Object[] = String[]` allows `ArrayStoreException` at runtime.
45. Jagged array? → Array of arrays with different lengths.
46. Default array values? → Zero/false/null per type.
47. Clone an array? → `arr.clone()` (shallow).
48. Deep vs shallow copy? → Shallow copies references; deep copies nested objects.
49. `Arrays.asList` gotcha? → Fixed-size, backed by array; `add` throws.
50. `List.of` gotcha? → Immutable, no nulls.
51. What is autoboxing cost? → Object allocation in tight loops.
52. NaN comparisons? → `NaN != NaN`; use `Double.isNaN`.
53. Integer overflow behavior? → Wraps silently; use `Math.addExact` to detect.
54. `Math.floorMod` vs `%`? → floorMod handles negatives to give non-negative result.
55. What is the string pool? → Cache of interned string literals.
56. `intern()`? → Returns the pooled instance of a string.
57. `equals` on StringBuilder? → Identity (not overridden); compare `toString()`.
58. Convert int→String? → `String.valueOf(i)` / `Integer.toString(i)`.
59. String→int? → `Integer.parseInt(s)`.
60. `compareTo` returns? → Negative/zero/positive.
61. Immutability benefits? → Thread safety, caching, safe sharing.
62. How to make a class immutable? → final class, final private fields, no setters, defensive copies.
63. Defensive copy? → Copy mutable inputs/outputs to protect invariants.
64. What is encapsulation? → Hiding state behind methods.
65. What is abstraction? → Exposing essentials, hiding details.
66. Inheritance vs composition? → Prefer composition ("has-a") for flexibility.
67. Liskov substitution? → Subtypes must be substitutable for base types.
68. What is a POJO? → Plain Old Java Object, no framework requirements.
69. What is a JavaBean? → POJO with no-arg ctor, getters/setters, Serializable.
70. `clone()` requirements? → Implement `Cloneable`, override `clone`.
71. Why is `Cloneable` flawed? → No `clone` method in interface; shallow by default.
72. Alternative to clone? → Copy constructor / static factory.
73. Singleton pitfalls? → Reflection, serialization, classloaders; enum solves them.
74. What is serialization? → Converting object to byte stream.
75. `serialVersionUID`? → Version id for serialization compatibility.
76. Externalizable vs Serializable? → Externalizable gives full manual control.
77. What's an inner class? → Non-static nested class; holds outer reference.
78. Static nested class? → No outer instance reference.
79. Local class? → Class defined in a method.
80. Anonymous class? → Unnamed one-off implementation.
81. Lambda vs anonymous class? → Lambda has no own `this`, targets functional interface.
82. Functional interface? → One abstract method (`@FunctionalInterface`).
83. Method reference kinds? → static, instance-of-particular, instance-of-arbitrary, constructor.
84. What is `Optional`? → Container for value-or-absent; avoid null.
85. Optional misuse? → As fields/params; calling `get()` blindly.
86. What is an enum? → Type-safe constant set; each a singleton.
87. Enum methods? → `values()`, `valueOf()`, `ordinal()`, `name()`.
88. EnumSet/EnumMap? → High-performance enum-keyed collections.
89. Annotation? → Metadata; processed at compile/runtime.
90. Meta-annotations? → `@Retention`, `@Target`, `@Inherited`, `@Documented`.
91. Retention policies? → SOURCE, CLASS, RUNTIME.
92. Reflection? → Inspect/modify classes at runtime.
93. Reflection downsides? → Slow, breaks encapsulation, no compile checks.
94. What is generics erasure? → Generic type info removed at runtime.
95. Raw type? → Generic used without type param (legacy).
96. Bounded type? → `<T extends Number>`.
97. Wildcard? → `<?>`, `<? extends T>`, `<? super T>`.
98. PECS? → Producer Extends, Consumer Super.
99. Can you create `new T[]`? → No (erasure); use reflection/Object[].
100. What is a NPE and how to avoid? → Null dereference; use Optional, Objects.requireNonNull, null checks.
101. `Objects.requireNonNull`? → Throws NPE early with a message.
102. `Objects.equals`? → Null-safe equals.
103. `Objects.hash`? → Convenience hashCode.
104. try-with-resources requires? → `AutoCloseable`.
105. Multi-catch syntax? → `catch (A | B e)`.
106. Can finally override return? → Yes (avoid `return` in finally).
107. Suppressed exceptions? → From try-with-resources close; `getSuppressed()`.
108. Custom exception? → Extend Exception/RuntimeException.
109. Exception chaining? → `new X("msg", cause)`.
110. Difference Error vs Exception? → Error = serious JVM issues; don't catch.
111. StackOverflowError cause? → Deep/infinite recursion.
112. OutOfMemoryError causes? → Heap exhaustion, leaks, metaspace.
113. What is a memory leak in Java? → Reachable-but-unused refs (static collections, listeners, ThreadLocal).
114. ThreadLocal use/risk? → Per-thread state; leaks in pools if not removed.
115. What is immutability vs thread-safety? → Immutable objects are inherently thread-safe.
116. Difference `length` vs `length()` vs `size()`? → array.length, String.length(), Collection.size().
117. Difference array vs ArrayList? → Fixed vs dynamic; primitives vs objects.
118. Autobox in collections? → Collections store objects, so primitives box.
119. What is a wrapper class? → Object form of a primitive.
120. Parse vs valueOf? → parseInt→primitive; valueOf→wrapper (cached).
121. Char to int? → Implicit widening or `Character.getNumericValue`.
122. String formatting? → `String.format` / `"%d".formatted(x)`.
123. printf? → `System.out.printf(...)`.
124. StringBuilder capacity? → Internal buffer; grows as needed.
125. Reverse a string? → `new StringBuilder(s).reverse()`.
126. Split with regex? → `s.split(",")` uses regex.
127. Difference replace vs replaceAll? → replace=literal, replaceAll=regex.
128. Immutable collections? → `List.of`, `Collections.unmodifiableList`.
129. What is boxing identity trap? → `==` on boxed values beyond cache range.
130. Why prefer interfaces in declarations? → Program to abstraction (`List l = new ArrayList()`).
131. Difference `extends` vs `implements`? → Class inherits class vs implements interface.
132. Can a class extend multiple classes? → No.
133. Can interface extend multiple interfaces? → Yes.
134. Default method conflict resolution? → Override + `I.super.m()`.
135. Static methods in interfaces? → Allowed (Java 8+), not inherited.
136. What is upcasting/downcasting? → To supertype (implicit) / to subtype (explicit, risky).
137. ClassCastException? → Invalid downcast.
138. instanceof before cast? → Safe-guards downcasts.
139. What is polymorphism benefit? → Code to base type, behavior varies.
140. Dynamic dispatch? → Runtime method selection by object type.
141. Static binding? → Compile-time (private/static/final/overloaded).
142. Constructor inheritance? → Constructors are not inherited.
143. Can abstract class have constructor? → Yes (called via subclass).
144. Can abstract method be private/static/final? → No (must be overridable).
145. Interface vs abstract for evolution? → Default methods evolve interfaces safely.
146. What is the `assert` keyword? → Debug-time invariant; disabled by default (`-ea`).
147. Why not use assert for arg validation? → It can be disabled; use exceptions.
148. What is `native`? → Method implemented in native code (JNI).
149. What is `strictfp`? → Portable floating-point (mostly obsolete).
150. What is `volatile`? → Visibility guarantee across threads.
151. What is `synchronized`? → Mutual exclusion + visibility via monitor.

> **More rapid-fire:** [04 Concurrency](../03-interview/04-Concurrency.md) · [03 Collections](../03-interview/03-Collections.md) · [06 Streams](../03-interview/06-Streams.md)

152. What makes good Java code? → Clear naming, small methods, immutability, proper exceptions, tests, and idiomatic APIs.
