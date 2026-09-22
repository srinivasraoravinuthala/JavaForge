# 29 → Networking & HTTP

**Previous:** [28 I/O & NIO](28-IOAndNIO.md) → **Next:** [30 JDBC](30-JDBC.md)

▶️ `pkg10networking/networking1InetAddress.java` · `networking5HttpClient.java`

---

## Layers you'll use

| Layer | Java API |
|-------|----------|
| Addresses | `InetAddress` |
| TCP sockets | `Socket`, `ServerSocket` |
| UDP | `DatagramSocket` |
| HTTP (modern) | `java.net.http.HttpClient` (Java 11+) |

---

## HttpClient example

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .GET()
    .build();
HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());
System.out.println(response.statusCode());
System.out.println(response.body());
```

Async: `client.sendAsync(request, handler).thenApply(...)`

**Related →** [Networking.md](../04-reference/05-Networking.md)

**Related →** [10 Java 9→21 Features](../03-interview/10-Java9To21Features.md)


**Next →** [30 JDBC](30-JDBC.md)
