# 02 — Learn: Basics to Advanced

**Part of:** [docs/00-INDEX.md](../00-INDEX.md)

> Read chapters **in order**. Each links to runnable `.java` files.

1. Read one chapter (15–25 min)
2. Run the code: `java pkg1core/core1HelloWorld.java`
3. Experiment — change code, re-run
4. For interviews → matching [03-interview](../03-interview/00-INDEX.md) topic

---

## Before chapter 01

| Doc | Purpose |
|-----|---------|
| [01-orientation/01-TutorialAndHistory.md](../01-orientation/01-TutorialAndHistory.md) | What is Java? |
| [01-orientation/02-LearningPath.md](../01-orientation/02-LearningPath.md) | Package run order |

---

## Chapters 01–09 — Java basics

| Ch | Title | Code |
|----|-------|------|
| [01](01-GettingStarted.md) | Getting Started | `pkg0intro`, `core1HelloWorld` |
| [02](02-VariablesAndTypes.md) | Variables & Types | `core2`, `core3` |
| [03](03-OperatorsAndCasting.md) | Operators & Casting | `core4` |
| [04](04-ControlFlow.md) | Control Flow | `core5` |
| [05](05-Loops.md) | Loops | `core6` |
| [06](06-Methods.md) | Methods | `core7` |
| [07](07-Arrays.md) | Arrays | `core8` |
| [08](08-Strings.md) | Strings | `core9` |
| [09](09-UserInput.md) | User Input | `core24` |

## Chapters 10–15 — OOP

| Ch | Title | Code |
|----|-------|------|
| [10](10-ClassesAndObjects.md) | Classes & Objects | `core25` |
| [11](11-ConstructorsAndEncapsulation.md) | Constructors & Encapsulation | `core26`, `core10` |
| [12](12-InheritanceAndPolymorphism.md) | Inheritance & Polymorphism | `core11`, `core12` |
| [13](13-AbstractionAndInterfaces.md) | Abstraction & Interfaces | `core13`, `core14` |
| [14](14-StaticAndEnums.md) | Static & Enums | `core27`, `core15` |
| [15](15-RecordsAndSealed.md) | Records & Sealed | `core16`, `core17` |

## Chapters 16–21 — Core APIs

| Ch | Title | Code |
|----|-------|------|
| [16](16-Exceptions.md) | Exceptions | `core18` |
| [17](17-Collections.md) | Collections | `core19`, `core28` |
| [18](18-Generics.md) | Generics | `core20` |
| [19](19-LambdasAndFunctional.md) | Lambdas | `core21` |
| [20](20-StreamsAndOptional.md) | Streams & Optional | `core22`, `core23` |
| [21](21-JavaVersions.md) | Java 5→21 | `pkg2versions` |

## Chapters 22–24 — CS & practice

| Ch | Title | Code |
|----|-------|------|
| [22](22-DataStructures.md) | Data Structures | `pkg3datastructures` |
| [23](23-Algorithms.md) | Algorithms | `pkg4algorithms` |
| [24](24-LeetCode.md) | LeetCode | `pkg5leetcode` |

## Chapters 25–27 — Senior

| Ch | Title | Code |
|----|-------|------|
| [25](25-JVMAndMemory.md) | JVM & Memory | `pkg6jvm` |
| [26](26-Concurrency.md) | Concurrency | `pkg7`, `pkg16` |
| [27](27-DesignPatterns.md) | Design Patterns | `pkg8patterns` |

## Chapters 28–32 — Applied Java

| Ch | Title | Code |
|----|-------|------|
| [28](28-IOAndNIO.md) | I/O & NIO | `pkg9io` |
| [29](29-Networking.md) | Networking | `pkg10networking` |
| [30](30-JDBC.md) | JDBC | `pkg11jdbc` |
| [31](31-RestAPIs.md) | REST APIs | `pkg12restapi` |
| [32](32-StandardLibraries.md) | Std Libraries | `pkg13libs` |

## Chapters 33–41 — Professional & full-stack

| Ch | Title | Code |
|----|-------|------|
| [33](33-Testing.md) | Testing | `pkg14testing` |
| [34](34-Modules.md) | Modules & SPI | `pkg15modules` |
| [35](35-Serialization.md) | Serialization | `pkg20serialization` |
| [36](36-PerformanceAndBuild.md) | Performance & Build | `pkg19`, `build/` |
| [37](37-InterviewPrep.md) | Interview Prep | `03-interview/` |
| [38](38-Metaprogramming.md) | Metaprogramming | `pkg17metaprogramming` |
| [39](39-ResiliencePatterns.md) | Resilience Patterns | `pkg18resiliencepatterns` |
| [40](40-SpringBootIntro.md) | Spring Boot Intro | `pkg21spring` |
| [41](41-RestAndFrontend.md) | REST & Frontend | `projects/05-notes-web` |

---

## Projects (hands-on labs)

See [07-projects/00-INDEX.md](../07-projects/00-INDEX.md) for the full ladder (gradebook → library → todo REST → Spring notes → browser UI).

---

## Package map (learn path)

| Package | Chapters | Topics |
|---------|----------|--------|
| `pkg0intro` | 01 | Hello World, setup |
| `pkg1core` | 01–15 | Core language & OOP |
| `pkg2versions` | 21 | Java 5→21 features |
| `pkg3datastructures` | 22 | DS implementations |
| `pkg4algorithms` | 23 | Sorting, searching, graphs |
| `pkg5leetcode` | 24 | LeetCode study plans (+ `common/` helpers) |
| `pkg6jvm` | 25 | JVM, memory, GC |
| `pkg7concurrency` | 26 | Threads, executors |
| `pkg16advconcurrency` | 26 | JMM, advanced concurrency |
| `pkg8patterns` | 27 | Design patterns |
| `pkg9io`–`pkg15modules` | 28–34 | I/O, networking, JDBC, REST, libs, testing, modules |
| `pkg17metaprogramming` | 38 | Annotations, proxies, reflection |
| `pkg18resiliencepatterns` | 39 | Circuit breaker, retry, bulkhead |
| `pkg19performance`–`pkg20serialization` | 35–36 | Performance, serialization |
| `pkg21spring` | 40–41 | Spring Boot notes API |

---

## Milestones

| After ch. | You can… |
|-----------|----------|
| 09 | Write a console program with loops, methods, classes |
| 15 | Model problems with OOP |
| 20 | Use Collections + Streams on real data |
| 23 | Implement DSA from memory |
| 27 | Explain JVM, threads, patterns |
| 32 | Build file/HTTP/DB-backed apps |
| 37 | Pass senior Java interviews |
| 40 | Build Spring Boot services with resilience patterns |
| 41 | Connect a browser UI to your Java API |

**Quick ref →** [05-quick-ref/00-INDEX.md](../05-quick-ref/00-INDEX.md)

**Start →** [01 Getting Started](01-GettingStarted.md)
