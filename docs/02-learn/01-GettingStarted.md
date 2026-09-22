# 01 — Getting Started

**Previous:** [00 Index](00-INDEX.md) · **Next:** [02 Variables & Types](02-VariablesAndTypes.md)

> **New here?** If you have not installed the JDK, downloaded the project, or set up an IDE yet, complete **[Environment Setup](../01-orientation/03-EnvironmentSetup.md)** first — everything you need is on this site.

---

## What you need

| Tool | Purpose |
|------|---------|
| **JDK 21+** | Compiler (`javac`) + runtime (`java`) |
| **Terminal** | Run commands |
| **This project** | All lessons with runnable examples |

Check your install:

```bash
java -version
javac -version
```

You should see version **21** or higher (25 works too).

---

## Your first program

Every Java app starts with a class and a `main` method — the entry point.

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

▶️ `java pkg0intro/intro1AboutJava.java` · `java pkg1core/core1HelloWorld.java`

```bash
java pkg0intro/intro1AboutJava.java
java pkg1core/core1HelloWorld.java
```

💡 **Key idea:** The file name must match the public class name (`Hello.java` → `class Hello`).

---

## How this project is organized

```
pkg0intro     → What is Java? (start here)
pkg1core      → Language fundamentals (core1 → core28)
pkg2versions  → Java 5 through 21 features
pkg3–pkg4     → Data structures & algorithms
pkg5leetcode  → Interview coding problems
pkg6–pkg20    → JVM, concurrency, I/O, databases, testing…
docs/02-learn/   → These tutorials (read in order)
docs/03-interview/ → Interview Q&A (use later)
```

Each class is **self-contained** — open one file, run it, learn one concept.

---

## Two ways to run code

### 1. Single-file launch (easiest)

```bash
java pkg1core/core1HelloWorld.java
```

No `javac` step — Java compiles and runs in one command (Java 11+).

### 2. Compile then run (classic)

```bash
javac -d out pkg1core/core1HelloWorld.java
java -cp out pkg1core.core1HelloWorld
```

Note: package name in the `-cp` command (`pkg1core.core1HelloWorld`).

---

## The compile → run pipeline

```
YourCode.java  ──javac──►  YourCode.class (bytecode)
                                │
                           java (JVM)
                                ▼
                           Program output
```

Read the full picture in [01-orientation/01-TutorialAndHistory.md](../01-orientation/01-TutorialAndHistory.md).

---

## ⚠️ Common mistakes

| Mistake | Fix |
|---------|-----|
| `java Hello` without compiling (classic mode) | Use `java Hello.java` or compile first |
| File name ≠ class name | Rename file or class to match |
| Wrong directory | Run from project root `JavaMastery/` |
| Missing semicolon `;` | Every statement ends with `;` |

---

## Practice

1. Run `intro1AboutJava`, `intro2HistoryOfJava`, `intro3Objectives`.
2. Run `core1HelloWorld` and change the printed message.
3. Add a second `System.out.println` line.

---

**Next →** [02 Variables & Types](02-VariablesAndTypes.md)
