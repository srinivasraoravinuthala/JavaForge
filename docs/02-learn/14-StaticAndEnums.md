# 14 — Static Members & Enums

**Previous:** [13 Abstraction & Interfaces](13-AbstractionAndInterfaces.md) · **Next:** [15 Records & Sealed](15-RecordsAndSealed.md)

▶️ `java pkg1core/core27StaticMembersDemo.java` · `java pkg1core/core15EnumsDemo.java`

---

## Static — belongs to the class

```java
class Counter {
    static int total = 0;     // shared by ALL instances
    int id;

    Counter() { total++; id = total; }
    static int getTotal() { return total; }
}
```

| Static | Instance |
|--------|----------|
| One copy per class | One copy per object |
| Called via `Class.method()` | Called on object |
| Can't use `this` | Has `this` |

---

## Static initializer block

```java
static {
    System.out.println("Class loaded — runs once");
}
```

Runs when the class is first loaded by the JVM.

---

## Static method hiding (not overriding)

```java
class Parent  { static String greet() { return "P"; } }
class Child extends Parent { static String greet() { return "C"; } }

Parent p = new Child();
p.greet();        // "P" — resolved by reference type at compile time
```

---

## Enums — type-safe constants

```java
enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

Day today = Day.MON;
if (today == Day.SAT) { ... }

for (Day d : Day.values()) System.out.println(d);
```

Enums can have **fields, constructors, and methods**:

```java
enum Planet {
    EARTH(5.97e24),
    MARS(6.39e23);
    final double mass;
    Planet(double mass) { this.mass = mass; }
}
```

💡 **Best singleton in Java:** `enum Instance { INSTANCE; }`

**Next →** [15 Records & Sealed](15-RecordsAndSealed.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

