package pkg8patterns;

/*
 * Composite (Structural)
 * ----------------------
 * INTENT: compose objects into tree structures and treat individual objects and
 *         compositions uniformly.
 * UML: Component <|-- Leaf and <|-- Composite (Composite holds Component children).
 * PROS: uniform treatment of leaves and groups; easy recursive operations.
 * CONS: can make the design overly general.
 * REAL-WORLD: file systems, UI component trees, org charts.
 */
import java.util.*;

public class patterns8CompositePattern {

    interface FileSystemNode { int size(); String name(); }

    // Leaf
    static class FileLeaf implements FileSystemNode {
        private final String name; private final int size;
        FileLeaf(String name, int size) { this.name = name; this.size = size; }
        public int size() { return size; }
        public String name() { return name; }
    }

    // Composite
    static class Directory implements FileSystemNode {
        private final String name;
        private final List<FileSystemNode> children = new ArrayList<>();
        Directory(String name) { this.name = name; }
        Directory add(FileSystemNode n) { children.add(n); return this; }
        public int size() { return children.stream().mapToInt(FileSystemNode::size).sum(); }
        public String name() { return name; }
    }

    public static void main(String[] args) {
        Directory root = new Directory("root")
                .add(new FileLeaf("a.txt", 100))
                .add(new Directory("sub")
                        .add(new FileLeaf("b.txt", 200))
                        .add(new FileLeaf("c.txt", 300)));
        System.out.println("total size of '" + root.name() + "' = " + root.size() + " bytes");
    }
}
