# Strings & Performance — Interview Questions (82+)

See [`pkg1core/core9StringsDemo.java`](../../pkg1core/core9StringsDemo.java) and [`pkg19performance`](../../pkg19performance).

---

## Detailed Questions

### 1. Why are Strings immutable in Java?
- **Short:** Security, thread-safety, string pool, and cached hashCode.
- **Detailed:** Immutability lets the string pool share literals safely, allows Strings as stable `HashMap` keys, caches `hashCode` at creation, and prevents mutation via references (class names, network paths). Trade-off: every "change" allocates a new object.
- **Example:** `s.toUpperCase()` returns new String; `s` unchanged.

### 2. String pool and intern()?
- **Short:** Pool stores unique literals; `intern()` adds heap strings to pool.
- **Detailed:** Literals live in pool (Java 7+ pool in heap). `new String("x")` creates heap object; `intern()` may return pooled reference. Overuse of `intern()` on dynamic strings can bloat pool / metaspace.
- **Example:** `"hello" == "hello"` true (pooled); `new String("hello") == "hello"` false.

### 3. String concatenation performance?
- **Short:** `+` in loop is O(n²); use `StringBuilder` or `String.join`.
- **Detailed:** Each `+` creates a new String copying prior content. Compiler optimizes constant folding (`"a"+"b"` → `"ab"`) but not loop concatenation. `StringBuilder` amortized O(n); thread-safe `StringBuffer` rarely needed.
- **Example:** `for (...) sb.append(x)` not `result += x`.

### 4. StringBuilder capacity tuning?
- **Short:** Set initial capacity if final size known to avoid resize copies.
- **Detailed:** Default 16 chars; grows by doubling + 2. `new StringBuilder(estimatedSize)` reduces array copies. `setLength(0)` reuses buffer for repeated builds.
- **Example:** Building CSV with known row count × avg length.

### 5. `String.format` vs `MessageFormat` vs text blocks?
- **Short:** `format` for printf-style; `MessageFormat` for locale patterns; text blocks for multi-line literals.
- **Detailed:** `"%s %d".formatted(name, age)` (Java 15+). Text blocks `"""` reduce escape noise. For i18n, `ResourceBundle` + `MessageFormat`.
- **Example:** See `core9StringsDemo` JSON text block.

### 6. `String` methods added in Java 11+?
- **Short:** `isBlank`, `strip`, `lines`, `repeat`, `isEmpty`.
- **Detailed:** `strip` uses Unicode whitespace (better than `trim` for some chars). `lines()` returns Stream of lines. `repeat(n)` for padding/separators.
- **Example:** `"  \u2000  ".strip().isBlank()` → true.

### 7. Charset and encoding gotchas?
- **Short:** Always specify `Charset` (UTF-8); never rely on platform default.
- **Detailed:** `String.getBytes()` without charset uses platform encoding — breaks cross-platform. `StandardCharsets.UTF_8` everywhere. Mojibake from wrong decode.
- **Example:** `s.getBytes(StandardCharsets.UTF_8)`.

### 8. `String` vs `char[]` for passwords?
- **Short:** `char[]` can be zeroed after use; `String` stays in pool/heap until GC.
- **Detailed:** `String` is immutable and may linger in memory dumps. `char[]` allows explicit wipe. Still not perfect (JIT copies). Prefer secure credential APIs.
- **Example:** `Arrays.fill(password, '\0')` after use.

### 9. Compact Strings (Java 9+)?
- **Short:** Internal `byte[]` + coder (LATIN1 or UTF16) saves memory for ASCII-heavy strings.
- **Detailed:** Implementation detail but explains memory: many strings use 1 byte/char. Transparent to API.
- **Example:** Millions of HTTP headers — memory win.

### 10. Regex performance traps?
- **Short:** Catastrophic backtracking on nested quantifiers; prefer possessive/atomic or RE2-style libs for untrusted input.
- **Detailed:** `(a+)+b` on long `aaaa...` can hang. `String.matches` compiles pattern each call — cache `Pattern`. Use `Matcher.find` for streaming.
- **Example:** Validate email with simple rules or library, not mega-regex.

### 11. When is `StringBuilder` slower than `+`?
- **Short:** Few fixed concatenations — compiler may use invokedynamic/string concat factory (Java 9+).
- **Detailed:** `a + b + c` with known strings is optimized. Loops and dynamic builds still need `StringBuilder`.
- **Example:** JMH in `pkg19performance/jmh-demo`.

### 12. Microbenchmark pitfalls?
- **Short:** Warmup JIT, avoid dead-code elimination, use JMH.
- **Detailed:** Naive `nanoTime` loops lie. Blackholes, forks, multiple JVM forks. Measure allocation with GC logs / JFR.
- **Example:** `StringConcatBenchmark` in pkg19performance.

---

## Rapid-Fire (Q → A)

1. String mutable? → No.
2. StringBuffer vs Builder? → Buffer synchronized; Builder faster single-thread.
3. == on String literals? → Often true (pool).
4. new String("a") == "a"? → false.
5. equals vs == for Strings? → equals for value.
6. hashCode cached? → Yes, after first compute.
7. concat method? → Creates new String.
8. intern() purpose? → Pool deduplication.
9. valueOf(int)? → Converts without `new String` for some cases.
10. toCharArray? → Defensive copy of chars.
11. substring (pre-Java 7)? → Shared char array (changed).
12. split regex? → Yes; escape `| . *`.
13. split limit param? → Controls array length.
14. join delimiter? → String.join or Collectors.joining.
15. replace vs replaceAll? → Literal vs regex.
16. replaceFirst? → Regex first match.
17. indexOf complexity? → O(n×m) naive for pattern.
18. contains? → indexOf >= 0.
19. startsWith offset? → Overload with index.
20. compareTo? → Lexicographic Unicode.
21. compareToIgnoreCase? → Case-insensitive.
22. isEmpty vs isBlank? → Blank checks whitespace.
23. strip vs trim? → strip = Unicode aware.
24. lines Stream? → Java 11+.
25. repeat? → Java 11+.
26. formatted method? → Java 15+ instance format.
27. Text blocks? → Java 15+ multi-line.
28. Indent on text block? → Normalize leading whitespace.
29. translateEscapes? → Unescape \\n etc.
30. String pool location? → Heap (Java 7+).
31. Too many intern()? → Pool pressure.
32. + in loop problem? → Quadratic copies.
33. StringBuilder not thread-safe? → Correct.
34. StringBuilder reverse? → In-place.
35. ensureCapacity? → Pre-grow buffer.
36. setLength? → Shrink logical length.
37. charAt bounds? → StringIndexOutOfBounds.
38. codePointCount? → Supplementary chars.
39. offsetByCodePoints? → Navigate Unicode.
40. getBytes UTF-8 size? → Up to 4 bytes per code point.
41. new String(bytes, charset)? → Decode bytes.
42. Reader vs InputStream text? → Reader char-oriented.
43. Scanner delimiter? → Token-based parsing.
44. Formatter locale? → Affects numbers/dates.
45. MessageFormat placeholders? → {0} {1}.
46. ResourceBundle encoding? → UTF-8 in modern JDK properties.
47. Collator for sorting? → Locale-sensitive string order.
48. Normalizer NFC? → Canonical composition Unicode.
49. Performance: avoid regex in hot path? → Often yes.
50. Pattern.compile cache? → Reuse compiled Pattern.
51. Matcher reset? → Reuse on same Pattern.
52. StringBuilder initial 16? → Default capacity.
53. AbstractStringBuilder? → Shared by Builder/Buffer.
54. Compact strings benefit? → Memory for Latin-1.
55. JMH why? → Reliable microbenchmarks.
56. Allocation rate metric? → Bytes/sec allocated.
57. TLAB? → Thread-local allocation buffer.
58. Escape analysis? → Stack allocate short-lived objects.
59. Scalar replacement? → Fields on stack if not escaped.
60. Profiling first rule? → Measure don't guess.
61. JFR? → JDK Flight Recorder low overhead.
62. async-profiler? → CPU/allocation flame graphs.
63. GC logs for alloc? → `-Xlog:gc*`.
64. String deduplication G1? → Optional same char[] sharing.
65. Object overhead? → Header + alignment (~16 bytes min).
66. char size? → 2 bytes (UTF-16 code unit).
67. Boolean in String.valueOf? → "true"/"false".
68. null + string concat? → "null" literal.
69. String.valueOf null? → "null".
70. concat with null reference? → NPE if reference null on instance concat.
