# Java Versions: 5 → 21

Each section lists **features**, **why introduced**, and **interview questions**. Runnable demos are in [`pkg2versions`](../../pkg2versions).

---

## Java 5 (2004) — "the language modernization"
**Features:** Generics, enhanced for-each, autoboxing/unboxing, varargs, enums, annotations, static imports, `java.util.concurrent`, `Scanner`.
**Why:** Type safety (generics removed unchecked casts), readability (for-each), and concurrency utilities for multicore.
**Interview Qs:**
- Why generics? What is type erasure? → Compile-time safety; generic type info removed at runtime.
- Difference between `int[]` and `List<Integer>`? → Primitives vs autoboxed objects; arrays are covariant, generics are invariant.
- What problem do enums solve over `int` constants? → Type safety, namespacing, behavior per constant.

## Java 6 (2006)
**Features:** Performance improvements, scripting API (JSR 223), JDBC 4.0, pluggable annotations processing.
**Why:** Mostly internal/perf; few language changes.
**Interview Qs:** What changed in Java 6? → Mainly JVM/library performance, not language syntax.

## Java 7 (2011) — "Project Coin"
**Features:** try-with-resources, diamond operator `<>`, strings in switch, multi-catch, numeric literals with underscores, binary literals, NIO.2 (`java.nio.file`), Fork/Join framework, `invokedynamic`.
**Why:** Reduce boilerplate and resource leaks; better file I/O; parallelism via Fork/Join.
**Interview Qs:**
- How does try-with-resources work? → Resources implementing `AutoCloseable` are closed in reverse order automatically.
- What is the Fork/Join framework? → Work-stealing pool for divide-and-conquer parallelism.

## Java 8 (2014) — "the functional release" (LTS-era)
**Features:** Lambdas, Streams API, `Optional`, default & static interface methods, method references, new `java.time`, `CompletableFuture`, Nashorn JS, PermGen → Metaspace.
**Why:** Enable functional-style, declarative data processing; fix the broken old Date API; remove fixed PermGen.
**Interview Qs:**
- Lambda vs anonymous class? → Lambda has no own `this`, no separate class file, targets a functional interface.
- Are streams lazy? → Yes; intermediate ops are lazy, terminal ops trigger execution.
- Why default methods? → Evolve interfaces without breaking implementers (e.g. `Collection.stream()`).
- `map` vs `flatMap`? → `map` 1:1 transform; `flatMap` flattens nested streams.
- `Optional` best practices? → Use as return type, not field/param; avoid `get()`.

## Java 9 (2017)
**Features:** Module System (JPMS / Project Jigsaw), `jshell` REPL, collection factory methods (`List.of`), private interface methods, `Stream.takeWhile/dropWhile/iterate`, reactive `Flow` API, multi-release JARs.
**Why:** Strong encapsulation & reliable configuration via modules; better tooling (REPL).
**Interview Qs:**
- What is a module? → A named, self-describing collection of packages with explicit `requires`/`exports`.
- `List.of` vs `Arrays.asList`? → `List.of` is immutable and rejects nulls; `asList` is fixed-size backed by the array.

## Java 10 (2018)
**Features:** `var` (local-variable type inference), application class-data sharing, parallel full GC for G1.
**Why:** Reduce verbosity for local variables while keeping static typing.
**Interview Qs:** Where can `var` be used? → Local variables with an initializer; not fields, params, or return types.

## Java 11 (2018, LTS)
**Features:** Standard `HttpClient`, `String` methods (`strip`, `isBlank`, `lines`, `repeat`), `Files.readString/writeString`, run single-file source (`java File.java`), `var` in lambda params, removed Java EE/CORBA modules.
**Why:** First long-term-support after 8; modern HTTP, productivity helpers.
**Interview Qs:** `strip()` vs `trim()`? → `strip` is Unicode-aware; `trim` only removes ≤ U+0020.

## Java 12–13
**Features:** Switch expressions (preview), text blocks (preview), Shenandoah GC, G1 improvements.
**Why:** Iterate on syntax via previews; lower GC latency.

## Java 14 (2020)
**Features:** Switch expressions (final), records (preview), pattern matching for `instanceof` (preview), helpful NullPointerExceptions, NUMA-aware G1.
**Interview Qs:** Switch expression vs statement? → Expression returns a value, uses `->`/`yield`, no fall-through.

## Java 15 (2020)
**Features:** Text blocks (final), sealed classes (preview), ZGC & Shenandoah production, hidden classes, EdDSA.
**Interview Qs:** What problem do text blocks solve? → Multi-line strings without escaping/concatenation.

## Java 16 (2021)
**Features:** Records (final), pattern matching for `instanceof` (final), `Stream.toList()`, packaging tool `jpackage`, strong encapsulation of JDK internals.
**Interview Qs:** What does a record generate? → Canonical constructor, accessors, `equals`/`hashCode`/`toString`; it's final & immutable.

## Java 17 (2021, LTS)
**Features:** Sealed classes (final), pattern matching for switch (preview), removed deprecated stuff, new macOS/AArch64 ports, deterministic `SecureRandom`.
**Why:** Second modern LTS; mature ADTs (records + sealed) enabling exhaustive matching.
**Interview Qs:**
- Why sealed classes? → Restrict subtypes for exhaustive switches and safer modeling.
- Records + sealed = ? → Algebraic data types in Java.

## Java 18–20
**Features:** UTF-8 by default (18), simple web server, code snippets in Javadoc, pattern matching for switch & record patterns (previews), virtual threads & structured concurrency (previews 19–20).
**Why:** Preview/refine Project Loom and Amber features.

## Java 21 (2023, LTS) — project target
**Features:** **Virtual threads (final)**, **pattern matching for switch (final)**, **record patterns (final)**, **sequenced collections**, generational ZGC, key encapsulation API; previews: string templates, structured concurrency, scoped values.
**Why:** Massive concurrency for blocking workloads (Loom), expressive pattern matching (Amber), uniform ordered collection access.
**Interview Qs:**
- Virtual vs platform threads? → Virtual threads are JVM-scheduled, cheap (millions possible), ideal for blocking I/O; platform threads map 1:1 to OS threads.
- What is a record pattern? → Deconstruct a record into its components in `switch`/`instanceof`.
- What are sequenced collections? → Interfaces (`SequencedCollection/Set/Map`) adding `getFirst/getLast/reversed`.
- When NOT to use virtual threads? → CPU-bound work (no benefit), or with thread-local-heavy / pinned (`synchronized`) blocking sections.

---

## LTS Summary
LTS releases: **8, 11, 17, 21** (and 25). Most enterprises target these. The 6-month release cadence (since Java 9) delivers smaller, incremental features with previews before finalization.
