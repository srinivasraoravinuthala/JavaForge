package pkg8patterns;

/*
 * Adapter (Structural)
 * --------------------
 * INTENT: convert the interface of a class into another interface clients expect.
 *         Lets incompatible classes work together.
 * UML: Target <- Adapter -> Adaptee (adapter implements Target, wraps Adaptee).
 * PROS: reuse existing/legacy code; separation of concerns.
 * CONS: extra indirection.
 * REAL-WORLD: Arrays.asList, InputStreamReader (bytes->chars), java.io adapters.
 */
public class patterns6AdapterPattern {

    // Target interface the client wants
    interface JsonLogger { void logJson(String json); }

    // Adaptee: an existing/legacy logger with a different API
    static class LegacyLogger {
        void writeLine(String text) { System.out.println("LEGACY> " + text); }
    }

    // Adapter makes LegacyLogger usable as a JsonLogger
    static class LoggerAdapter implements JsonLogger {
        private final LegacyLogger legacy;
        LoggerAdapter(LegacyLogger legacy) { this.legacy = legacy; }
        public void logJson(String json) { legacy.writeLine("json=" + json); }
    }

    public static void main(String[] args) {
        JsonLogger logger = new LoggerAdapter(new LegacyLogger());
        logger.logJson("{\"event\":\"login\"}");
    }
}
