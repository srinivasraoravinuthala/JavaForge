package pkg12restapi;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
 * restapi5HttpClientConsume.java
 * ------------------------------
 * Consuming a REST API like a real client: headers, timeouts, status checks,
 * error handling, and concurrent (async) requests.
 *
 * DEFINITION:
 *   The flip side of restapi3: how a client app talks to a REST service. We host
 *   a tiny server, then call it the way production code would.
 *
 * KEY POINTS:
 *   - Reuse one HttpClient; set connectTimeout + per-request timeout.
 *   - Check statusCode() and branch on 2xx vs 4xx/5xx.
 *   - sendAsync returns CompletableFuture — fan out N calls and join them.
 *   - Always send Accept/Content-Type and (when needed) Authorization headers.
 */
public class restapi5HttpClientConsume {

    public static void main(String[] args) throws Exception {
        // Stand up a server with a couple of endpoints
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/ok", ex -> write(ex, 200, "{\"status\":\"ok\"}"));
        server.createContext("/secure", ex -> {
            String auth = ex.getRequestHeaders().getFirst("Authorization");
            if ("Bearer token123".equals(auth)) write(ex, 200, "{\"data\":\"secret\"}");
            else write(ex, 401, "{\"error\":\"unauthorized\"}");
        });
        server.start();
        String base = "http://127.0.0.1:" + server.getAddress().getPort();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        // 1) Simple GET with status handling
        HttpResponse<String> ok = client.send(
                HttpRequest.newBuilder(URI.create(base + "/ok"))
                        .timeout(Duration.ofSeconds(2))
                        .header("Accept", "application/json")
                        .GET().build(),
                HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /ok -> " + ok.statusCode());
        System.out.println(ok.statusCode() / 100 == 2 ? "  success: " + ok.body()
                                                       : "  failure: " + ok.body());

        // 2) Auth: without token (401) then with token (200)
        for (String token : new String[]{null, "Bearer token123"}) {
            HttpRequest.Builder b = HttpRequest.newBuilder(URI.create(base + "/secure")).GET();
            if (token != null) b.header("Authorization", token);
            HttpResponse<String> r = client.send(b.build(), HttpResponse.BodyHandlers.ofString());
            System.out.printf("GET /secure (token=%s) -> %d %s%n",
                    token == null ? "none" : "yes", r.statusCode(), r.body());
        }

        // 3) Concurrent async calls, joined together
        System.out.println("\nFiring 4 async requests:");
        List<CompletableFuture<String>> futures = List.of(0, 1, 2, 3).stream()
                .map(i -> client.sendAsync(
                        HttpRequest.newBuilder(URI.create(base + "/ok")).GET().build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApply(resp -> "  req#" + i + " -> " + resp.statusCode()))
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        futures.forEach(f -> System.out.println(f.join()));

        server.stop(0);
    }

    static void write(com.sun.net.httpserver.HttpExchange ex, int status, String body) throws java.io.IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(status, bytes.length);
        ex.getResponseBody().write(bytes);
        ex.close();
    }
}
