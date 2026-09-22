# 32 → Standard Libraries

**Previous:** [31 REST APIs](31-RestAPIs.md) → **Next:** [33 Testing](33-Testing.md)

▶️ `pkg13libs/libs1DateTimeApi.java` · `libs8Logging.java`

---

## What's in pkg13libs

| # | File | API |
|---|------|-----|
| 1 | `libs1DateTimeApi` | `java.time` · `LocalDate`, `ZonedDateTime`, `Duration` |
| 2 | `libs2RegexApi` | `Pattern`, `Matcher` |
| 3 | `libs3BigDecimalDemo` | Money / precise decimals |
| 4 | `libs4OptionalAdvanced` | Optional patterns |
| 5 | `libs5CollectionsUtilities` | `Collections`, `Arrays` helpers |
| 6 | `libs6ReflectionBasics` | Class metadata |
| 7 | `libs7AnnotationsDemo` | Custom annotations |
| 8 | `libs8Logging` | `java.util.logging` / SLF4J concepts |

---

## java.time · replace old Date API

```java
LocalDate today = LocalDate.now();
LocalDateTime meeting = LocalDateTime.of(2025, 6, 14, 10, 30);
ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
Duration d = Duration.between(start, end);
```

Never use `java.util.Date` in new code.

---

## BigDecimal for money

```java
BigDecimal price = new BigDecimal("19.99");   // from String, not double!
BigDecimal total = price.multiply(BigDecimal.valueOf(3))
                        .setScale(2, RoundingMode.HALF_UP);
```

**Related →** [Libraries.md](../04-reference/08-Libraries.md)

**Milestone:** You completed **Applied Java** (chapters 28→32). ▶️

**Related →** [14 Effective Java](../03-interview/14-EffectiveJava.md)


**Next →** [33 Testing](33-Testing.md)
