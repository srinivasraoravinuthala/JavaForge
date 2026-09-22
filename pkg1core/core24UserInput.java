package pkg1core;

/*
 * core24UserInput.java
 * ----------------
 * Reading user input with Scanner and BufferedReader.
 *
 * EXPLANATION:
 *  - Scanner: easy token-based parsing (nextInt, nextLine). Good for learning.
 *  - BufferedReader + InputStreamReader: faster for large text; readLine() per line.
 *  - Pitfall: mixing nextInt() then nextLine() without consuming the leftover newline.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class core24UserInput {

    static void demoScanner() {
        // Simulated input via String — same API as System.in
        Scanner sc = new Scanner("Alice\n25\n3.14\n");
        System.out.println("Scanner demo:");
        System.out.println("  name: " + sc.nextLine());
        System.out.println("  age:  " + sc.nextInt());
        sc.nextLine(); // consume newline after nextInt before next nextLine
        sc.close();
    }

    static void demoBufferedReader() throws Exception {
        String simulated = "line one\nline two\n";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new java.io.ByteArrayInputStream(simulated.getBytes())));
        System.out.println("BufferedReader demo:");
        System.out.println("  " + br.readLine());
        System.out.println("  " + br.readLine());
        br.close();
    }

    public static void main(String[] args) throws Exception {
        demoScanner();
        demoBufferedReader();

        // Real interactive use (uncomment to try):
        // Scanner in = new Scanner(System.in);
        // System.out.print("Enter your name: ");
        // String name = in.nextLine();
        // System.out.println("Hello, " + name);
    }
}
