# JavaMastery

> Complete Java learning — **numbered packages + sequenced docs** from first program to Principal Engineer.

This tree is **JavaForge**, the canonical content and code repository for JavaMastery. It was migrated from the historical [JavaMastery](https://github.com/srinivasraoravinuthala/JavaMastery) repository at commit `3a409be`. That repository stays untouched. JavaForge-UI reads this tree at build time.

| | |
|---|---|
| **Language** | Java 21 (JDK 25 compatible) |
| **Run code** | `java pkg1core/core1HelloWorld.java` |
| **All documentation** | Everything below — or [docs/00-INDEX.md](docs/00-INDEX.md) for the same map inside `docs/` |

---

## Start here (4 steps)

0. **Set up your machine** — [Environment Setup](docs/01-orientation/03-EnvironmentSetup.md) (JDK, clone project, IDE, first run)  
1. **Read** [What is Java?](docs/01-orientation/01-TutorialAndHistory.md) — JVM, WORA, history  
2. **Follow** [Learn chapter 01](docs/02-learn/01-GettingStarted.md) — tutorials basics → advanced (37 chapters)  
3. **Run** your first programs:

```bash
java pkg0intro/intro1AboutJava.java
java pkg1core/core1HelloWorld.java
```

**Code run order** (which `.java` file next): [docs/01-orientation/02-LearningPath.md](docs/01-orientation/02-LearningPath.md)

---

## Branch strategy

| Branch | Purpose |
|--------|---------|
| `main` | Canonical content. JavaForge-UI copies this branch at build time. |
| `develop` | Active work — merge to `main` when ready to publish. |

---

## Documentation map

All markdown lives under `docs/`. **This README is the project entry point** — every link you need is below.

```
docs/
├── 00-INDEX.md              Detailed index (mirror of this section)
├── 01-orientation/          What is Java? + runnable code order
├── 02-learn/                Tutorials ch.01–37  ← READ IN ORDER
├── 03-interview/            ~1,500 interview Q&A
├── 04-reference/            Deep-dive topic guides
├── 05-quick-ref/            Cheatsheet + one-page notes
└── 06-career/               Roadmap + interview strategy
```

---

### 01 — Orientation

| File | Purpose |
|------|---------|
| [03-EnvironmentSetup.md](docs/01-orientation/03-EnvironmentSetup.md) | **Start here** — JDK, clone/ZIP, IntelliJ/Eclipse, Maven intro, first run |
| [01-TutorialAndHistory.md](docs/01-orientation/01-TutorialAndHistory.md) | Java definition, JDK/JVM/JRE, timeline |
| [02-LearningPath.md](docs/01-orientation/02-LearningPath.md) | Package run order: `pkg0` → `pkg20` |

---

### 02 — Learn (tutorials, 37 chapters)

**[Full chapter index →](docs/02-learn/00-INDEX.md)**

| Chapters | Topics | Start |
|----------|--------|-------|
| **01–09** | Variables, operators, loops, methods, arrays, strings, input | [ch.01](docs/02-learn/01-GettingStarted.md) |
| **10–15** | OOP: classes, inheritance, interfaces, records, sealed | [ch.10](docs/02-learn/10-ClassesAndObjects.md) |
| **16–21** | Exceptions, collections, generics, lambdas, streams, Java versions | [ch.16](docs/02-learn/16-Exceptions.md) |
| **22–24** | Data structures, algorithms, LeetCode | [ch.22](docs/02-learn/22-DataStructures.md) |
| **25–27** | JVM, concurrency, design patterns | [ch.25](docs/02-learn/25-JVMAndMemory.md) |
| **28–32** | I/O, networking, JDBC, REST, std libraries | [ch.28](docs/02-learn/28-IOAndNIO.md) |
| **33–37** | Testing, modules, serialization, performance, interview prep | [ch.33](docs/02-learn/33-Testing.md) |

---

### 03 — Interview Q&A (~1,500 questions)

**[Full topic index →](docs/03-interview/00-INDEX.md)**

| # | Topic | File |
|---|-------|------|
| 01 | Core Java | [01-CoreJava.md](docs/03-interview/01-CoreJava.md) |
| 02 | OOP & SOLID | [02-OopAndSolid.md](docs/03-interview/02-OopAndSolid.md) |
| 03 | Collections | [03-Collections.md](docs/03-interview/03-Collections.md) |
| 04 | Concurrency | [04-Concurrency.md](docs/03-interview/04-Concurrency.md) |
| 05 | JVM | [05-JVM.md](docs/03-interview/05-JVM.md) |
| 06 | Streams | [06-Streams.md](docs/03-interview/06-Streams.md) |
| 07 | Exceptions | [07-Exceptions.md](docs/03-interview/07-Exceptions.md) |
| 08 | Generics | [08-Generics.md](docs/03-interview/08-Generics.md) |
| 09 | Strings & performance | [09-StringsAndPerformance.md](docs/03-interview/09-StringsAndPerformance.md) |
| 10 | Java 9–21 | [10-Java9To21Features.md](docs/03-interview/10-Java9To21Features.md) |
| 11 | I/O & serialization | [11-IOAndSerialization.md](docs/03-interview/11-IOAndSerialization.md) |
| 12 | Reflection | [12-ReflectionAndAnnotations.md](docs/03-interview/12-ReflectionAndAnnotations.md) |
| 13 | Design patterns (GoF) | [13-DesignPatterns.md](docs/03-interview/13-DesignPatterns.md) |
| 14 | Effective Java | [14-EffectiveJava.md](docs/03-interview/14-EffectiveJava.md) |
| 15 | Spring Boot | [15-SpringBoot.md](docs/03-interview/15-SpringBoot.md) |
| 16 | JDBC / JPA / Hibernate | [16-JdbcJpaHibernate.md](docs/03-interview/16-JdbcJpaHibernate.md) |
| 17 | Print puzzles | [17-PrintPuzzles.md](docs/03-interview/17-PrintPuzzles.md) |
| 18 | System design | [18-SystemDesign.md](docs/03-interview/18-SystemDesign.md) |
| 19 | Performance | [19-Performance.md](docs/03-interview/19-Performance.md) |

**Interview strategy:** [docs/06-career/02-InterviewGuide.md](docs/06-career/02-InterviewGuide.md)

---

### 04 — Reference (deep dives)

**[Full reference index →](docs/04-reference/00-INDEX.md)**

| # | File | Pair with learn ch. |
|---|------|---------------------|
| 01 | [Java Versions](docs/04-reference/01-JavaVersions.md) | 21 |
| 02 | [JVM Internals](docs/04-reference/02-JVMInternals.md) | 25 |
| 03 | [Design Patterns](docs/04-reference/03-DesignPatterns.md) | 27 |
| 04 | [I/O](docs/04-reference/04-IO.md) | 28 |
| 05 | [Networking](docs/04-reference/05-Networking.md) | 29 |
| 06 | [JDBC](docs/04-reference/06-JDBC.md) | 30 |
| 07 | [REST APIs](docs/04-reference/07-RestApis.md) | 31 |
| 08 | [Libraries](docs/04-reference/08-Libraries.md) | 32 |
| 09 | [Testing](docs/04-reference/09-Testing.md) | 33 |
| 10 | [Modules](docs/04-reference/10-Modules.md) | 34 |
| 11 | [Memory Model](docs/04-reference/11-MemoryModel.md) | 26 |
| 12 | [Serialization](docs/04-reference/12-Serialization.md) | 35 |
| 13 | [Build Tools](docs/04-reference/13-BuildTools.md) | 36 |
| 14 | [LeetCode](docs/04-reference/14-LeetCode.md) | 24 |

---

### 05 — Quick reference

| File | Use when |
|------|----------|
| [01-Cheatsheet.md](docs/05-quick-ref/01-Cheatsheet.md) | Syntax lookup while coding |
| [02-JavaNotes.md](docs/05-quick-ref/02-JavaNotes.md) | One-page revision (JVM, GC, collections) |

---

### 06 — Career

| File | Use when |
|------|----------|
| [01-Roadmap.md](docs/06-career/01-Roadmap.md) | Beginner → Principal stages & skill checklist |
| [02-InterviewGuide.md](docs/06-career/02-InterviewGuide.md) | Interview stages, STAR, 30/60/90-day plan |

---

## Runnable packages

| Package | Topic | Run example |
|---------|-------|-------------|
| `pkg0intro` | About Java, history | `java pkg0intro/intro1AboutJava.java` |
| `pkg1core` | Core Java (`core1`–`core28`) | `java pkg1core/core1HelloWorld.java` |
| `pkg2versions` | Java 5 → 21 features | `java pkg2versions/versions1Java5Features.java` |
| `pkg3datastructures` | DSA structures | `java pkg3datastructures/datastructures0DynamicArray.java` |
| `pkg4algorithms` | Sorting, DP, graphs | `java pkg4algorithms/algorithms1SortingAlgorithms.java` |
| `pkg5leetcode` | 249 LeetCode solutions | `java pkg5leetcode/blind75/blind75_LC1TwoSum.java` |
| `pkg6jvm` | JVM & GC | `java pkg6jvm/jvm1ClassLoadingDemo.java` |
| `pkg7concurrency` | Threads, locks | `java pkg7concurrency/concurrency1ThreadBasics.java` |
| `pkg8patterns` | 23 GoF patterns | `java pkg8patterns/patterns1SingletonPattern.java` |
| `pkg9io`–`pkg13libs` | I/O, HTTP, JDBC, REST | see [learn ch.28–32](docs/02-learn/28-IOAndNIO.md) |
| `pkg14`–`pkg20` | Testing, modules, Maven | `mvn test -f pkg14testing/pom.xml` |

### `pkg1core` learning order

```
core1HelloWorld → core9StringsDemo → core17SealedClassesDemo
core2Variables → core10Encapsulation → core18ExceptionsDemo
core3DataTypes → core11Inheritance → core19CollectionsDemo
core4Operators → core12Polymorphism → core20GenericsDemo
core5ControlStatements → core13AbstractionDemo → core21FunctionalProgramming
core6Loops → core14InterfacesDemo → core22StreamsDemo
core7Methods → core15EnumsDemo → core23OptionalDemo
core8ArraysDemo → core16RecordsDemo → core24UserInput
core25ClassesAndObjects → core26Constructors → core27StaticMembers → core28ComparatorDemo
```

---

## How to run

```bash
# Single file (Java 11+)
java pkg1core/core1HelloWorld.java

# Whole package (PowerShell)
./run-all.ps1 pkg1core

# All LeetCode (249 files)
./run-leetcode-all.ps1

# Maven modules
mvn test -f pkg14testing/pom.xml
./run-maven-all.ps1
```

Pure-Java packages (`pkg0`–`pkg13`, `pkg15`–`pkg19`) are self-contained. Maven: `pkg14`, `pkg20`, `build/`.

---

## Naming convention

- **Package:** `pkg<N><topic>` → `pkg1core`, `pkg2versions`, …
- **Class:** `<topic><N><Name>` → `core1HelloWorld`, `patterns23VisitorPattern`
- File name = class name = `public class` name. The number is **study order** within that package.

---

## Coverage status (what's complete vs. optional next steps)

| Area | Status | Notes |
|------|--------|-------|
| Core Java tutorials + code | ✅ Complete | `pkg1core` core1–core28, 37 learn chapters |
| OOP, collections, generics, streams | ✅ Complete | Consolidated demo classes (not one-file-per-subtopic) |
| Java 5→21 features | ✅ Covered | Bundled in 6 version classes + docs |
| DSA + algorithms | ✅ Complete | Dynamic array, counting sort, divide & conquer included |
| JVM, concurrency, 23 GoF patterns | ✅ Complete | `pkg6`–`pkg8` |
| Applied Java (I/O, HTTP, JDBC, REST) | ✅ Complete | `pkg9`–`pkg13` |
| Ecosystem (testing, modules, Maven) | ✅ Complete | `pkg14`–`pkg20`, `build/` |
| Interview Q&A | ✅ ~1,500 questions | 19 topics in `docs/03-interview/` |
| LeetCode 75 | ✅ 75/75 | `pkg5leetcode/official75/` |
| Top Interview 150 | ✅ ~150/150 | `blind75` + `interview150` |
| Shared `ListNode` / `TreeNode` helpers | ⬜ Optional | Duplicated per LeetCode file today |
| Radix / bucket sort | ⬜ Optional | Extra sorting algorithms |
| Bellman-Ford / Floyd-Warshall | ⬜ Optional | Extra graph algorithms |
| Swing / JavaFX GUI | ⬜ Not included | Desktop UI (optional track) |
| Servlet / JSP | ⬜ Not included | Legacy web (Spring Boot covered in interview docs) |
| Per-release Java classes (12, 13, 18…) | ⬜ Optional | Features covered in bundled version files |

If you want to extend the project, the highest-impact additions are: **finish Top Interview 150 gaps**, **shared LeetCode node helpers**, and **learn chapters for `pkg17`–`pkg18`** (metaprogramming & resilience patterns).
