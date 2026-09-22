# Networking (`pkg10networking`)

Runnable demos: [`pkg10networking`](../../pkg10networking). Every demo runs **fully offline** (loopback / in-process server).

## The stack
```
Application : your code (HTTP, custom protocols)
Transport   : TCP (reliable, ordered)  |  UDP (fast, best-effort)
Network     : IP (addresses, routing)   <- InetAddress
```

## TCP vs UDP
| | TCP | UDP |
|---|-----|-----|
| Connection | yes (handshake) | none |
| Delivery | guaranteed, ordered | best-effort, unordered |
| Overhead | higher | low |
| Use for | HTTP, DB, files | DNS, video, games, metrics |
| Java | `Socket` / `ServerSocket` | `DatagramSocket` / `DatagramPacket` |

## Files in this package
| # | File | Topic |
|---|------|-------|
| 1 | `networking1InetAddress` | DNS, hostnames, local interfaces |
| 2 | `networking2TcpSocket` | TCP echo client+server (in-process) |
| 3 | `networking3UdpDatagram` | UDP send/receive over loopback |
| 4 | `networking4UrlAndConnection` | `URL` / `URLConnection` (legacy HTTP) |
| 5 | `networking5HttpClient` | `java.net.http.HttpClient` (Java 11+, modern) |

## HTTP clients: old vs new
- **Legacy:** `URL.openConnection()` — verbose, blocking, no HTTP/2.
- **Modern (use this):** `HttpClient` — fluent, HTTP/2, sync + async, timeouts, redirects.

```java
HttpClient client = HttpClient.newHttpClient();
HttpResponse<String> r = client.send(
    HttpRequest.newBuilder(URI.create("https://api.example.com")).GET().build(),
    HttpResponse.BodyHandlers.ofString());
```

## Tips
- Bind to port `0` to let the OS pick a free ephemeral port (great for tests).
- `accept()`, `receive()`, and `read()` **block** — use threads or `setSoTimeout`.
- Reuse one `HttpClient`; configure `connectTimeout` + per-request `timeout`.
