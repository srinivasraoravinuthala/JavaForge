# Project 03 — Todo REST (JDK HttpServer)

**Unlock after:** [Learn ch.31 REST APIs](../../docs/02-learn/31-RestAPIs.md)

## Goal

In-memory todo CRUD over HTTP JSON using JDK `HttpServer` (no Spring yet).

## Done when

- [ ] `GET /api/todos` lists items
- [ ] `POST /api/todos` creates (`{"title":"..."}`)
- [ ] `DELETE /api/todos/{id}` removes
- [ ] Client demo calls the API (or curl)

## Run

```bash
java projects/03-todo-rest/TodoServer.java
# other terminal:
curl http://localhost:8080/api/todos
curl -X POST http://localhost:8080/api/todos -H "Content-Type: application/json" -d "{\"title\":\"Ship lab\"}"
```

## Stretch

Persist todos to a file or H2/SQLite (after ch.30 JDBC).
