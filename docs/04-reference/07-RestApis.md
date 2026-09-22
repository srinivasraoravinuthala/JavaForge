# REST APIs (`pkg12restapi`)

Runnable demos: [`pkg12restapi`](../../pkg12restapi). Every demo starts a real HTTP
server **in-process** (JDK's `com.sun.net.httpserver`, no dependencies) and calls
it with `HttpClient` — so it all runs **offline**.

## What is REST?
**RE**presentational **S**tate **T**ransfer — an HTTP architectural style where
**resources** (nouns, addressed by URLs) are acted on with **HTTP methods** (verbs)
and exchanged as **representations** (usually JSON).

## Verbs → meaning
| Method | Meaning | Idempotent? |
|--------|---------|-------------|
| GET | read | yes |
| POST | create | no |
| PUT | replace | yes |
| PATCH | partial update | no |
| DELETE | remove | yes |

## Status codes (the essentials)
`200 OK` · `201 Created` · `204 No Content` · `400 Bad Request` ·
`401 Unauthorized` · `403 Forbidden` · `404 Not Found` · `409 Conflict` ·
`422 Unprocessable Entity` · `500 Internal Server Error`

## Files in this package
| # | File | Topic |
|---|------|-------|
| 1 | `restapi1RestConcepts` | verbs, URLs, status codes, headers |
| 2 | `restapi2HttpServerDemo` | minimal `HttpServer` + routes |
| 3 | `restapi3RestCrudApi` | full CRUD `/users` with routing + client |
| 4 | `restapi4JsonHandling` | JSON shape; toy serialize/parse + Jackson notes |
| 5 | `restapi5HttpClientConsume` | consuming APIs: auth, status, async |

## Resource URL design
```
GET    /users           list
POST   /users           create
GET    /users/42        read one
PUT    /users/42        replace
DELETE /users/42        delete
GET    /users/42/orders sub-resource
```

## Going further (frameworks)
The JDK server is great for demos/tests. Real services typically use **Spring Boot**
(`@RestController`) or **JAX-RS** (Jakarta), and **Jackson** for JSON binding:
```java
@RestController
class UserController {
    @GetMapping("/users/{id}")
    User get(@PathVariable int id) { return service.find(id); }
}
```
