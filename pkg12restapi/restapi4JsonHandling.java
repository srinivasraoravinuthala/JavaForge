package pkg12restapi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * restapi4JsonHandling.java
 * -------------------------
 * Working with JSON. Real apps use Jackson or Gson; here we show the shape of
 * JSON and a tiny hand-rolled writer/reader so the demo has NO dependencies.
 *
 * DEFINITION:
 *   JSON is the lingua franca of REST: objects {key:value}, arrays [..], strings,
 *   numbers, booleans, null. In production, map JSON <-> POJOs with a library
 *   (Jackson's ObjectMapper). This file demonstrates both worlds.
 *
 * KEY POINTS:
 *   - Production: ObjectMapper.writeValueAsString(obj) / readValue(json, Pojo.class).
 *   - Always escape special chars (" \\ newlines) when writing JSON by hand.
 *   - Prefer a library for anything beyond trivial payloads — parsing is fiddly.
 *   - Records map cleanly to/from JSON objects.
 */
public class restapi4JsonHandling {

    record User(int id, String name, boolean active) {}

    public static void main(String[] args) {
        // --- Build JSON from a record (toy serializer) ---
        User user = new User(1, "Ada \"the\" Lovelace", true);
        System.out.println("Serialized object:");
        System.out.println("  " + toJson(user));

        // --- Build a JSON array from a list ---
        List<User> users = List.of(new User(1, "Ada", true), new User(2, "Linus", false));
        StringBuilder arr = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            if (i > 0) arr.append(',');
            arr.append(toJson(users.get(i)));
        }
        arr.append(']');
        System.out.println("\nSerialized array:");
        System.out.println("  " + arr);

        // --- Parse a flat JSON object into a Map (toy parser) ---
        String json = "{\"id\": 42, \"name\": \"Grace\", \"active\": false}";
        Map<String, String> parsed = parseFlat(json);
        System.out.println("\nParsed object:");
        parsed.forEach((k, v) -> System.out.println("  " + k + " = " + v));

        System.out.println("\nIn production, replace these helpers with Jackson:");
        System.out.println("  ObjectMapper m = new ObjectMapper();");
        System.out.println("  String s = m.writeValueAsString(user);");
        System.out.println("  User u   = m.readValue(s, User.class);");
    }

    /** Minimal JSON object writer for a User record (escapes quotes/backslashes). */
    static String toJson(User u) {
        return "{\"id\":" + u.id()
             + ",\"name\":\"" + escape(u.name()) + "\""
             + ",\"active\":" + u.active() + "}";
    }

    static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\t", "\\t");
    }

    /** Toy parser for a flat {"k": v, ...} object — NOT for nested/real JSON. */
    static Map<String, String> parseFlat(String json) {
        Map<String, String> out = new LinkedHashMap<>();
        String body = json.trim().replaceAll("^\\{|}$", "");
        for (String pair : body.split(",")) {
            String[] kv = pair.split(":", 2);
            if (kv.length == 2) out.put(clean(kv[0]), clean(kv[1]));
        }
        return out;
    }

    static String clean(String s) { return s.trim().replaceAll("^\"|\"$", ""); }
}
