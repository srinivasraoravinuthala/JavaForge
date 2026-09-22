# 13 — Abstraction & Interfaces

**Previous:** [12 Inheritance & Polymorphism](12-InheritanceAndPolymorphism.md) · **Next:** [14 Static & Enums](14-StaticAndEnums.md)

▶️ `java pkg1core/core13AbstractionDemo.java` · `java pkg1core/core14InterfacesDemo.java`

---

## Abstract classes — partial implementation

```java
abstract class Payment {
    protected final double amount;
    Payment(double amount) { this.amount = amount; }

    abstract String method();      // subclass MUST implement
    abstract void authorize();

    final void process() {         // template method — fixed algorithm
        validate();
        authorize();
        System.out.println("Charged " + amount);
    }
    void validate() {
        if (amount <= 0) throw new IllegalArgumentException();
    }
}
```

Use when subclasses share **state and concrete behavior**.

---

## Interfaces — contracts

```java
interface Drawable {
    void draw();                   // implicitly public abstract
    default void highlight() {     // Java 8+ — optional impl
        System.out.println("highlight");
    }
    static void info() {           // utility, not inherited
        System.out.println("Drawable v1");
    }
}
```

- A class **implements** one or more interfaces
- Interface = **can-do** capability (`Comparable`, `Runnable`, `Serializable`)

---

## Abstract class vs interface

| | Abstract class | Interface |
|---|----------------|-----------|
| Inheritance | `extends` (one) | `implements` (many) |
| State | Can have fields | Only constants (before records) |
| Use when | Shared base + partial impl | Capability / multiple roles |

---

## Multiple interfaces

```java
class Button implements Drawable, Clickable, Serializable { ... }
```

Java supports **multiple inheritance of type** through interfaces.

**Next →** [14 Static & Enums](14-StaticAndEnums.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

