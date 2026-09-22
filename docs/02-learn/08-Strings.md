# 08 — Strings

**Previous:** [07 Arrays](07-Arrays.md) · **Next:** [09 User Input](09-UserInput.md)

▶️ `java pkg1core/core9StringsDemo.java`

---

## Strings are immutable

```java
String s = "hello";
s.toUpperCase();        // returns "HELLO"
System.out.println(s);  // still "hello"
```

Every "change" creates a **new** String object.

💡 **Why immutable?** Thread-safe, safe as HashMap keys, string pool caching.

---

## String pool

```java
String a = "hello";
String b = "hello";              // same pooled object
String c = new String("hello");  // new heap object

a == b;           // true (same reference)
a == c;           // false
a.equals(c);      // true (same content)
```

⚠️ Always use `.equals()` for content comparison, not `==`.

---

## Essential methods

```java
s.length();
s.charAt(0);
s.substring(1, 4);
s.indexOf('l');
s.replace("old", "new");
s.split(",");
s.toUpperCase();
s.strip();          // Java 11+ (Unicode-aware trim)
s.isBlank();
"ab".repeat(3);     // "ababab"
```

---

## StringBuilder — efficient building

```java
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 5; i++) sb.append(i).append(',');
System.out.println(sb.toString());
```

Use in loops — `+` concatenation in a loop is O(n²).

---

## Text blocks (Java 15+)

```java
String json = """
    {
      "name": "Java",
      "version": 21
    }
    """;
```

---

## Formatting

```java
String msg = String.format("Hello, %s! Score: %d", name, score);
String msg2 = "Score: %d".formatted(score);   // Java 15+
```

**Deep dive →** [09 Strings & Performance](../03-interview/09-StringsAndPerformance.md)

**Next →** [09 User Input](09-UserInput.md)
