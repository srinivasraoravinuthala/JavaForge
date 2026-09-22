package pkg18resiliencepatterns;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/*
 * resilience1CircuitBreaker.java
 * --------------------------------
 * Circuit breaker: stop calling a failing dependency until it recovers.
 *
 * DEFINITION:
 *   States: CLOSED (normal) -> OPEN (fail fast) -> HALF_OPEN (probe) -> CLOSED/OPEN.
 *   Prevents cascading failures and wasted resources on a dead service.
 *
 * KEY POINTS:
 *   - Trip to OPEN after failure threshold in a window.
 *   - After cooldown, allow one probe (HALF_OPEN); success closes, failure reopens.
 *   - Production: Resilience4j, Spring Cloud Circuit Breaker.
 */
public class resilience1CircuitBreaker {

    enum State { CLOSED, OPEN, HALF_OPEN }

    static class CircuitBreaker {
        private final int failureThreshold;
        private final long cooldownMs;
        private State state = State.CLOSED;
        private int failures = 0;
        private long openedAt = 0;

        CircuitBreaker(int failureThreshold, long cooldownMs) {
            this.failureThreshold = failureThreshold;
            this.cooldownMs = cooldownMs;
        }

        <T> T execute(Supplier<T> call) {
            if (state == State.OPEN) {
                if (System.currentTimeMillis() - openedAt >= cooldownMs) {
                    state = State.HALF_OPEN;
                    System.out.println("  breaker -> HALF_OPEN (probe)");
                } else {
                    throw new RuntimeException("Circuit OPEN — fail fast");
                }
            }
            try {
                T result = call.get();
                onSuccess();
                return result;
            } catch (RuntimeException e) {
                onFailure();
                throw e;
            }
        }

        private void onSuccess() {
            failures = 0;
            if (state != State.CLOSED) {
                state = State.CLOSED;
                System.out.println("  breaker -> CLOSED");
            }
        }

        private void onFailure() {
            failures++;
            if (state == State.HALF_OPEN || failures >= failureThreshold) {
                state = State.OPEN;
                openedAt = System.currentTimeMillis();
                System.out.println("  breaker -> OPEN (failures=" + failures + ")");
                failures = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger attempts = new AtomicInteger();
        CircuitBreaker breaker = new CircuitBreaker(2, 500);
        Supplier<String> flaky = () -> {
            attempts.incrementAndGet();
            throw new RuntimeException("service down");
        };

        for (int i = 1; i <= 5; i++) {
            try {
                breaker.execute(flaky);
            } catch (RuntimeException e) {
                System.out.println("call " + i + ": " + e.getMessage());
            }
            Thread.sleep(150);
        }
        System.out.println("Total attempts to flaky service: " + attempts.get() + " (breaker limited calls)");
    }
}
