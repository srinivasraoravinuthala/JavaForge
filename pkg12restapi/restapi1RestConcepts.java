package pkg12restapi;

/*
 * restapi1RestConcepts.java
 * -------------------------
 * REST fundamentals: the vocabulary of web APIs (no code to run, just the map).
 *
 * DEFINITION:
 *   REST (REpresentational State Transfer) is an architectural style for APIs
 *   over HTTP. Resources (nouns) are identified by URLs; HTTP methods (verbs)
 *   act on them; representations (JSON/XML) carry their state.
 *
 * THE 6 REST CONSTRAINTS (brief):
 *   1. Client–Server   — separate UI from data storage.
 *   2. Stateless       — each request carries all context (no server session).
 *   3. Cacheable       — responses say if/how they can be cached.
 *   4. Uniform Interface — consistent resource URLs + standard methods.
 *   5. Layered System  — proxies/gateways are transparent.
 *   6. Code on Demand  — (optional) server can ship executable code.
 */
public class restapi1RestConcepts {

    public static void main(String[] args) {
        System.out.println("HTTP methods (verbs) and their REST meaning:");
        print("GET",    "read a resource (safe, idempotent)");
        print("POST",   "create a new resource (not idempotent)");
        print("PUT",    "replace a resource (idempotent)");
        print("PATCH",  "partially update a resource");
        print("DELETE", "remove a resource (idempotent)");

        System.out.println("\nResource URL design (nouns, plural, hierarchical):");
        System.out.println("  GET    /users           -> list users");
        System.out.println("  GET    /users/42        -> one user");
        System.out.println("  POST   /users           -> create user");
        System.out.println("  PUT    /users/42        -> replace user 42");
        System.out.println("  DELETE /users/42        -> delete user 42");
        System.out.println("  GET    /users/42/orders -> sub-resource");

        System.out.println("\nKey status codes:");
        status(200, "OK");          status(201, "Created");
        status(204, "No Content");  status(400, "Bad Request");
        status(401, "Unauthorized");status(403, "Forbidden");
        status(404, "Not Found");   status(409, "Conflict");
        status(422, "Unprocessable Entity"); status(500, "Internal Server Error");

        System.out.println("\nCommon headers:");
        System.out.println("  Content-Type: application/json   (what the body IS)");
        System.out.println("  Accept: application/json         (what the client WANTS)");
        System.out.println("  Authorization: Bearer <token>    (who the client IS)");

        System.out.println("\nNext: restapi2 builds a server, restapi3 adds CRUD routing,");
        System.out.println("restapi4 handles JSON, restapi5 consumes it with HttpClient.");
    }

    static void print(String verb, String meaning) { System.out.printf("  %-7s %s%n", verb, meaning); }
    static void status(int code, String text)       { System.out.printf("  %d %s%n", code, text); }
}
