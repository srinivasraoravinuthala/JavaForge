# 15 — Records & Sealed Classes

**Previous:** [14 Static & Enums](14-StaticAndEnums.md) · **Next:** [16 Exceptions](16-Exceptions.md)

▶️ `java pkg1core/core16RecordsDemo.java` · `java pkg1core/core17SealedClassesDemo.java`

---

## Records (Java 16+) — data carriers

```java
record Point(int x, int y) {}
```

Compiler auto-generates:
- Constructor
- `x()` and `y()` accessors (not `getX()`)
- `equals`, `hashCode`, `toString`

```java
Point p = new Point(3, 4);
System.out.println(p.x());    // 3
```

### Compact constructor — validation

```java
record User(String email) {
    User {
        if (!email.contains("@")) throw new IllegalArgumentException();
    }
}
```

💡 Use records for DTOs, value objects, pattern matching — not JPA entities without care.

---

## Sealed classes (Java 17+) — controlled hierarchy

```java
sealed interface Expr permits Constant, Add {
    double eval();
}
record Constant(double value) implements Expr {
    public double eval() { return value; }
}
record Add(Expr left, Expr right) implements Expr {
    public double eval() { return left.eval() + right.eval(); }
}
```

Only listed classes can extend/implement. Enables **exhaustive** switch:

```java
double result = switch (expr) {
    case Constant c -> c.value();
    case Add a      -> a.left().eval() + a.right().eval();
};
```

---

## Why sealed + records together?

Modern Java algebraic data types — model closed sets of variants (expressions, AST nodes, payment types) with compile-time safety.

**Milestone:** You completed **OOP** (chapters 10–15). 🎉

**Next →** [16 Exceptions](16-Exceptions.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

