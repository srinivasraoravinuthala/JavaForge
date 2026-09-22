# 21 — Java Version Features (5 → 21)

**Previous:** [20 Streams & Optional](20-StreamsAndOptional.md) · **Next:** [22 Data Structures](22-DataStructures.md)

▶️ `pkg2versions/versions1Java5Features.java` → `versions6Java21Features.java`

---

## Timeline summary

| Version | Year | Headline features |
|---------|------|-------------------|
| **5** | 2004 | Generics, enums, autoboxing, for-each, annotations |
| **7** | 2011 | try-with-resources, diamond `<>`, NIO.2 |
| **8** | 2014 | Lambdas, Streams, Optional, `java.time` |
| **9** | 2017 | Modules, `List.of`, `takeWhile` |
| **10** | 2018 | `var` local inference |
| **11** | 2018 LTS | HttpClient, String methods, run `.java` directly |
| **14** | 2020 | Switch expressions |
| **15** | 2020 | Text blocks |
| **16** | 2021 | Records, `instanceof` patterns |
| **17** | 2021 LTS | Sealed classes |
| **21** | 2023 LTS | Virtual threads, record patterns, sequenced collections |

---

## Run each demo

```bash
java pkg2versions/versions1Java5Features.java
java pkg2versions/versions2Java7Features.java
java pkg2versions/versions3Java8Features.java
java pkg2versions/versions4Java9To11Features.java
java pkg2versions/versions5Java17Features.java
java pkg2versions/versions6Java21Features.java
```

---

## What to learn from each

### Java 5 — foundations still used daily
Generics, enhanced for, `enum`, `ExecutorService`.

### Java 8 — biggest mindset shift
Stop writing loops for every transformation — use Streams. Replace `Date` with `java.time`.

### Java 9–11 — cleaner code
`List.of()`, `var`, `Files.readString`, `HttpClient`.

### Java 14–17 — modern syntax
Switch expressions, text blocks, records, sealed classes.

### Java 21 — concurrency revolution
```java
Thread.startVirtualThread(() -> fetchData());
```

Use virtual threads for **blocking I/O at scale** (HTTP calls, DB queries).

---

**Full reference →** [Java Versions](../04-reference/01-JavaVersions.md) · [10 Java 9–21 Features](../03-interview/10-Java9To21Features.md)

**Next →** [22 Data Structures](22-DataStructures.md)
