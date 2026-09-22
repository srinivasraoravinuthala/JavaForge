package pkg18resiliencepatterns;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/*
 * resilience2RetryWithBackoff.java
 * --------------------------------
 * Retry with exponential backoff + jitter: recover from transient failures.
 *
 * DEFINITION:
 *   Retry re-executes a failed operation. Backoff increases delay between tries;
 *   jitter randomizes delay to prevent synchronized retries (thundering herd).
 *
 * KEY POINTS:
 *   - Only retry transient errors (timeouts, 503), not business failures.
 *   - Cap max attempts and max delay.
 *   - Idempotent operations are safe to retry.
 */
public class resilience2RetryWithBackoff {

    static <T> T retry(Supplier<T> action, int maxAttempts, Duration initialDelay, Duration maxDelay) {
        Duration delay = initialDelay;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return action.get();
            } catch (RuntimeException e) {
                if (attempt == maxAttempts) throw e;
                long jitter = ThreadLocalRandom.current().nextLong(delay.toMillis() / 2, delay.toMillis());
                System.out.printf("  attempt %d failed (%s), retry in %d ms%n", attempt, e.getMessage(), jitter);
                try { Thread.sleep(jitter); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
                delay = Duration.ofMillis(Math.min(delay.toMillis() * 2, maxDelay.toMillis()));
            }
        }
        throw new IllegalStateException("unreachable");
    }

    public static void main(String[] args) {
        java.util.concurrent.atomic.AtomicInteger calls = new java.util.concurrent.atomic.AtomicInteger();

        String result = retry(() -> {
            int n = calls.incrementAndGet();
            if (n <= 2) throw new RuntimeException("timeout");
            return "ok on attempt " + n;
        }, 5, Duration.ofMillis(50), Duration.ofMillis(400));

        System.out.println("Result: " + result);
    }
}
