# 31 → REST APIs

**Previous:** [30 JDBC](30-JDBC.md) → **Next:** [32 Standard Libraries](32-StandardLibraries.md)

▶️ `pkg12restapi/restapi1RestConcepts.java` · `restapi5HttpClientConsume.java`

---

## REST principles

| Concept | Rule |
|---------|------|
| **Resource** | Noun in URL: `/users/42` |
| **HTTP verb** | GET read, POST create, PUT replace, PATCH update, DELETE remove |
| **Status codes** | 200 OK, 201 Created, 400 Bad Request, 404 Not Found, 500 Server Error |
| **Stateless** | Each request carries all context (no server session) |

---

## Build + consume

This package shows:
1. REST design concepts
2. Minimal HTTP server (`HttpServer`)
3. JSON handling
4. Client consumption with `HttpClient`

**Milestone project:** File config · JDBC database · REST endpoint · call with HttpClient — scaffolded as [Project 03 Todo REST](../../projects/03-todo-rest/README.md).

**Related →** [RestApis.md](../04-reference/07-RestApis.md)

**Related →** [15 Spring Boot](../03-interview/15-SpringBoot.md)


**Next →** [32 Standard Libraries](32-StandardLibraries.md)
