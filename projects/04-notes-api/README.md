# Project 04 — Notes API (Spring Boot + H2)

**Unlock after:** [Learn ch.40 Spring Boot Intro](../../docs/02-learn/40-SpringBootIntro.md)

Runnable code lives in [`pkg21spring/`](../../pkg21spring/).

## Goal

Spring Boot REST notes API with JPA + H2.

## Done when

- [ ] `GET /api/notes` / `POST /api/notes` / `DELETE /api/notes/{id}` work
- [ ] Data survives process restart only in-file H2 (or document in-memory)
- [ ] `mvn test` (or at least app starts)

## Run

```bash
cd pkg21spring
mvn spring-boot:run
curl http://localhost:8080/api/notes
```

## Next

Wire a browser UI in [Project 05 — Notes Web](../05-notes-web/README.md).
