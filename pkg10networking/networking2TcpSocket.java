package pkg10networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * networking2TcpSocket.java
 * -------------------------
 * TCP sockets: a reliable, ordered, connection-based byte stream.
 *
 * DEFINITION:
 *   ServerSocket listens for incoming connections; Socket is one endpoint of a
 *   connection. This demo runs BOTH server and client in one process over
 *   loopback, so it works fully offline.
 *
 * KEY POINTS:
 *   - Server: bind a port, accept() (blocks) -> get a Socket per client.
 *   - Client: new Socket(host, port) connects; then read/write its streams.
 *   - Bind port 0 to let the OS pick a free ephemeral port.
 *   - TCP guarantees delivery & order; for fire-and-forget use UDP (networking3).
 */
public class networking2TcpSocket {

    public static void main(String[] args) throws Exception {
        // Start a tiny echo server on an OS-chosen port, in a background thread
        ServerSocket server = new ServerSocket(0);   // 0 => ephemeral port
        int port = server.getLocalPort();

        Thread serverThread = new Thread(() -> {
            try (Socket client = server.accept();    // blocks until a client connects
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                String line = in.readLine();
                System.out.println("[server] received: " + line);
                out.println("ECHO: " + line);        // send a response
            } catch (Exception e) {
                System.out.println("[server] error: " + e.getMessage());
            }
        });
        serverThread.start();

        // Client connects to the server over loopback and exchanges one message
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("[client] connected to port " + port);
            out.println("hello over TCP");
            System.out.println("[client] reply   : " + in.readLine());
        }

        serverThread.join();
        server.close();
        System.out.println("Done.");
    }
}
