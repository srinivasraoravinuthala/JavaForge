package pkg13libs;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/*
 * libs4RandomAndUuid.java
 * -----------------------
 * Randomness done right: Random, ThreadLocalRandom, SecureRandom, and UUIDs.
 *
 * DEFINITION:
 *   Random is a fast pseudo-random generator (predictable from its seed).
 *   SecureRandom is cryptographically strong (for tokens/keys). UUID is a
 *   128-bit unique identifier.
 *
 * KEY POINTS:
 *   - Seed a Random to get reproducible sequences (great for tests).
 *   - Use ThreadLocalRandom in concurrent code (no contention).
 *   - Use SecureRandom for anything security-sensitive — never plain Random.
 *   - UUID.randomUUID() (v4) is the go-to for distributed unique ids.
 */
public class libs4RandomAndUuid {

    public static void main(String[] args) {
        // Seeded Random is reproducible
        Random seeded = new Random(42);
        System.out.print("Seeded(42) ints : ");
        for (int i = 0; i < 5; i++) System.out.print(seeded.nextInt(100) + " ");
        System.out.println("(same every run)");

        // Ranges and other types
        Random r = new Random();
        System.out.println("\nrandom double   : " + r.nextDouble());
        System.out.println("random boolean  : " + r.nextBoolean());
        System.out.println("dice (1..6)     : " + (r.nextInt(6) + 1));

        // Streams of random numbers (Java 8+)
        String nums = r.ints(5, 0, 10).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.println("5 ints [0,10)   : " + nums);

        // ThreadLocalRandom — preferred in multithreaded code
        System.out.println("\nThreadLocalRandom: " + ThreadLocalRandom.current().nextInt(1000));

        // SecureRandom — for tokens, salts, keys
        SecureRandom sr = new SecureRandom();
        byte[] token = new byte[8];
        sr.nextBytes(token);
        System.out.println("Secure token    : " + java.util.HexFormat.of().formatHex(token));

        // UUIDs
        UUID id = UUID.randomUUID();
        System.out.println("\nUUID v4         : " + id);
        System.out.println("version         : " + id.version() + ", variant: " + id.variant());
    }
}
