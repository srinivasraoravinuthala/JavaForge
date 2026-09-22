package pkg13libs;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * libs8Logging.java
 * -----------------
 * Logging with java.util.logging (JUL) — the built-in, dependency-free logger.
 *
 * DEFINITION:
 *   A Logger records events at severity levels. Handlers decide where logs go
 *   (console, file); Formatters decide how they look; Levels filter what's kept.
 *   Real apps often use SLF4J + Logback/Log4j2, but the concepts are identical.
 *
 * KEY POINTS:
 *   - Levels: SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINEST.
 *   - setLevel() on the logger AND handler controls what is emitted.
 *   - Use lambda/supplier overloads to avoid building messages that get filtered.
 *   - Never log secrets; prefer parameterized messages over string concatenation.
 */
public class libs8Logging {

    private static final Logger log = Logger.getLogger(libs8Logging.class.getName());

    public static void main(String[] args) {
        // Configure a custom one-line formatter on a console handler
        log.setUseParentHandlers(false);              // don't double-print via root logger
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new Formatter() {
            @Override public String format(LogRecord r) {
                return String.format("[%-7s] %s%n", r.getLevel(), r.getMessage());
            }
        });
        log.addHandler(handler);
        log.setLevel(Level.ALL);

        // Log at every level
        log.severe("database connection lost");
        log.warning("disk usage at 85%");
        log.info("user 42 logged in");
        log.config("cache size = 256MB");
        log.fine("entering method process()");

        // Lazy message (only built if FINE is enabled) — avoids wasted work
        log.fine(() -> "expensive detail: " + expensiveComputation());

        // Log an exception with a stack trace
        try {
            throw new IllegalStateException("simulated failure");
        } catch (Exception e) {
            log.log(Level.SEVERE, "operation failed", e);
        }

        // Raise the threshold: now only WARNING+ are shown
        System.out.println("\n-- raising level to WARNING --");
        log.setLevel(Level.WARNING);
        log.info("this INFO is now suppressed");
        log.warning("this WARNING still shows");
    }

    static String expensiveComputation() { return "computed-" + (2 * 21); }
}
