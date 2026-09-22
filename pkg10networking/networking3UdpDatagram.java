package pkg10networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * networking3UdpDatagram.java
 * ---------------------------
 * UDP datagrams: connectionless, unordered, best-effort messages.
 *
 * DEFINITION:
 *   UDP sends independent packets (datagrams) with no connection, no ordering,
 *   and no delivery guarantee — but with very low overhead. Good for DNS, video,
 *   games, metrics. This demo sends a packet to itself over loopback.
 *
 * KEY POINTS:
 *   - DatagramSocket sends/receives; DatagramPacket holds bytes + address + port.
 *   - receive() blocks until a packet arrives (or timeout via setSoTimeout).
 *   - No accept()/connect() handshake — just fire packets.
 *   - Packets can be lost, duplicated, or reordered: the app must cope.
 */
public class networking3UdpDatagram {

    public static void main(String[] args) throws Exception {
        InetAddress loop = InetAddress.getLoopbackAddress();

        // Receiver socket on an OS-chosen port
        DatagramSocket receiver = new DatagramSocket(0);
        int port = receiver.getLocalPort();

        Thread receiverThread = new Thread(() -> {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                receiver.receive(packet);             // blocks until a datagram arrives
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("[receiver] got: \"" + msg + "\" from port " + packet.getPort());
            } catch (Exception e) {
                System.out.println("[receiver] error: " + e.getMessage());
            }
        });
        receiverThread.start();

        // Sender fires one datagram at the receiver
        try (DatagramSocket sender = new DatagramSocket()) {
            byte[] data = "hello over UDP".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, loop, port);
            sender.send(packet);
            System.out.println("[sender] sent " + data.length + " bytes to port " + port);
        }

        receiverThread.join();
        receiver.close();
        System.out.println("Done.");
    }
}
