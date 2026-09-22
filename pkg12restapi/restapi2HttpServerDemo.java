package pkg12restapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/*
 * restapi2HttpServerDemo.java
 * ---------------------------
 * Build a minimal HTTP server with the JDK's built-in com.sun.net.httpserver.
 *
 * DEFINITION:
 *   HttpServer (module jdk.httpserver, no dependencies) lets you stand up an
 *   HTTP endpoint in a few lines. Each "context" maps a path to a handler that
 *   reads the request and writes a response. Great for demos, tools, and tests.
 *
 * KEY POINTS:
 *   - createContext(path, handler) registers a route.
 *   - HttpExchange = one request/response pair (method, headers, body, status).
 *   - sendResponseHeaders(status, bodyLength) THEN write the body, then close().
 *   - This demo starts the server and calls it with HttpClient — fully offline.
 */
public class restapi2HttpServerDemo {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);

        // Route: GET /time  -> plain text
        server.createContext("/time", ex ->
                respond(ex, 200, "text/plain", "server time: " + System.currentTimeMillis()));

        // Route: GET /greet?name=Ada -> reads a query parameter
        server.createContext("/greet", ex -> {
            String query = ex.getRequestURI().getQuery();          // e.g. name=Ada
            String name = "World";
            if (query != null && query.startsWith("name="))
                name = query.substring("name=".length());
            respond(ex, 200, "text/plain", "Hello, " + name + "!");
        });

        server.start();
        int port = server.getAddress().getPort();
        System.out.println("Server listening on http://127.0.0.1:" + port);

        // Call our own server to prove it works
        HttpClient client = HttpClient.newHttpClient();
        call(client, "http://127.0.0.1:" + port + "/time");
        call(client, "http://127.0.0.1:" + port + "/greet?name=Ada");

        server.stop(0);
        System.out.println("Server stopped.");
    }

    static void respond(HttpExchange ex, int status, String type, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", type);
        ex.sendResponseHeaders(status, bytes.length);
        ex.getResponseBody().write(bytes);
        ex.close();
    }

    static void call(HttpClient client, String url) throws Exception {
        HttpResponse<String> resp = client.send(
                HttpRequest.newBuilder(URI.create(url)).GET().build(),
                HttpResponse.BodyHandlers.ofString());
        System.out.printf("GET %-30s -> %d  %s%n", url, resp.statusCode(), resp.body());
    }
}
