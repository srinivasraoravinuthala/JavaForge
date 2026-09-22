# 09 — User Input

**Previous:** [08 Strings](08-Strings.md) · **Next:** [10 Classes & Objects](10-ClassesAndObjects.md)

▶️ `java pkg1core/core24UserInput.java`

---

## Scanner — easiest for beginners

```java
Scanner sc = new Scanner(System.in);
System.out.print("Name: ");
String name = sc.nextLine();

System.out.print("Age: ");
int age = sc.nextInt();
sc.nextLine();   // consume leftover newline — IMPORTANT!
```

| Method | Reads |
|--------|-------|
| `nextLine()` | Entire line as String |
| `nextInt()` | Next int token |
| `nextDouble()` | Next double |
| `hasNext()` | More input available? |

⚠️ **Classic bug:** `nextInt()` then `nextLine()` — the `nextLine()` reads an empty string because the newline after the number is still in the buffer. Call `nextLine()` once to consume it.

---

## BufferedReader — faster for large text

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
```

Preferred for reading many lines from files or stdin in production.

---

## Mini project idea

Combine everything from Part 1:

```
1. Ask user name and age (Scanner)
2. Validate age > 0 (if/else)
3. Store scores in an array
4. Print average with formatted String
```

---

## Practice

1. Run `core24UserInput` (uses simulated input — read the code).
2. Uncomment the interactive section and try with real keyboard input.
3. Build the mini project above.

**Milestone:** You completed **Java Basics** (chapters 01–09). 🎉

**Next →** [10 Classes & Objects](10-ClassesAndObjects.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

