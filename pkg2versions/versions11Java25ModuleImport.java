package pkg2versions;

/*
 * versions11Java25ModuleImport.java
 * Module import declarations (JEP 511), final in Java 25.
 * Preview in Java 23 (JEP 476) and Java 24 (JEP 494).
 * import module java.base brings in the exported packages of java.base.
 */
import module java.base;

public class versions11Java25ModuleImport {
    public static void main(String[] args) {
        var names = List.of("ada", "bea");
        System.out.println(names.getFirst());
    }
}
