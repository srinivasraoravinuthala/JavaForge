package pkg9io;

import java.io.File;
import java.io.IOException;

/*
 * io1FileBasics.java
 * ------------------
 * The legacy java.io.File API: representing paths, files, and directories.
 *
 * DEFINITION:
 *   A File object is an abstract handle to a path on disk. Creating a File
 *   does NOT touch the disk — you must call methods (createNewFile, mkdirs,
 *   delete) to actually act on the filesystem.
 *
 * KEY POINTS:
 *   - File works for both files and directories (it is just a path).
 *   - Use File.separator for portable paths; prefer java.nio (io4) for new code.
 *   - exists(), isFile(), isDirectory(), length(), canRead() inspect a path.
 *   - Clean up temp resources so the demo is repeatable.
 */
public class io1FileBasics {

    public static void main(String[] args) throws IOException {
        File dir = new File("io_demo_dir");
        File file = new File(dir, "notes.txt");   // nested path: io_demo_dir/notes.txt

        System.out.println("Path string      : " + file.getPath());
        System.out.println("File.separator   : '" + File.separator + "'");

        // Create directory + file on disk
        boolean dirMade  = dir.mkdirs();          // creates parent dirs too
        boolean fileMade = file.createNewFile();  // creates empty file
        System.out.println("\nDirectory created: " + dirMade);
        System.out.println("File created     : " + fileMade);

        // Inspect the path now that it exists
        System.out.println("\nexists()         : " + file.exists());
        System.out.println("isFile()         : " + file.isFile());
        System.out.println("isDirectory()    : " + file.isDirectory());
        System.out.println("length() bytes   : " + file.length());
        System.out.println("absolute path    : " + file.getAbsolutePath());

        // List directory contents
        System.out.println("\nContents of " + dir.getName() + ":");
        File[] children = dir.listFiles();
        if (children != null) for (File c : children) System.out.println("  - " + c.getName());

        // Cleanup so the demo can be re-run
        file.delete();
        dir.delete();
        System.out.println("\nCleaned up. exists()? " + file.exists());
    }
}
