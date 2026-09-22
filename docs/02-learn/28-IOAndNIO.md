# 28 — File I/O & NIO

**Previous:** [27 Design Patterns](27-DesignPatterns.md) · **Next:** [29 Networking](29-Networking.md)

▶️ `pkg9io/io1FileBasics.java` → `io6TryWithResourcesAndScanner.java`

---

## Two stream families

```
Bytes  → InputStream / OutputStream     (images, binary)
Chars  → Reader / Writer                (text, charset-aware)
```

Modern code prefers **NIO.2**: `Path`, `Files`.

---

## Quick reference

```java
// Read all text (modern)
String content = Files.readString(path, StandardCharsets.UTF_8);

// Write text
Files.writeString(path, "hello", StandardCharsets.UTF_8);

// Stream lines lazily
try (Stream<String> lines = Files.lines(path)) {
    lines.filter(l -> !l.isBlank()).forEach(System.out::println);
}

// Always use try-with-resources
try (var in = Files.newInputStream(path)) {
    byte[] data = in.readAllBytes();
}
```

---

## Class order in pkg9io

| # | File | Topic |
|---|------|-------|
| 1 | `io1FileBasics` | Legacy `File` API |
| 2 | `io2ByteStreams` | Binary I/O |
| 3 | `io3CharacterStreams` | Text I/O |
| 4 | `io4NioFilesAndPaths` | **Preferred** NIO.2 API |
| 5 | `io5Serialization` | Object ↔ bytes |
| 6 | `io6TryWithResourcesAndScanner` | Auto-close, parsing |

**Full guide →** [I/O](../04-reference/04-IO.md) · [11 I/O & Serialization](../03-interview/11-IOAndSerialization.md)

**Next →** [29 Networking](29-Networking.md)
