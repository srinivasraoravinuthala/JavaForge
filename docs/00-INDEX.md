# JavaMastery Documentation — Master Index

> **Everything in one sequenced place.** Read top-to-bottom for learning; jump by number when reviewing.

---

## New student?

**Start here → [01-orientation/03-EnvironmentSetup.md](01-orientation/03-EnvironmentSetup.md)** — install JDK 21, download the project, set up IntelliJ or Eclipse, and run your first program. Everything you need is on this site.

---

## Documentation map

```
docs/
├── 00-INDEX.md              ← YOU ARE HERE (master index)
│
├── 01-orientation/          Step 0 — What is Java? How to navigate this project
├── 02-learn/                Steps 1–41 — Tutorials: basics → advanced (READ IN ORDER)
├── 03-interview/            Interview Q&A bank (~1,500 questions, 19 topics)
├── 04-reference/            Deep-dive topic guides (use after matching learn chapter)
├── 05-quick-ref/            Cheatsheet & one-page revision
├── 06-career/               Roadmap & interview strategy
└── 07-projects/             Hands-on labs (gradebook → Spring + browser UI)
```

---

## Link conventions

When linking between docs, **always use numbered filenames**:

- Good: `../03-interview/07-Exceptions.md`
- Bad: `../03-interview/Exceptions.md`

Relative paths resolve from the current file (`../` for sibling folders, `../../` for cross-section).

---

## Start here (new learners)

| Order | What | Link |
|-------|------|------|
| 1 | What is Java? History, JVM, WORA | [01-orientation/01-TutorialAndHistory.md](01-orientation/01-TutorialAndHistory.md) |
| 2 | Run order for `.java` files | [01-orientation/02-LearningPath.md](01-orientation/02-LearningPath.md) |
| 3 | **Tutorial chapter 1** | [02-learn/00-INDEX.md](02-learn/00-INDEX.md) → [01 Getting Started](02-learn/01-GettingStarted.md) |

```bash
java pkg0intro/intro1AboutJava.java
java pkg1core/core1HelloWorld.java
```

---

## 01 — Orientation

| # | File | Purpose |
|---|------|---------|
| 01 | [Tutorial & History](01-orientation/01-TutorialAndHistory.md) | Java definition, timeline, JDK/JVM |
| 02 | [Learning Path](01-orientation/02-LearningPath.md) | Package run order (`pkg0` → `pkg20`) |

---

## 02 — Learn (tutorials, 41 chapters)

**[→ Open learn index](02-learn/00-INDEX.md)** · **[Projects](07-projects/00-INDEX.md)**

| Part | Chapters | Topics |
|------|----------|--------|
| Basics | 01–09 | Syntax, types, loops, methods, arrays, strings |
| OOP | 10–15 | Classes, inheritance, interfaces, records |
| Core APIs | 16–21 | Exceptions, collections, generics, streams |
| CS | 22–24 | DSA, algorithms, LeetCode |
| Senior | 25–27 | JVM, concurrency, patterns |
| Applied | 28–32 | I/O, HTTP, JDBC, REST |
| Pro | 33–41 | Testing, modules, Spring, REST ↔ frontend |

---

## 03 — Interview Q&A

**[→ Open interview index](03-interview/00-INDEX.md)**

18 numbered topic files from Core Java through System Design. Use after completing matching learn chapters.

---

## 04 — Reference (deep dives)

| # | File | Read after learn ch. |
|---|------|----------------------|
| 01 | [Java Versions](04-reference/01-JavaVersions.md) | 21 |
| 02 | [JVM Internals](04-reference/02-JVMInternals.md) | 25 |
| 03 | [Design Patterns](04-reference/03-DesignPatterns.md) | 27 |
| 04 | [I/O](04-reference/04-IO.md) | 28 |
| 05 | [Networking](04-reference/05-Networking.md) | 29 |
| 06 | [JDBC](04-reference/06-JDBC.md) | 30 |
| 07 | [REST APIs](04-reference/07-RestApis.md) | 31 |
| 08 | [Libraries](04-reference/08-Libraries.md) | 32 |
| 09 | [Testing](04-reference/09-Testing.md) | 33 |
| 10 | [Modules](04-reference/10-Modules.md) | 34 |
| 11 | [Memory Model](04-reference/11-MemoryModel.md) | 26 |
| 12 | [Serialization](04-reference/12-Serialization.md) | 35 |
| 13 | [Build Tools](04-reference/13-BuildTools.md) | 36 |
| 14 | [LeetCode](04-reference/14-LeetCode.md) | 24 |

---

## 05 — Quick reference

| # | File | Use when |
|---|------|----------|
| 01 | [Cheatsheet](05-quick-ref/01-Cheatsheet.md) | Syntax lookup while coding |
| 02 | [Java Notes](05-quick-ref/02-JavaNotes.md) | One-page revision before interview |

---

## 06 — Career

| # | File | Use when |
|---|------|----------|
| 01 | [Roadmap](06-career/01-Roadmap.md) | Beginner → Principal skill checklist |
| 02 | [Interview Guide](06-career/02-InterviewGuide.md) | Prep framework, 30/60/90 plan |

---

## Runnable code (packages)

| Package | Docs chapter |
|---------|--------------|
| `pkg0intro` | orientation |
| `pkg1core` | learn 01–21 |
| `pkg2versions` | learn 21 |
| `pkg3–4` | learn 22–23 |
| `pkg5leetcode` | learn 24 |
| `pkg6–8` | learn 25–27 |
| `pkg9–13` | learn 28–32 |
| `pkg14–20` | learn 33–36 |

Project entry: [README.md](../../README.md) (full documentation index)
