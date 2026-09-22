# 40 — Spring Boot Intro

**Previous:** [39 Resilience Patterns](39-ResiliencePatterns.md) · **Next:** [41 REST & Frontend](41-RestAndFrontend.md)

▶️ [`pkg21spring`](../../pkg21spring/README.md) · Lab: [Project 04 Notes API](../../projects/04-notes-api/README.md)

---

## What is Spring Boot?

Spring Boot is the **opinionated** way to build production Java apps — auto-configuration, embedded Tomcat, and a vast ecosystem (Spring Data, Security, Cloud).

| Concept | What it does |
|---------|--------------|
| `@SpringBootApplication` | Enables auto-config + component scan |
| `@RestController` | REST endpoints with `@GetMapping`, etc. |
| `@Autowired` / constructor injection | Dependency injection |
| `application.properties` | Externalized configuration |
| Spring Data JPA | Repository abstraction over Hibernate |

---

## File-by-file in `pkg21spring`

| File | Role |
|------|------|
| `NotesApplication.java` | Entry point |
| `Note.java` | JPA `@Entity` |
| `NoteRepository.java` | `JpaRepository` |
| `NoteController.java` | `/api/notes` CRUD + CORS |
| `application.properties` | H2 in-memory DB |

```bash
cd pkg21spring
mvn spring-boot:run
curl http://localhost:8080/api/notes
```

---

## Minimal REST controller

```java
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteRepository repo;

    public NoteController(NoteRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Note> all() { return repo.findAll(); }
}
```

---

## Learning path for Spring

1. Solid **OOP + JDBC + REST** (chapters 10–31)
2. Run **`pkg21spring`** (this chapter)
3. Read [15 Spring Boot Interview](../03-interview/15-SpringBoot.md)
4. Add a browser UI in [ch.41](41-RestAndFrontend.md)

**Next →** [41 REST & Frontend](41-RestAndFrontend.md)

**Full interview topic →** [15 Spring Boot](../03-interview/15-SpringBoot.md)

**Career guide →** [06-career/02-InterviewGuide.md](../06-career/02-InterviewGuide.md)
