# I/O, NIO & Serialization — Interview Questions (82+)

See [`pkg9io`](../../pkg9io) and [`pkg20serialization`](../../pkg20serialization).

---

## Detailed Questions

### 1. Byte streams vs character streams?
- **Short:** `InputStream`/`OutputStream` for bytes; `Reader`/`Writer` for chars with encoding.
- **Detailed:** Text always needs charset (`UTF-8`). `InputStreamReader` bridges bytes→chars. Use streams for images/binary; readers for text.
- **Example:** `Files.newBufferedReader(path, UTF_8)`.

### 2. Buffered I/O why?
- **Short:** Reduces system calls by batching reads/writes.
- **Detailed:** `BufferedInputStream`/`BufferedReader` wrap raw streams. Default buffer sizes (~8K). Especially important for many small reads.
- **Example:** `new BufferedReader(new FileReader(f))` — prefer `Files.newBufferedReader`.

### 3. try-with-resources for I/O?
- **Short:** Mandatory pattern for streams, readers, channels.
- **Detailed:** All standard I/O classes implement `AutoCloseable`. Closing flushes buffers. Suppressed exceptions on close failures preserved.
- **Example:** `try (var out = Files.newOutputStream(path)) { ... }`

### 4. NIO.2 `Path` and `Files` API?
- **Short:** Modern file API: walk tree, copy, move, attributes, watch service.
- **Detailed:** `Paths.get` / `Path.of`. `Files.readString`, `writeString`, `walk`, `list`, `copy` with `StandardCopyOption`. Better than legacy `File`.
- **Example:** `Files.walk(path).filter(Files::isRegularFile).forEach(...)`.

### 5. NIO channels and buffers?
- **Short:** `FileChannel`, `SocketChannel`, `ByteBuffer` for block-oriented I/O.
- **Detailed:** Buffers have position/limit/capacity; flip for read→write. Channels support scatter/gather, memory-mapped files (`map`), non-blocking mode with selector.
- **Example:** Memory-mapped file for large read-mostly data.

### 6. Selector and non-blocking I/O?
- **Short:** Multiplex many channels on one thread; event-driven.
- **Detailed:** `Selector.select()` returns ready keys (ACCEPT, READ, WRITE). Foundation of scalable network servers. Netty builds on similar ideas.
- **Example:** Single thread serving thousands of idle connections.

### 7. Java serialization — how it works?
- **Short:** `ObjectOutputStream` writes object graph via reflection; needs `Serializable`.
- **Detailed:** Writes class metadata + field values. Handles object references and cycles. `serialVersionUID` for version compatibility. **Security risk** — don't deserialize untrusted data.
- **Example:** `implements Serializable` + `private static final long serialVersionUID = 1L;`

### 8. transient and Externalizable?
- **Short:** `transient` skips fields; `Externalizable` for custom read/write.
- **Detailed:** Skip sensitive or derivable fields with `transient`. `Externalizable` replaces default mechanism — full control but more work.
- **Example:** `transient` password hash; recompute on deserialize.

### 9. Why avoid Java native serialization?
- **Short:** Security, performance, brittleness, cross-language poor.
- **Detailed:** Gadget chains → RCE (Apache Commons Collections history). Slow, verbose. Prefer JSON (Jackson), Protobuf, Avro for services.
- **Example:** [`pkg20serialization`](../../pkg20serialization) compares formats.

### 10. Jackson vs JAXB vs YAML?
- **Short:** Jackson — JSON default; JAXB — XML binding; YAML — human config.
- **Detailed:** Jackson modules for dates, polymorphism (`@JsonSubTypes`). JAXB annotations on fields/getters. YAML via SnakeYAML — watch unsafe deserialization settings.
- **Example:** `serialization1JacksonDemo.java`.

### 11. Protobuf / Avro for services?
- **Short:** Schema-first, compact, evolvable wire formats.
- **Detailed:** Protobuf from `.proto`; backward compatible field numbers. Avro with schema registry for Kafka. Both faster and safer than Java serialization.
- **Example:** `serialization4ProtobufDemo.java`.

### 12. File locking and concurrent access?
- **Short:** `FileChannel.lock()` shared/exclusive; coordinate writers.
- **Detailed:** OS-level advisory locks. Doesn't replace application-level consistency for databases. `Files.move` atomic on same filesystem.
- **Example:** Exclusive lock during log file rotation.

---

## Rapid-Fire (Q → A)

1. File vs Path? → Prefer NIO.2 Path.
2. Absolute vs relative Path? → resolve/normalize.
3. Files.exists? → Also isDirectory, isRegularFile.
4. CREATE_NEW? → Fails if exists.
5. REPLACE_EXISTING? → Copy option.
6. ATOMIC_MOVE? → Same filesystem atomic.
7. DirectoryStream? → Try-with-resources glob.
8. WatchService events? → CREATE, MODIFY, DELETE.
9. StandardOpenOption APPEND? → Append to file.
10. StandardOpenOption DSYNC? → Sync data.
11. InputStream read returns -1? → EOF.
12. readAllBytes Java 9? → On InputStream.
13. transferTo InputStream? → Java 9+ to OutputStream.
14. ObjectInputStream risk? → Deserialization attacks.
15. ObjectInputFilter? → JDK 9+ allowlist filter.
16. serialVersionUID why? → Version mismatch InvalidClassException.
17. custom serialization? → writeObject/readObject private.
18. readResolve writeReplace? → Control deserialized instance.
19. Serializable marker? → Empty interface flags serializable.
20. NotSerializableException? → Non-serializable field.
21. static fields serialized? → No.
22. parent class not Serializable? → Parent fields default values.
23. Externalizable extends? → Serializable.
24. DataInputStream? → Primitive binary reads.
25. RandomAccessFile mode? → r, rw, rws, rwd.
26. FileChannel position? → seek-like.
27. ByteBuffer allocate vs allocateDirect? → Direct for native I/O.
28. ByteOrder? → BIG_ENDIAN default; set LITTLE_ENDIAN.
29. Charset.forName? → Prefer StandardCharsets constants.
30. MalformedInputException? → Bad charset decode.
31. CodingErrorAction REPLACE? → Replace bad chars.
32. Console class? → System.console() for password read.
33. System.in wrapped? → Scanner or BufferedReader.
34. PrintWriter autoFlush? → println flushes.
35. flush vs close? → flush pushes buffer; close flushes+closes.
36. Socket streams? → getInputStream/getOutputStream.
37. ServerSocket accept? → Blocks for connection.
38. try-with-resources socket? → Close closes streams too.
39. HttpClient replaces? → URLConnection for many cases.
40. URI vs URL? → URI identifies; URL accesses (legacy).
41. Base64 encoder? → java.util.Base64.
42. ZipInputStream? → Stream zip entries.
43. GZIPOutputStream? → Compress stream wrapper.
44. Serializable collections? → ArrayList etc. serializable.
45. HashMap serialization? → Yes but key/value types must be too.
46. JSON date format ISO? → Jackson JavaTimeModule.
47. @JsonIgnore? → Skip property.
48. @JsonProperty? → Name mapping.
49. YAML SnakeYAML safe? → Constructor restrict types.
50. Protobuf field numbers? → Never reuse.
51. Avro schema evolution? → Add fields with defaults.
52. Kafka serializer? → String, Avro, JSON common.
53. Deep copy via serialization? → Slow; prefer copy constructors.
54. Cloneable pitfalls? → Shallow clone default.
55. copyOf for collections? → Immutable copy not deep clone.
56. Files.size? → File size bytes.
57. Files.probeContentType? → MIME guess.
58. UserPrincipal? → File owner attribute.
59. PosixFilePermissions? → chmod-like on POSIX.
60. isSymbolicLink? → Files.isSymbolicLink.
61. readSymbolicLink? → Target path.
62. Path relativize? → Relative path between.
63. normalize dots? → Removes . and ..
64. SPI CharsetProvider? → Custom charset provider.
65. Reader mark/reset? → BufferedReader supports mark.
66. LineNumberReader? → Track line numbers.
67. StreamTokenizer legacy? → Prefer Scanner/ split.
68. ObjectOutputStream flush? → Flush before close.
69. SocketChannel non-blocking? → configureBlocking(false).
70. CompletableFuture supplyAsync IO? → Use virtual threads executor Java 21.
