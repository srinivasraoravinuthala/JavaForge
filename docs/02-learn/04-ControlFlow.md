# 04 — Control Flow

**Previous:** [03 Operators & Casting](03-OperatorsAndCasting.md) · **Next:** [05 Loops](05-Loops.md)

▶️ `java pkg1core/core5ControlStatements.java`

---

## if / else if / else

```java
int score = 82;
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else {
    System.out.println("Below B");
}
```

Only **one branch** runs — the first condition that is true.

---

## switch — classic (statement)

```java
int day = 3;
switch (day) {
    case 1: System.out.println("Monday"); break;
    case 2: System.out.println("Tuesday"); break;
    case 3: System.out.println("Wednesday"); break;
    default: System.out.println("Other");
}
```

⚠️ **Without `break`, execution falls through** to the next case.

---

## switch — modern expression (Java 14+)

```java
String type = switch (day) {
    case 1, 2, 3, 4, 5 -> "Weekday";
    case 6, 7          -> "Weekend";
    default -> {
        yield "Invalid";   // yield returns from a block
    }
};
```

💡 Arrow form `->` **does not fall through**. Switch can return a value.

---

## Pattern matching for switch (Java 21)

```java
sealed interface Shape permits Circle, Rectangle {}
record Circle(double r) implements Shape {}
record Rectangle(double w, double h) implements Shape {}

double area = switch (shape) {
    case Circle c    -> Math.PI * c.r() * c.r();
    case Rectangle r -> r.w() * r.h();
};
```

Works beautifully with **sealed types** — compiler checks exhaustiveness.

---

## When to use what

| Construct | Use when |
|-----------|----------|
| `if/else` | Ranges, complex boolean logic |
| `switch` | Single variable, many discrete values |
| switch expression | Assigning a result from discrete cases |

---

## Practice

1. Run `core5ControlStatements`.
2. Convert a 5-branch if/else grade system to a switch expression.
3. Add a `default` case — when is it required?

**Next →** [05 Loops](05-Loops.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

