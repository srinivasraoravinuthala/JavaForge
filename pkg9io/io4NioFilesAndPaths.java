package pkg9io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

/*
 * io4NioFilesAndPaths.java
 * ------------------------
 * Modern file I/O: java.nio.file.Path + Files (Java 7+, the recommended API).
 *
 * DEFINITION:
 *   NIO.2 replaces java.io.File with Path (a typed path) and Files (a toolbox
 *   of static helpers). It is shorter, safer, charset-aware, and more powerful.
 *
 * KEY POINTS:
 *   - Files.writeString / readString (Java 11+) do whole-file text in one line.
 *   - Files.write/readAllLines work with List<String>; default charset is UTF-8.
 *   - StandardOpenOption controls CREATE / APPEND / TRUNCATE behavior.
 *   - Path offers resolve(), getFileName(), getParent() for safe composition.
 */
public class io4NioFilesAndPaths {

    public static void main(String[] args) throws IOException {
        Path dir  = Files.createTempDirectory("io4");
        Path file = dir.resolve("data.txt");          // dir + "data.txt", portably

        System.out.println("file name : " + file.getFileName());
        System.out.println("parent    : " + file.getParent());

        // Whole-file write/read as a String (Java 11+)
        Files.writeString(file, "first line\n", StandardCharsets.UTF_8);
        Files.writeString(file, "appended line\n", StandardOpenOption.APPEND);
        System.out.println("\nreadString():\n" + Files.readString(file).stripTrailing());

        // Work with lists of lines
        Files.write(file, List.of("alpha", "beta", "gamma"));   // overwrites
        List<String> lines = Files.readAllLines(file);
        System.out.println("\nreadAllLines(): " + lines);

        // Stream lines lazily (good for huge files — does not load everything)
        System.out.println("\nUppercased via stream:");
        try (Stream<String> s = Files.lines(file)) {
            s.map(String::toUpperCase).forEach(l -> System.out.println("  " + l));
        }

        // Metadata
        System.out.println("\nsize=" + Files.size(file) + " bytes, exists=" + Files.exists(file));

        // Recursive cleanup (delete files, then the directory)
        try (Stream<Path> walk = Files.walk(dir)) {
            walk.sorted((a, b) -> b.getNameCount() - a.getNameCount())   // children first
                .forEach(p -> { try { Files.deleteIfExists(p); } catch (IOException ignored) {} });
        }
        System.out.println("Cleaned up: exists=" + Files.exists(dir));

        // Paths.get is the older factory (equivalent to Path.of)
        Path p = Paths.get("a", "b", "c.txt");
        System.out.println("\nPaths.get -> " + p);
    }
}
