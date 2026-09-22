package pkg13libs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/*
 * libs3CryptoAndHashing.java
 * --------------------------
 * Hashing, HMAC, and Base64 with the built-in java.security / javax.crypto APIs.
 *
 * DEFINITION:
 *   A cryptographic hash (SHA-256) maps data to a fixed-size digest (one-way).
 *   HMAC adds a secret key for authenticity. Base64 encodes bytes as ASCII text
 *   (encoding, NOT encryption).
 *
 * KEY POINTS:
 *   - MessageDigest computes hashes (SHA-256 preferred; avoid MD5/SHA-1).
 *   - For passwords use a slow KDF (PBKDF2/bcrypt/Argon2), NOT a plain hash.
 *   - HMAC proves a message came from someone who holds the key.
 *   - Base64.getEncoder()/getDecoder() is the standard text-safe byte transport.
 */
public class libs3CryptoAndHashing {

    public static void main(String[] args) throws Exception {
        String message = "the quick brown fox";

        // 1) SHA-256 hash
        byte[] digest = sha256(message);
        System.out.println("SHA-256 (hex)   : " + toHex(digest));
        System.out.println("SHA-256 (base64): " + Base64.getEncoder().encodeToString(digest));

        // Same input -> same hash; tiny change -> totally different hash
        System.out.println("\nDeterministic?  : " + (toHex(sha256(message)).equals(toHex(digest))));
        System.out.println("Avalanche       : " + toHex(sha256(message + "!")).substring(0, 16) + "...");

        // 2) Base64 round-trip
        String encoded = Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println("\nBase64 encoded  : " + encoded);
        System.out.println("Base64 decoded  : " + decoded);

        // 3) HMAC-SHA256 (keyed authentication)
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec("super-secret-key".getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] tag = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("\nHMAC-SHA256     : " + toHex(tag));

        // 4) Cryptographically strong random bytes (e.g. tokens, salts)
        byte[] salt = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(salt);
        System.out.println("Random salt     : " + Base64.getEncoder().encodeToString(salt));
    }

    static byte[] sha256(String s) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(s.getBytes(StandardCharsets.UTF_8));
    }

    static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
