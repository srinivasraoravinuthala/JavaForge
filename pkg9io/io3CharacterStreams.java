package pkg9io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * io3CharacterStreams.java
 * ------------------------
 * Character streams: Reader / Writer for text, with encoding handled for you.
 *
 * DEFINITION:
 *   Character streams convert between bytes and 16-bit chars using a charset.
 *   Use them whenever you read or write text instead of raw bytes.
 *
 * KEY POINTS:
 *   - BufferedReader.readLine() reads a line at a time; returns null at EOF.
 *   - BufferedWriter / PrintWriter buffer output; PrintWriter adds println/printf.
 *   - FileReader/FileWriter use the platform charset (prefer Files.* with UTF-8).
 *   - try-with-resources closes and flushes automatically.
 */
public class io3CharacterStreams {

    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("io3", ".txt");

        // WRITE text line by line
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file.toFile())))) {
            out.println("line 1: hello");
            out.printf("line %d: %s%n", 2, "formatted");
            out.println("line 3: end");
        }

        // READ text line by line
        System.out.println("File contents:");
        try (BufferedReader in = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            int n = 1;
            while ((line = in.readLine()) != null) {     // null == end of file
                System.out.println("  " + (n++) + " | " + line);
            }
        }

        // Reader over an in-memory String, plus the Stream-of-lines API (Java 8+)
        String csv = "a,1\nb,2\nc,3";
        System.out.println("\nParsed from StringReader:");
        try (BufferedReader r = new BufferedReader(new StringReader(csv))) {
            r.lines()
             .map(l -> l.split(","))
             .forEach(parts -> System.out.println("  key=" + parts[0] + " val=" + parts[1]));
        }

        Files.deleteIfExists(file);
    }
}
