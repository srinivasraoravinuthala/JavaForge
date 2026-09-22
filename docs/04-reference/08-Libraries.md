# Essential Built-in Libraries (`pkg13libs`)

Runnable demos: [`pkg13libs`](../../pkg13libs). Run with `java pkg13libs/libs1DateTimeApi.java`.

These are the high-value standard-library APIs every Java developer should know —
all part of the JDK, **no dependencies**.

## Files in this package
| # | File | Package | What it covers |
|---|------|---------|----------------|
| 1 | `libs1DateTimeApi` | `java.time` | dates, times, zones, `Period`/`Duration`, formatting |
| 2 | `libs2RegexPattern` | `java.util.regex` | match/find/groups/replace/split |
| 3 | `libs3CryptoAndHashing` | `java.security`, `javax.crypto` | SHA-256, HMAC, Base64, `SecureRandom` |
| 4 | `libs4RandomAndUuid` | `java.util` | `Random`, `ThreadLocalRandom`, `SecureRandom`, `UUID` |
| 5 | `libs5BigNumbersAndMath` | `java.math` | `BigInteger`, `BigDecimal`, `Math` |
| 6 | `libs6Reflection` | `java.lang.reflect` | inspect/invoke types at runtime |
| 7 | `libs7Annotations` | `java.lang.annotation` | custom annotations + a mini test runner |
| 8 | `libs8Logging` | `java.util.logging` | levels, handlers, formatters |

## Quick rules of thumb
- **Dates:** use `java.time` (immutable, thread-safe). Never `Date`/`Calendar`.
- **Money/precision:** use `BigDecimal` with explicit scale + `RoundingMode`. Never `double`.
- **Regex:** compile `Pattern` once, reuse it; prefer named groups.
- **Security:** `SecureRandom` for tokens/salts/keys; for passwords use a slow KDF (PBKDF2/bcrypt/Argon2), not a plain hash.
- **Reflection:** powerful but slow and unsafe — the basis of Spring/JUnit/Jackson; use sparingly in app code.
- **Annotations:** `RetentionPolicy.RUNTIME` makes them readable via reflection (how frameworks discover your code).
- **Logging:** JUL ships with the JDK; most apps prefer **SLF4J + Logback/Log4j2**, but the concepts (levels/handlers/formatters) are identical.
