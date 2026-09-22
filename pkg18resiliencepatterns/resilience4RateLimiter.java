package pkg18resiliencepatterns;

import java.util.concurrent.TimeUnit;

/*
 * resilience4RateLimiter.java
 * ---------------------------
 * Token-bucket rate limiter: cap requests per time window.
 *
 * DEFINITION:
 *   Allows bursts up to bucket capacity, refills tokens at a steady rate.
 *   Protects your service and downstream dependencies from overload.
 *
 * KEY POINTS:
 *   - acquire() blocks or fails when no tokens available.
 *   - Different from semaphore: tokens refill over time automatically.
 *   - Production: Guava RateLimiter, Resilience4j RateLimiter, API gateways.
 */
public class resilience4RateLimiter {

    static class TokenBucket {
        private final double refillPerMs;
        private final int capacity;
        private double tokens;
        private long lastRefill;

        TokenBucket(int capacity, double permitsPerSecond) {
            this.capacity = capacity;
            this.tokens = capacity;
            this.refillPerMs = permitsPerSecond / 1000.0;
            this.lastRefill = System.currentTimeMillis();
        }

        synchronized boolean tryAcquire() {
            refill();
            if (tokens >= 1) {
                tokens -= 1;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            double added = (now - lastRefill) * refillPerMs;
            if (added > 0) {
                tokens = Math.min(capacity, tokens + added);
                lastRefill = now;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket limiter = new TokenBucket(3, 2.0); // burst 3, refill 2/sec

        for (int i = 0; i < 8; i++) {
            boolean ok = limiter.tryAcquire();
            System.out.println("request " + i + ": " + (ok ? "allowed" : "throttled"));
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
