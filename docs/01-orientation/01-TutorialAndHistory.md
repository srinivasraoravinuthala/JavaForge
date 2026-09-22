# Java — Complete Tutorial, History & Objectives

A single, beginner-friendly tutorial covering **what Java is, where it came from, why it exists, and how it works** — before you dive into the numbered packages.

> Runnable versions of this content: `pkg0intro/intro1AboutJava.java`, `intro2HistoryOfJava.java`, `intro3Objectives.java`.

---

## 1. What is Java? (Definition)

**Java** is a high-level, class-based, **object-oriented**, **statically-typed**, general-purpose programming language. Java source code is compiled to **bytecode** that runs on the **Java Virtual Machine (JVM)**, giving Java its famous promise:

> **"Write Once, Run Anywhere" (WORA)** — compile once, run on any device that has a JVM.

Java is also a **platform** (language + JVM + huge standard library + tooling), not just a language.

### How a Java program runs
```
   YourCode.java
        │  javac (compiler)
        ▼
   YourCode.class   (platform-neutral BYTECODE)
        │  java (launches the JVM)
        ▼
   JVM: class loader → bytecode verifier → interpreter + JIT compiler → native CPU
        │  (+ automatic Garbage Collection for memory)
        ▼
   Program output
```

### JDK vs JRE vs JVM
| Term | Contains | Purpose |
|------|----------|---------|
| **JVM** | execution engine | runs bytecode, manages memory/GC, JIT |
| **JRE** | JVM + standard libraries | to **run** Java apps |
| **JDK** | JRE + `javac` + tools | to **build** Java apps |

### Editions
- **Java SE** (Standard Edition) — the core language + standard library (this project uses only Java SE).
- **Jakarta EE / Java EE** — enterprise APIs (servlets, persistence, messaging).
- **Java ME** — micro edition for constrained/embedded devices.

---

## 2. History of Java

| Year | Milestone |
|------|-----------|
| **1991** | The "Green Project" begins at **Sun Microsystems**; James Gosling, Mike Sheridan & Patrick Naughton design a language called **Oak** (named after a tree outside Gosling's office) for interactive TV / embedded devices. |
| **1995** | Renamed **Java** (after Java coffee — "Oak" was trademarked). Publicly announced with the **WORA** slogan; rode the rise of the web (applets). |
| **1996** | **JDK 1.0** released. |
| **1998** | **J2SE 1.2** ("Java 2"): Swing GUI + the **Collections Framework**. |
| **2004** | **J2SE 5.0**: generics, enums, autoboxing, varargs, annotations, enhanced for. |
| **2006** | **Java 6**: performance; Java open-sourced as **OpenJDK**. |
| **2010** | **Oracle** acquires Sun Microsystems and becomes Java's steward. |
| **2011** | **Java 7**: try-with-resources, diamond operator, NIO.2, Fork/Join. |
| **2014** | **Java 8** (landmark): **lambdas, Streams, Optional**, new Date/Time API. |
| **2017** | **Java 9**: the **module system (JPMS)**, `jshell`; switches to a **6-month release cadence**. |
| **2018** | **Java 10** (`var`) and **Java 11 (LTS)**: HttpClient, run single-file source. |
| **2020** | **Java 14**: switch expressions (final), records (preview). |
| **2021** | **Java 17 (LTS)**: sealed classes, records final, pattern matching for `instanceof`. |
| **2023** | **Java 21 (LTS)**: **virtual threads**, record patterns, sequenced collections. |
| **2025** | **Java 25 (LTS)**: latest long-term-support release. |

**Name evolution:** Oak → Green → **Java**.
**Stewardship:** Sun Microsystems (1991–2010) → **Oracle** (2010–present), developed openly via **OpenJDK**.
**LTS releases** (long-term support, what companies target): **8, 11, 17, 21, 25**.

---

## 3. The Objectives (Design Goals) of Java

The creators set five primary goals. Java should be:

1. **Simple, object-oriented, and familiar** — C-like syntax, no pointers or manual memory management.
2. **Robust and secure** — strong static typing, exceptions, garbage collection, bytecode verification, sandboxing.
3. **Architecture-neutral and portable** — bytecode + JVM; fixed primitive sizes across platforms.
4. **High performance** — Just-In-Time (JIT) compilation of hot code to native instructions.
5. **Interpreted, threaded, and dynamic** — fast startup, built-in multithreading, runtime class loading.

These goals explain nearly every design decision in the language.

### Why learn Java?
- Dominant in **enterprise**, **Android**, **big data**, and **cloud** systems.
- Massive **ecosystem**, libraries, tooling, and community.
- **Backward compatible** with stable LTS releases.
- Excellent vehicle for learning **OOP, data structures, algorithms, and system design**.
- Strong **career and interview** demand.

---

## 4. Core Concepts Glossary (quick definitions)

| Term | Definition |
|------|------------|
| **Class** | Blueprint describing state (fields) and behavior (methods). |
| **Object** | A runtime instance of a class. |
| **Method** | A named block of behavior; may take parameters and return a value. |
| **Field/Variable** | Named storage for data (instance, static, or local). |
| **Encapsulation** | Hiding internal state behind methods. |
| **Inheritance** | A class acquiring members of another (`extends`). |
| **Polymorphism** | One interface, many implementations (runtime dispatch). |
| **Abstraction** | Exposing essentials, hiding details (abstract classes/interfaces). |
| **Interface** | A contract of methods a class promises to implement. |
| **Package** | A namespace grouping related classes (e.g. `pkg1core`). |
| **Bytecode** | Platform-neutral instructions in `.class` files. |
| **JVM** | The virtual machine that executes bytecode. |
| **Garbage Collection** | Automatic reclamation of unreachable objects. |
| **Exception** | A signal of an abnormal condition, handled with try/catch. |
| **Generics** | Parameterized types for compile-time type safety. |
| **Lambda** | A concise anonymous function implementing a functional interface. |
| **Stream** | A declarative pipeline for processing data. |
| **Thread** | An independent path of execution within a process. |

---

## 5. Your First Program (anatomy)

```java
package pkg1core;            // 1. namespace this class belongs to

public class core1HelloWorld {            // 2. class (file name matches)
    public static void main(String[] args) {   // 3. JVM entry point
        System.out.println("Hello, JavaMastery!"); // 4. print to console
    }
}
```
- `public` — visible to the JVM/other code.
- `static` — callable without creating an object.
- `void` — returns nothing.
- `main(String[] args)` — the standard entry point; `args` are command-line arguments.

**Run it:**
```bash
java pkg0intro/intro1AboutJava.java        # the runnable "about Java" tutorial
java pkg1core/core1HelloWorld.java         # your first program
```

---

## 6. Where to go next

**Start the JavaMastery tutorial path:** [02-learn/00-INDEX.md](../02-learn/00-INDEX.md) → [01 Getting Started](../02-learn/01-GettingStarted.md)

Then follow the numbered packages — see [README.md](../../README.md):
- **Tutorials:** [02-learn/00-INDEX.md](../02-learn/00-INDEX.md) (basics → advanced, in order)
- **Reference:** [Java versions](../04-reference/01-JavaVersions.md) · [JVM internals](../04-reference/02-JVMInternals.md) · [Design patterns](../04-reference/03-DesignPatterns.md)
- **Practice:** [LeetCode](../04-reference/14-LeetCode.md) · [Interview Q&A](../03-interview/00-INDEX.md)
