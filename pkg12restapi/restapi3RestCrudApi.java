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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * restapi3RestCrudApi.java
 * ------------------------
 * A complete in-memory REST CRUD API: routing by method + path, JSON in/out.
 *
 * DEFINITION:
 *   This is restapi2 taken to a real resource: /users supports
 *   GET (list), POST (create), GET /users/{id} (read), DELETE /users/{id}.
 *   Data lives in a thread-safe map. A client then exercises every route.
 *
 * KEY POINTS:
 *   - Dispatch on getRequestMethod() + path segments.
 *   - Return correct status codes (200/201/404) and JSON bodies.
 *   - Use a ConcurrentHashMap because the server is multi-threaded.
 *   - Everything runs in-process over loopback — no external services.
 */
public class restapi3RestCrudApi {

    static final Map<Integer, String> USERS = new ConcurrentHashMap<>();
    static final AtomicInteger SEQ = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/users", restapi3RestCrudApi::handleUsers);
        server.start();
        String base = "http://127.0.0.1:" + server.getAddress().getPort();

        HttpClient client = HttpClient.newHttpClient();

        // CREATE two users (POST)
        send(client, "POST", base + "/users", "{\"name\":\"Ada\"}");
        send(client, "POST", base + "/users", "{\"name\":\"Linus\"}");
        // LIST (GET collection)
        send(client, "GET", base + "/users", null);
        // READ one (GET item)
        send(client, "GET", base + "/users/1", null);
        // READ missing -> 404
        send(client, "GET", base + "/users/999", null);
        // DELETE one
        send(client, "DELETE", base + "/users/1", null);
        // LIST again
        send(client, "GET", base + "/users", null);

        server.stop(0);
    }

    static void handleUsers(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path = ex.getRequestURI().getPath();                 // /users or /users/{id}
        String[] parts = path.split("/");                           // ["", "users", "{id}"?]
        Integer id = parts.length == 3 ? tryParse(parts[2]) : null;

        if (method.equals("POST") && id == null) {                  // create
            String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String name = extractName(body);
            int newId = SEQ.incrementAndGet();
            USERS.put(newId, name);
            respond(ex, 201, "{\"id\":" + newId + ",\"name\":\"" + name + "\"}");
        } else if (method.equals("GET") && id == null) {            // list
            respond(ex, 200, toJsonArray());
        } else if (method.equals("GET")) {                          // read one
            String name = USERS.get(id);
            if (name == null) respond(ex, 404, "{\"error\":\"not found\"}");
            else respond(ex, 200, "{\"id\":" + id + ",\"name\":\"" + name + "\"}");
        } else if (method.equals("DELETE") && id != null) {         // delete
            respond(ex, USERS.remove(id) != null ? 204 : 404, "");
        } else {
            respond(ex, 405, "{\"error\":\"method not allowed\"}");
        }
    }

    static String toJsonArray() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (var e : USERS.entrySet()) {
            if (!first) sb.append(',');
            sb.append("{\"id\":").append(e.getKey()).append(",\"name\":\"").append(e.getValue()).append("\"}");
            first = false;
        }
        return sb.append(']').toString();
    }

    static String extractName(String json) {
        int k = json.indexOf("\"name\"");
        if (k < 0) return "unknown";
        int q1 = json.indexOf('"', json.indexOf(':', k) + 1);
        int q2 = json.indexOf('"', q1 + 1);
        return (q1 < 0 || q2 < 0) ? "unknown" : json.substring(q1 + 1, q2);
    }

    static Integer tryParse(String s) { try { return Integer.valueOf(s); } catch (Exception e) { return null; } }

    static void respond(HttpExchange ex, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(status, bytes.length == 0 ? -1 : bytes.length);
        if (bytes.length > 0) ex.getResponseBody().write(bytes);
        ex.close();
    }

    static void send(HttpClient client, String method, String url, String body) throws Exception {
        HttpRequest.Builder b = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json");
        b = switch (method) {
            case "POST"   -> b.POST(HttpRequest.BodyPublishers.ofString(body));
            case "DELETE" -> b.DELETE();
            default       -> b.GET();
        };
        HttpResponse<String> r = client.send(b.build(), HttpResponse.BodyHandlers.ofString());
        System.out.printf("%-6s %-28s -> %d %s%n", method, url, r.statusCode(), r.body());
    }
}
