# pkg21spring — Spring Boot Notes API

Hands-on lab for [ch.40 Spring Boot Intro](../docs/02-learn/40-SpringBootIntro.md).

```bash
cd pkg21spring
mvn spring-boot:run
```

| File | Role |
|------|------|
| `NotesApplication.java` | `@SpringBootApplication` entry |
| `Note.java` | JPA entity |
| `NoteRepository.java` | Spring Data JPA |
| `NoteController.java` | REST `/api/notes` |
| `application.properties` | H2 + port |

Also listed as [Project 04](../projects/04-notes-api/README.md). Frontend: [Project 05](../projects/05-notes-web/README.md).
