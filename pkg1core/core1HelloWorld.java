package pkg1core;

/*
 * core1HelloWorld.java
 * ----------------
 * The classic first program. Demonstrates the minimal structure of a Java app.
 *
 * EXPLANATION:
 *  - Every Java application starts in `public static void main(String[] args)`.
 *  - `public`  : the JVM must be able to call it from outside the class.
 *  - `static`  : no object needs to exist to call it.
 *  - `void`    : main returns nothing.
 *  - `String[] args` : command-line arguments.
 *
 * RUN:  java core1HelloWorld.java   (single-file launch)
 *   or: javac core1HelloWorld.java && java core1HelloWorld
 */
public class core1HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, JavaMastery!");

        // Command-line args demo: try `java core1HelloWorld.java Alice`
        if (args.length > 0) {
            System.out.println("Hello, " + args[0] + "!");
        } else {
            System.out.println("(Tip: pass your name as an argument.)");
        }
    }
}
