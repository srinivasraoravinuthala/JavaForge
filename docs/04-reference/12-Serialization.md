# Serialization formats (`pkg20serialization`)

Maven module — run demos with `mvn exec:java -f pkg20serialization/pom.xml -Dexec.mainClass=pkg20serialization.serialization1JacksonDemo`

## Format comparison

| Format | Schema | Human-readable | Typical use |
|--------|--------|----------------|-------------|
| JSON (Jackson) | optional | yes | REST APIs, config |
| XML (JAXB) | yes (XSD) | yes | legacy enterprise, SOAP |
| YAML (SnakeYAML) | informal | yes | K8s, Spring config |
| Protobuf | required (.proto) | no (binary) | gRPC, high-throughput RPC |
| Avro | required | no (binary) | Kafka, schema evolution |

## When to use what

- **REST / general APIs:** JSON + Jackson (default in Spring Boot).
- **Config files:** YAML or properties.
- **High-performance RPC:** Protobuf + gRPC.
- **Kafka with evolution:** Avro + schema registry.
- **Legacy SOAP/XML:** JAXB.

## Security

- Never deserialize untrusted Java serialization (`io5Serialization`) — RCE risk.
- JSON/XML parsers: disable default typing in Jackson; validate input size.
- YAML: `SafeConstructor` — unsafe YAML can execute code.

## Files

| Class | Library |
|-------|---------|
| `serialization1JacksonDemo` | Jackson |
| `serialization2JaxbDemo` | Jakarta XML Bind |
| `serialization3YamlDemo` | SnakeYAML |
| `serialization4ProtobufDemo` | protobuf-java |
| `serialization5AvroDemo` | Apache Avro |

## Jackson quick reference

```java
ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(obj);
MyType obj = mapper.readValue(json, MyType.class);
```

Records map cleanly to JSON objects out of the box.
