package pkg10networking;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/*
 * networking5HttpClient.java
 * --------------------------
 * The modern HTTP client: java.net.http.HttpClient (Java 11+).
 *
 * DEFINITION:
 *   HttpClient is the standard, fluent, HTTP/2-capable client. It supports
 *   sync and async calls, timeouts, redirects, and body handlers. This demo
 *   talks to a local server so it runs offline.
 *
 * KEY POINTS:
 *   - Build once (HttpClient.newBuilder()), reuse for many requests.
 *   - HttpRequest is immutable; choose GET/POST and a BodyPublisher.
 *   - BodyHandlers.ofString() turns the response body into a String.
 *   - sendAsync() returns a CompletableFuture for non-blocking calls.
 */
public class networking5HttpClient {

    public static void main(String[] args) throws Exception {
        // Local echo-ish server: GET returns text, POST echoes the request body
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/api", ex -> {
            String method = ex.getRequestMethod();
            byte[] reqBody = ex.getRequestBody().readAllBytes();
            String reply = "GET".equals(method)
                    ? "{\"message\":\"hello\"}"
                    : "{\"echo\":\"" + new String(reqBody, StandardCharsets.UTF_8) + "\"}";
            byte[] out = reply.getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, out.length);
            ex.getResponseBody().write(out);
            ex.close();
        });
        server.start();
        String base = "http://127.0.0.1:" + server.getAddress().getPort() + "/api";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        // 1) GET
        HttpRequest get = HttpRequest.newBuilder(URI.create(base))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> getResp = client.send(get, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET  status: " + getResp.statusCode());
        System.out.println("GET  body  : " + getResp.body());

        // 2) POST with a body
        HttpRequest post = HttpRequest.newBuilder(URI.create(base))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Ada\"}"))
                .build();
        HttpResponse<String> postResp = client.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println("\nPOST status: " + postResp.statusCode());
        System.out.println("POST body  : " + postResp.body());

        // 3) Async GET (non-blocking) — join just to print in this demo
        client.sendAsync(get, HttpResponse.BodyHandlers.ofString())
              .thenApply(HttpResponse::body)
              .thenAccept(b -> System.out.println("\nASYNC body : " + b))
              .join();

        server.stop(0);
    }
}
