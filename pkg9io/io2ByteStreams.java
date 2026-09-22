package pkg9io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * io2ByteStreams.java
 * -------------------
 * Byte streams: reading/writing raw bytes with InputStream / OutputStream.
 *
 * DEFINITION:
 *   Byte streams move 8-bit bytes and are the foundation of all I/O. Use them
 *   for binary data (images, audio, files of any kind). For text prefer the
 *   character streams in io3.
 *
 * KEY POINTS:
 *   - FileInputStream / FileOutputStream talk to files; ByteArray*Stream to memory.
 *   - Wrap in Buffered*Stream to batch syscalls and dramatically speed up I/O.
 *   - Always use try-with-resources so streams are closed (and flushed).
 *   - transferTo() (Java 9+) copies a whole stream in one call.
 */
public class io2ByteStreams {

    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("io2", ".bin");
        byte[] payload = "Bytes: \u2600\u2764 0123".getBytes();

        // WRITE bytes (buffered for speed)
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file.toFile()))) {
            out.write(payload);
        } // try-with-resources auto-closes (and flushes) here

        // READ bytes back
        try (InputStream in = new BufferedInputStream(new FileInputStream(file.toFile()))) {
            byte[] read = in.readAllBytes();      // Java 9+ convenience
            System.out.println("Read " + read.length + " bytes: " + new String(read));
        }

        // Copy one stream into another with transferTo (no manual loop)
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        try (InputStream src = new ByteArrayInputStream(payload)) {
            long copied = src.transferTo(sink);
            System.out.println("transferTo copied " + copied + " bytes into memory");
        }

        // Manual read loop (how buffering works under the hood)
        int total = 0;
        try (InputStream in = new FileInputStream(file.toFile())) {
            byte[] buf = new byte[4];
            int n;
            while ((n = in.read(buf)) != -1) total += n;   // -1 signals EOF
        }
        System.out.println("Manual loop counted " + total + " bytes");

        Files.deleteIfExists(file);
    }
}
