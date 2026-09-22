# 12 — Inheritance & Polymorphism

**Previous:** [11 Constructors & Encapsulation](11-ConstructorsAndEncapsulation.md) · **Next:** [13 Abstraction & Interfaces](13-AbstractionAndInterfaces.md)

▶️ `java pkg1core/core11Inheritance.java` · `java pkg1core/core12Polymorphism.java`

---

## Inheritance · is-a relationship

```java
class Animal {
    void speak() { System.out.println("..."); }
}

class Dog extends Animal {
    @Override
    void speak() { System.out.println("Woof!"); }
}
```

- `extends` for classes (single inheritance only)
- Child inherits fields and methods
- `@Override` recommended · compiler catches typos

---

## `super` keyword

```java
class Dog extends Animal {
    Dog(String name) {
        super();              // call parent constructor (must be first)
    }
    @Override
    void speak() {
        super.speak();        // call parent version
        System.out.println("Dog done");
    }
}
```

---

## Polymorphism · one interface, many forms

```java
Animal a = new Dog();    // upcasting · always safe
a.speak();               // prints "Woof!" · runtime dispatch
```

▶️ **Dynamic dispatch:** JVM calls the **actual object's** method, not the reference type.

---

## Overloading vs overriding

| | Overloading | Overriding |
|---|-------------|------------|
| When resolved | Compile time | Runtime |
| Signature | Same name, different params | Same name + params |
| Where | Same class | Subclass |

---

## When NOT to inherit

▶️ Don't inherit just to reuse code · use **composition** (`has-a`).

Bad: `class Stack extends ArrayList`  
Good: `class Stack { private Deque<T> data; }`

**Related →** [03-interview/02-OopAndSolid.md](../03-interview/02-OopAndSolid.md)

**Related →** [01 Core Java](../03-interview/01-CoreJava.md)


**Next →** [13 Abstraction & Interfaces](13-AbstractionAndInterfaces.md)
