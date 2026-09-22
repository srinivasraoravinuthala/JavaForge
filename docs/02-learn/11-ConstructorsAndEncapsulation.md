# 11 — Constructors & Encapsulation

**Previous:** [10 Classes & Objects](10-ClassesAndObjects.md) · **Next:** [12 Inheritance & Polymorphism](12-InheritanceAndPolymorphism.md)

▶️ `java pkg1core/core26ConstructorsDemo.java` · `java pkg1core/core10Encapsulation.java`

---

## Constructors initialize objects

```java
class Employee {
    final String name;
    final int id;

    Employee(String name, int id) {    // constructor
        this.name = name;
        this.id = id;
    }
}
```

- Same name as class, no return type
- If you write **no** constructor, compiler adds a no-arg default
- Once you define **any** constructor, default is **not** generated

---

## Constructor chaining

```java
Employee() {
    this("Unknown", 0);    // must be first statement
}

Employee(String name, int id) {
    this(name, id, "General");
}

Employee(String name, int id, String dept) {
    this.name = name;
    this.id = id;
}
```

`this(...)` calls another constructor in the same class.  
`super(...)` calls parent constructor (in subclasses).

---

## Encapsulation — hide internal state

```java
class BankAccount {
    private double balance;   // hidden

    public double getBalance() { return balance; }

    public void deposit(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("amt > 0");
        balance += amt;
    }
}
```

| Access | Who can access |
|--------|----------------|
| `private` | Same class only |
| package-private | Same package |
| `protected` | Package + subclasses |
| `public` | Everyone |

💡 **Benefit:** Invariants are protected — callers can't set `balance = -999`.

---

## Immutable objects

Make fields `final`, no setters, defensive copies for mutable components.

```java
record Point(int x, int y) {}   // compiler-generated immutable class
```

**Next →** [12 Inheritance & Polymorphism](12-InheritanceAndPolymorphism.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

