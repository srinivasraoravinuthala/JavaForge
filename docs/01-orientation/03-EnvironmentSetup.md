# 03 — Environment Setup

**Part of:** [docs/00-INDEX.md](../00-INDEX.md)

**Previous:** [02 Learning Path](02-LearningPath.md) · **Next:** [02-learn/01 Getting Started](../02-learn/01-GettingStarted.md)

> **Goal:** By the end of this page you can run your first Java program from this project — on your machine, in an IDE or terminal — without hunting for install guides elsewhere.

---

## 0. What you are setting up

| Piece | What it is |
|-------|------------|
| **This website** | Tutorials, interview Q&A, and runnable examples — read and learn here |
| **[JavaMastery on GitHub](https://github.com/srinivasraoravinuthala/JavaMastery)** | The code you download and run locally (`pkg0intro`, `pkg1core`, …) |
| **JDK 21** | Java compiler + runtime (required) |
| **IDE (optional but recommended)** | IntelliJ IDEA Community or Eclipse — edit and run with one click |
| **Maven (later)** | Build tool for multi-module examples — not needed on day one |

**Project link:** https://github.com/srinivasraoravinuthala/JavaMastery

---

## 1. Install JDK 21 (Eclipse Temurin)

We recommend **Eclipse Temurin** (from Adoptium) — free, open source, widely used in tutorials.

**Download:** https://adoptium.net/temurin/releases/?version=21

Pick **JDK 21**, your operating system, and **x64** (most PCs). Download the **.msi** (Windows), **.pkg** (macOS), or **.tar.gz** / package manager (Linux).

### Windows

1. Run the Temurin **.msi** installer.
2. Keep **“Set JAVA_HOME variable”** and **“Add to PATH”** checked (default).
3. Finish the installer.

### macOS

1. Run the **.pkg** installer and follow the prompts.
2. Temurin is usually installed under `/Library/Java/JavaVirtualMachines/`.

Or with Homebrew:

```bash
brew install temurin@21
```

### Linux (Debian/Ubuntu example)

```bash
sudo apt update
sudo apt install temurin-21-jdk
```

For other distros, use the `.tar.gz` from Adoptium or your package manager.

---

## 2. Verify Java in the terminal

Open **Terminal** (macOS/Linux) or **Command Prompt / PowerShell** (Windows).

```bash
java -version
javac -version
```

You should see **version 21** (or higher — 25 also works). Example:

```
openjdk version "21.0.x" ...
```

| Problem | Fix |
|---------|-----|
| `'java' is not recognized` | Reopen the terminal after install; on Windows, confirm PATH includes Temurin’s `bin` folder |
| Old version (8, 11, …) | Uninstall older JDKs or set `JAVA_HOME` to Temurin 21 (see below) |

**Optional — set `JAVA_HOME` (if IDEs pick the wrong Java):**

| OS | Typical `JAVA_HOME` |
|----|---------------------|
| Windows | `C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot` |
| macOS | `/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home` |
| Linux | `/usr/lib/jvm/temurin-21-jdk-amd64` (path may vary) |

Add `%JAVA_HOME%\bin` (Windows) or `$JAVA_HOME/bin` (macOS/Linux) to **PATH**.

---

## 3. Get the JavaMastery project

Choose **one** method.

### Option A — Git clone (recommended if you use Git)

```bash
git clone https://github.com/srinivasraoravinuthala/JavaMastery.git
cd JavaMastery
```

### Option B — Download ZIP (no Git required)

1. Open https://github.com/srinivasraoravinuthala/JavaMastery
2. Click green **Code** → **Download ZIP**
3. Extract the ZIP to a folder you will remember (e.g. `Documents/JavaMastery`)
4. Open a terminal in that folder — all run commands below assume you are in the **project root** (you should see folders like `pkg0intro`, `pkg1core`, `docs`)

---

## 4. Pick an IDE (choose one)

Both are **free**. You only need **one**.

| | **IntelliJ IDEA Community** (recommended) | **Eclipse IDE for Java Developers** |
|---|-------------------------------------------|-------------------------------------|
| Download | https://www.jetbrains.com/idea/download/ | https://www.eclipse.org/downloads/ |
| Best for | Fast setup, strong Java support | Classic, widely used in courses |
| Open project | **Open** → select `JavaMastery` folder | **Import** → existing Maven or file system |

---

## 5a. IntelliJ IDEA — open and run

1. Install **IntelliJ IDEA Community Edition**.
2. **File → Open** → select your `JavaMastery` folder → **Trust Project**.
3. In the Project tree, open `pkg0intro/intro1AboutJava.java`.
4. Click the **green Run** arrow next to `main`, or right-click the file → **Run 'intro1AboutJava.main()'**.
5. You should see output in the **Run** panel at the bottom.

**Terminal fallback (same folder):**

```bash
java pkg0intro/intro1AboutJava.java
```

If IntelliJ says “No JDK”: **File → Project Structure → Project → SDK** → add Temurin 21.

---

## 5b. Eclipse — import and run

1. Install **Eclipse IDE for Java Developers**.
2. **File → Open Projects from File System…** → **Directory** → select your `JavaMastery` folder → **Finish**.
   - If prompted for Maven: you can import as a general Java project; Maven is not required for `pkg0` / `pkg1` single-file runs.
3. In **Package Explorer**, open `pkg0intro` → `intro1AboutJava.java`.
4. Right-click the file → **Run As → Java Application**.
5. Output appears in the **Console** view.

**Terminal fallback:**

```bash
cd path/to/JavaMastery
java pkg0intro/intro1AboutJava.java
```

If Eclipse uses the wrong JRE: **Window → Preferences → Java → Installed JREs** → add Temurin 21.

---

## 6. Run from the terminal (project root)

All examples in this course assume you run commands from the **JavaMastery root** (where `pkg0intro` lives).

```bash
cd JavaMastery          # skip if already there
java pkg0intro/intro1AboutJava.java
java pkg1core/core1HelloWorld.java
```

No separate `javac` step — Java 11+ compiles and runs single files in one command.

---

## 7. Install Maven (when you need it)

**You do not need Maven for chapters 01–35.** Single-file `java pkg…/….java` is enough.

Install Maven when you reach **multi-module build examples** (`build/`) or [chapter 36 — Performance & Build](../02-learn/36-PerformanceAndBuild.md).

**Verify after install:**

```bash
mvn -version
```

| OS | Quick install |
|----|----------------|
| Windows | https://maven.apache.org/download.cgi — unzip, add `bin` to PATH; or `choco install maven` |
| macOS | `brew install maven` |
| Linux | `sudo apt install maven` |

---

## 8. How this website fits in

1. **Read** tutorials here (Orientation → Learn chapters in order).
2. **Run** the matching `.java` files locally (steps above) or use **Examples → View source → Run online** on this site.
3. **Learning order:**
   - [01 Tutorial & History](01-TutorialAndHistory.md)
   - [02-learn/01 Getting Started](../02-learn/01-GettingStarted.md)
   - `pkg0intro` → `pkg1core` (see [Learning Path](02-LearningPath.md))

---

## 9. Ready checklist

Before [chapter 02 — Variables & Types](../02-learn/02-VariablesAndTypes.md), confirm:

- [ ] `java -version` shows **21+**
- [ ] JavaMastery project is on your machine (clone or ZIP)
- [ ] You ran `java pkg0intro/intro1AboutJava.java` successfully (IDE or terminal)
- [ ] You ran `java pkg1core/core1HelloWorld.java` successfully
- [ ] You know where the project root folder is on disk

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---------|--------------|-----|
| `Could not find or load main class` | Wrong directory | `cd` to JavaMastery root; use `java pkg0intro/intro1AboutJava.java` |
| `error: package pkg0intro does not exist` | Not at project root | Run from folder containing `pkg0intro` |
| IDE shows red errors but terminal works | IDE JDK not set | Point IDE to Temurin 21 (see sections 5a/5b) |
| File name / class name mismatch | Java rule | Public class name must match file name (`Hello.java` → `class Hello`) |

---

**Next →** [02-learn/01 Getting Started](../02-learn/01-GettingStarted.md)
