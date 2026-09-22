package pkg15modules;

import java.util.List;
import java.util.ServiceLoader;

/*
 * modules2ServiceLoaderSpi.java
 * -----------------------------
 * ServiceLoader (SPI): discover implementations of an interface at runtime.
 *
 * DEFINITION:
 *   SPI (Service Provider Interface) lets a library find implementations without
 *   hard-coding classes. Register providers in META-INF/services/<interface-name>
 *   or via module provides/uses in module-info.java.
 *
 * KEY POINTS:
 *   - ServiceLoader.load(Encoder.class) returns all registered implementations.
 *   - JPMS: module provides com.example.Encoder with com.example.UpperEncoder.
 *   - Classpath SPI: file META-INF/services/com.example.Encoder lists impl classes.
 *   - This demo uses in-process providers (no META-INF) to stay single-file runnable.
 */
public class modules2ServiceLoaderSpi {

  interface Encoder {
    String encode(String input);
    String name();
  }

  static class UpperEncoder implements Encoder {
    public String encode(String s) { return s.toUpperCase(); }
    public String name() { return "upper"; }
  }

  static class ReverseEncoder implements Encoder {
    public String encode(String s) { return new StringBuilder(s).reverse().toString(); }
    public String name() { return "reverse"; }
  }

  /** Manual SPI registry for single-file demo (production uses META-INF or JPMS). */
  static List<Encoder> loadEncoders() {
    return List.of(new UpperEncoder(), new ReverseEncoder());
  }

  public static void main(String[] args) {
    String text = "hello spi";
    System.out.println("Input: " + text);

  // In-process demo
    for (Encoder enc : loadEncoders())
      System.out.printf("  [%s] -> %s%n", enc.name(), enc.encode(text));

    // Real ServiceLoader (works when META-INF/services is present on classpath)
    System.out.println("\nServiceLoader discovery (may be empty in single-file mode):");
    ServiceLoader<Encoder> sl = ServiceLoader.load(Encoder.class);
    int count = 0;
    for (Encoder enc : sl) {
      System.out.printf("  discovered [%s] -> %s%n", enc.name(), enc.encode(text));
      count++;
    }
    if (count == 0)
      System.out.println("  (none — add META-INF/services/pkg15modules.modules2ServiceLoaderSpi$Encoder)");

    System.out.println("\nFor a full SPI + JPMS demo run: cd pkg15modules/spi-demo && mvn -q exec:java");
  }
}
