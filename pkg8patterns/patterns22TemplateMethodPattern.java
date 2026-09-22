package pkg8patterns;

/*
 * Template Method (Behavioral)
 * ----------------------------
 * INTENT: define the skeleton of an algorithm in a base method, deferring some
 *         steps to subclasses without changing the algorithm's structure.
 * UML: AbstractClass + templateMethod() (final) + step1()/step2() (abstract).
 * PROS: code reuse; enforce invariant algorithm structure ("Hollywood principle").
 * CONS: inheritance-based (less flexible than composition/strategy).
 * REAL-WORLD: java.util.AbstractList, InputStream.read, HttpServlet.service.
 */
public class patterns22TemplateMethodPattern {

    static abstract class DataProcessor {
        // The template method: fixed steps, variable implementations
        final void process() {
            read();
            transform();
            write();
        }
        abstract void read();
        abstract void transform();
        void write() { System.out.println("  writing result (default)"); }   // hook with default
    }

    static class CsvProcessor extends DataProcessor {
        void read() { System.out.println("CSV: read rows"); }
        void transform() { System.out.println("CSV: split by comma"); }
    }
    static class JsonProcessor extends DataProcessor {
        void read() { System.out.println("JSON: read document"); }
        void transform() { System.out.println("JSON: parse tree"); }
        @Override void write() { System.out.println("  JSON: pretty-print result"); }   // override hook
    }

    public static void main(String[] args) {
        System.out.println("-- CSV --"); new CsvProcessor().process();
        System.out.println("-- JSON --"); new JsonProcessor().process();
    }
}
