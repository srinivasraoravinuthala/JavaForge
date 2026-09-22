# Exceptions — Interview Questions (77+)

See [`pkg1core/core18ExceptionsDemo.java`](../../pkg1core/core18ExceptionsDemo.java).

---

## Detailed Questions

### 1. Exception hierarchy in Java?
- **Short:** `Throwable` → `Error` (don't catch) and `Exception` → checked vs `RuntimeException` (unchecked).
- **Detailed:** Checked exceptions extend `Exception` (not `RuntimeException`) and must be declared or caught. Unchecked are programming bugs or unrecoverable usage (`NPE`, `IllegalArgumentException`). `Error` signals serious JVM problems (`OutOfMemoryError`, `StackOverflowError`).
- **Example:** `IOException` checked; `NullPointerException` unchecked.

### 2. When to use checked vs unchecked?
- **Short:** Checked for recoverable, expected failures; unchecked for bugs and programming errors.
- **Detailed:** Modern style favors unchecked for most application code (Spring, Hibernate use unchecked wrappers). Checked forces every caller to handle — can clutter APIs. Use checked when the caller can meaningfully recover (retry I/O).
- **Example:** File not found → checked `IOException` or wrap in `UncheckedIOException`.

### 3. try-with-resources — how does it work?
- **Short:** Auto-closes `AutoCloseable` resources; suppresses close exceptions properly.
- **Detailed:** Compiler desugars to try/finally with `close()` called. If both try and close throw, primary exception is preserved; close exception is **suppressed**. Resources declared in try header must be effectively final.
- **Example:** `try (var in = Files.newInputStream(path)) { ... }`

### 4. Multi-catch and rethrow rules?
- **Short:** `catch (A | B e)` if A and B are unrelated; can't combine if one subclasses the other.
- **Detailed:** Catch parameter is implicitly `final`. Rethrowing narrows the declared type if the catch block only throws a subtype. Annotate with `@Throws` for documentation.
- **Example:** `catch (SQLException | IOException e)`.

### 5. Exception chaining — why?
- **Short:** Preserve root cause when wrapping at layer boundaries.
- **Detailed:** `new ServiceException("save failed", sqlEx)` keeps stack trace of original. Without `cause`, debugging production issues is painful. Always pass `cause` to wrapper constructor.
- **Example:** `throw new DataAccessException("user " + id, e);`

### 6. finally vs try-with-resources?
- **Short:** `finally` always runs (unless `System.exit`); try-with-resources is the idiomatic close pattern.
- **Detailed:** `return` in try still runs `finally` before actually returning (can clobber return value if finally also returns). Prefer try-with-resources over manual finally-close.
- **Example:** Returning from try while finally modifies state — gotcha in interviews.

### 7. Custom exception design?
- **Short:** Extend `Exception` for checked, `RuntimeException` for unchecked; meaningful messages; optional error codes.
- **Detailed:** Provide constructors `(String)`, `(String, Throwable)`, `(Throwable)`. Don't over-hierarchy. Domain exceptions (`InsufficientFundsException`) aid handling at boundaries.
- **Example:** See `core18ExceptionsDemo.InsufficientFundsException`.

### 8. Should you catch `Exception` or `Throwable`?
- **Short:** Avoid broad catch in application code; catch specific types.
- **Detailed:** `catch (Exception e)` swallows programming bugs. `catch (Throwable)` includes `Error` — almost never correct. Top-level handlers (HTTP filter) may log and map to 500.
- **Example:** Framework `@ControllerAdvice` maps `ValidationException` → 400, unknown → 500.

### 9. Stack trace cost and logging?
- **Short:** Creating exceptions is cheap until you read stack trace; logging fills stack at throw/catch site.
- **Detailed:** `new Exception()` without throw is sometimes used for stack capture (expensive). Use logging frameworks; don't `printStackTrace()` in production. Include correlation IDs.
- **Example:** `log.error("order {} failed", id, ex);`

### 10. Suppressed exceptions?
- **Short:** Secondary exceptions during close/add suppressed to primary.
- **Detailed:** `Throwable.getSuppressed()` returns them. Important when debugging try-with-resources with multiple failures.
- **Example:** Read fails, then close also fails — both visible.

### 11. Assertions vs exceptions?
- **Short:** `assert` for internal invariants (disabled by default); exceptions for contract violations callers should handle.
- **Detailed:** Enable with `-ea`. Never use assert for user input validation — it can be off in production.
- **Example:** `assert index >= 0` in private method after internal logic.

### 12. Best practices for exception messages?
- **Short:** Actionable, include context (ids, operation), no secrets.
- **Detailed:** "Failed to save order 12345 for user 99" beats "Error". Don't log passwords or tokens. Internationalize user-facing messages separately from log messages.
- **Example:** `throw new OrderNotFoundException(orderId);`

---

## Rapid-Fire (Q → A)

1. Root of exceptions? → Throwable.
2. Don't catch? → Error (generally).
3. Checked parent? → Exception (not RuntimeException).
4. Unchecked parent? → RuntimeException.
5. Must declare checked? → throws or catch.
6. NPE type? → Unchecked.
7. IllegalArgumentException when? → Bad argument to method.
8. IllegalStateException when? → Object state wrong for call.
9. IOException? → Checked I/O failure.
10. try-with-resources interface? → AutoCloseable.
11. Closeable vs AutoCloseable? → Closeable extends AutoCloseable; close throws IOException.
12. Multi-catch restriction? → No subclass + supertype together.
13. Catch param final? → Implicitly final.
14. throw vs throws? → throw statement vs method signature.
15. throw null? → NPE at throw site.
16. finally without catch? → Yes, try-finally.
17. finally always runs? → Except System.exit / JVM crash.
18. return in try + finally? → finally runs before return completes.
19. try-with-resources compile? → Desugared to try-finally-close.
20. Suppressed exceptions API? → getSuppressed(), addSuppressed().
21. initCause? → Set cause after construction (once).
22. getCause? → Underlying throwable.
23. fillInStackTrace? → Capture stack; expensive.
24. printStackTrace production? → Avoid; use logger.
25. Rethrow same exception? → Stack trace preserved.
26. Wrap and throw new? → Pass cause constructor.
27. Exception as control flow? → Anti-pattern (e.g. parse int via exception).
28. Validation: exception vs return code? → Exception or Result type for domain.
29. Optional vs exception for missing? → Optional for expected absence; exception for exceptional.
30. Business vs technical exception? → Business may map to 4xx; technical to 5xx.
31. Retry on which exceptions? → Transient (timeout), not validation.
32. Idempotent retry safe? → Design operations accordingly.
33. Circuit breaker relation? → Stop calling failing dependency.
34. Global exception handler Spring? → @ControllerAdvice.
35. Servlet filter exception? → Map to error response.
36. SQLException checked? → Yes (often wrapped by JDBC templates).
37. DataAccessException? → Spring unchecked wrapper.
38. PersistenceException? → JPA unchecked wrapper.
39. CompletionException? → Wraps async stage failures.
40. ExecutionException? → Future.get() wrapper.
41. UncheckedIOException? → Wrap checked IO.
42. Sneaky throws (Lombok)? → Controversial; hides checked.
43. try-catch-performance? → No cost when no throw; throw is expensive.
44. Table exception in DB? → Use SQL state codes.
45. OOM catchable? → Technically yes; rarely recoverable.
46. StackOverflowError? → Usually infinite recursion.
47. ExceptionInInitializerError? → Static init failed.
48. Unhandled exception thread? → Default handler prints and may exit.
49. setDefaultUncaughtExceptionHandler? → Custom thread crash handling.
50. try-with multiple resources? → Semicolon-separated; closed reverse order.
51. Resource must be? → Effectively final.
52. Custom close exception? → Added as suppressed.
53. try-with on String? → No; String not AutoCloseable.
54. Cleaner (Java 9+)? → Alternative to finalize for native cleanup.
55. PhantomReference use? → Post-mortem cleanup.
56. finalize status? → Deprecated; don't use.
57. try-catch in loop? → OK; avoid throw as normal path.
58. Exception hierarchy design? → Shallow tree preferred.
59. Error codes + exceptions? → Complement, not replace.
60. Log and rethrow? → Yes at boundary; don't swallow.
61. Swallow exception smell? → Empty catch block.
62. catch log throw? → Preserve stack with cause.
63. fail-fast collections? → CME on concurrent mod.
64. CME is unchecked? → Yes, RuntimeException.
65. Specifying behavior in javadoc? → @throws tags.
