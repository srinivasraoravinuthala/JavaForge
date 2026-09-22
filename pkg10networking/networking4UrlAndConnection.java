package pkg10networking;

import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/*
 * networking4UrlAndConnection.java
 * --------------------------------
 * The classic URL / URLConnection API for fetching resources over HTTP.
 *
 * DEFINITION:
 *   A URL identifies a resource (scheme://host:port/path?query). URLConnection
 *   opens a stream to it. This is the older API; prefer HttpClient (networking5)
 *   for new code. We fetch from a tiny in-process server so it runs offline.
 *
 * KEY POINTS:
 *   - Parse a URL to inspect protocol, host, port, path, query.
 *   - openConnection() + getInputStream() reads the response body.
 *   - Build URLs via URI.create(...).toURL() (the URL(String) ctor is deprecated).
 *   - Set headers/timeouts on the URLConnection before connecting.
 */
public class networking4UrlAndConnection {

    public static void main(String[] args) throws Exception {
        // Start a local server that returns a fixed body
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/hello", ex -> {
            byte[] body = "Hello from URLConnection demo".getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "text/plain");
            ex.sendResponseHeaders(200, body.length);
            ex.getResponseBody().write(body);
            ex.close();
        });
        server.start();
        int port = server.getAddress().getPort();

        // Parse a URL and inspect its parts
        URL url = URI.create("http://127.0.0.1:" + port + "/hello?lang=en").toURL();
        System.out.println("protocol = " + url.getProtocol());
        System.out.println("host     = " + url.getHost());
        System.out.println("port     = " + url.getPort());
        System.out.println("path     = " + url.getPath());
        System.out.println("query    = " + url.getQuery());

        // Open a connection, set a header/timeout, and read the body
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept", "text/plain");
        conn.setConnectTimeout(2000);
        System.out.println("\nContent-Type: " + conn.getContentType());
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            System.out.println("Body        : " + in.readLine());
        }

        server.stop(0);
    }
}
