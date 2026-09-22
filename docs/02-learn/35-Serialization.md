# 35 → Serialization & Data Formats

**Previous:** [34 Modules](34-Modules.md) → **Next:** [36 Performance & Build](36-PerformanceAndBuild.md)

▶️ `mvn test -f pkg20serialization/pom.xml`

---

## Formats compared

| Format | Human-readable | Schema | Cross-language | Use |
|--------|------------------|--------|----------------|-----|
| **JSON** (Jackson) | Yes | Optional | Yes | REST APIs, config |
| **XML** (JAXB) | Yes | Yes (XSD) | Yes | Legacy enterprise |
| **YAML** | Yes | Loose | Yes | Config files |
| **Protobuf** | No | `.proto` required | Yes | gRPC, high perf |
| **Avro** | No | Schema registry | Yes | Kafka pipelines |
| **Java serialization** | No | Java-only | No | ▶️ avoid for external data |

---

## Jackson example

```java
ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(user);
User back = mapper.readValue(json, User.class);
```

▶️ **Never deserialize untrusted Java native serialization** · remote code execution risk.

**Related →** [Serialization.md](../04-reference/12-Serialization.md)

**Related →** [11 I/O & Serialization](../03-interview/11-IOAndSerialization.md)


**Next →** [36 Performance & Build](36-PerformanceAndBuild.md)
