package pkg8patterns;

/*
 * Proxy (Structural)
 * ------------------
 * INTENT: provide a surrogate/placeholder for another object to control access
 *         (lazy loading, access control, caching, logging, remoting).
 * UML: Subject <|-- RealSubject and <|-- Proxy (proxy holds a RealSubject).
 * PROS: control/extend access without changing the real object.
 * CONS: extra indirection; possible latency.
 * REAL-WORLD: Spring AOP proxies, java.lang.reflect.Proxy, Hibernate lazy loading.
 */
public class patterns12ProxyPattern {

    interface Image { String display(); }

    // Expensive object
    static class RealImage implements Image {
        private final String file;
        RealImage(String file) { this.file = file; loadFromDisk(); }
        private void loadFromDisk() { System.out.println("  loading " + file + " from disk (expensive)"); }
        public String display() { return "showing " + file; }
    }

    // Virtual proxy: defers creation until first use (lazy loading)
    static class ImageProxy implements Image {
        private final String file;
        private RealImage real;     // created on demand
        ImageProxy(String file) { this.file = file; }
        public String display() {
            if (real == null) real = new RealImage(file);   // lazy init
            return real.display();
        }
    }

    public static void main(String[] args) {
        Image image = new ImageProxy("photo.png");
        System.out.println("proxy created (nothing loaded yet)");
        System.out.println(image.display());   // triggers load
        System.out.println(image.display());   // reuses, no reload
    }
}
