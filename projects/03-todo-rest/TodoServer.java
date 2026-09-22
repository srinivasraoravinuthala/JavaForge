import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** Minimal Todo REST API — Project 03 (after ch.31). */
public class TodoServer {
    record Todo(int id, String title) {}

    private static final List<Todo> TODOS = new ArrayList<>();
    private static final AtomicInteger SEQ = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/todos", TodoServer::handle);
        server.start();
        System.out.println("Todo API on http://localhost:8080/api/todos");
    }

    private static void handle(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path = ex.getRequestURI().getPath();
        try {
            if ("GET".equals(method) && path.equals("/api/todos")) {
                write(ex, 200, toJsonArray());
                return;
            }
            if ("POST".equals(method) && path.equals("/api/todos")) {
                String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String title = extractTitle(body);
                Todo t = new Todo(SEQ.getAndIncrement(), title);
                TODOS.add(t);
                write(ex, 201, "{\"id\":" + t.id() + ",\"title\":\"" + esc(t.title()) + "\"}");
                return;
            }
            if ("DELETE".equals(method) && path.startsWith("/api/todos/")) {
                int id = Integer.parseInt(path.substring("/api/todos/".length()));
                TODOS.removeIf(t -> t.id() == id);
                write(ex, 204, "");
                return;
            }
            write(ex, 404, "{\"error\":\"not found\"}");
        } catch (Exception e) {
            write(ex, 400, "{\"error\":\"" + esc(e.getMessage()) + "\"}");
        }
    }

    private static String toJsonArray() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < TODOS.size(); i++) {
            Todo t = TODOS.get(i);
            if (i > 0) sb.append(',');
            sb.append("{\"id\":").append(t.id()).append(",\"title\":\"").append(esc(t.title())).append("\"}");
        }
        return sb.append(']').toString();
    }

    private static String extractTitle(String json) {
        int i = json.indexOf("\"title\"");
        if (i < 0) return "untitled";
        int colon = json.indexOf(':', i);
        int q1 = json.indexOf('"', colon + 1);
        int q2 = json.indexOf('"', q1 + 1);
        return json.substring(q1 + 1, q2);
    }

    private static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static void write(HttpExchange ex, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        ex.sendResponseHeaders(code, bytes.length == 0 ? -1 : bytes.length);
        if (bytes.length > 0) {
            try (OutputStream os = ex.getResponseBody()) {
                os.write(bytes);
            }
        }
        ex.close();
    }
}
