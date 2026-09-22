package pkg10networking;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/*
 * networking1InetAddress.java
 * ---------------------------
 * Identifying hosts: IP addresses, hostnames, and local network interfaces.
 *
 * DEFINITION:
 *   InetAddress represents an IP address (v4 or v6). It maps between hostnames
 *   and addresses (DNS) and lets you inspect the machine's own interfaces.
 *
 * KEY POINTS:
 *   - getLocalHost() returns this machine; getByName() resolves any host (needs DNS).
 *   - getLoopbackAddress() (127.0.0.1 / ::1) always works offline.
 *   - isReachable() does an ICMP/echo-style ping (may need privileges).
 *   - NetworkInterface enumerates real NICs (Ethernet, Wi-Fi, loopback).
 */
public class networking1InetAddress {

    public static void main(String[] args) throws Exception {
        // Loopback is always available, no network required
        InetAddress loop = InetAddress.getLoopbackAddress();
        System.out.println("Loopback : " + loop.getHostName() + " -> " + loop.getHostAddress());

        // This machine
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Localhost: " + local.getHostName() + " -> " + local.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Localhost: (could not resolve) " + e.getMessage());
        }

        // DNS resolution of an external name (skipped gracefully if offline)
        try {
            InetAddress[] all = InetAddress.getAllByName("dns.google");
            System.out.println("\ndns.google resolves to:");
            for (InetAddress a : all) System.out.println("  " + a.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("\nDNS lookup skipped (offline): " + e.getMessage());
        }

        // Local network interfaces
        System.out.println("\nNetwork interfaces:");
        Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
        while (nics != null && nics.hasMoreElements()) {
            NetworkInterface nic = nics.nextElement();
            System.out.printf("  %-20s up=%-5s loopback=%s%n",
                    nic.getDisplayName(), nic.isUp(), nic.isLoopback());
        }
    }
}
