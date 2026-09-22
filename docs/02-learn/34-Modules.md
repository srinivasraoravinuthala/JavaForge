# 34 → Modules & SPI

**Previous:** [33 Testing](33-Testing.md) → **Next:** [35 Serialization](35-Serialization.md)

▶️ `pkg15modules/modules1JpmsConcepts.java` · `modules2ServiceLoaderSpi`

---

## Java Platform Module System (JPMS)

`module-info.java` declares:

```java
module com.myapp {
    requires java.sql;           // depends on
    exports com.myapp.api;       // public API
    opens com.myapp.internal to spring.core;  // reflection
}
```

**Why modules?** Strong encapsulation, smaller runtime images (`jlink`), explicit dependencies.

---

## ServiceLoader (SPI)

Define interface in one module, implementations in others, discover at runtime:

```
META-INF/services/com.example.Encoder · lists impl classes
ServiceLoader.load(Encoder.class)
```

See `pkg15modules/spi-demo/` for a working example.

**Related →** [Modules.md](../04-reference/10-Modules.md)

**Related →** [10 Java 9→21 Features](../03-interview/10-Java9To21Features.md)


**Next →** [35 Serialization](35-Serialization.md)
