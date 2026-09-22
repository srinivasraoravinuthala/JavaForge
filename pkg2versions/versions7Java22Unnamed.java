package pkg2versions;

/*
 * versions7Java22Unnamed.java
 * Unnamed variables and unnamed patterns (JEP 456), final in Java 22.
 * This was a preview in Java 21 (JEP 443). The file uses the final form.
 */
import java.util.List;

public class versions7Java22Unnamed {
    public static void main(String[] args) {
        int seen = 0;
        for (int _ : List.of(1, 2, 3)) {
            seen++;
        }
        System.out.println("seen=" + seen);

        try {
            Integer.parseInt("nope");
        } catch (NumberFormatException _) {
            System.out.println("not a number");
        }
    }
}
