# 10 — Classes & Objects

**Previous:** [09 User Input](09-UserInput.md) · **Next:** [11 Constructors & Encapsulation](11-ConstructorsAndEncapsulation.md)

▶️ `java pkg1core/core25ClassesAndObjectsDemo.java`

---

## Class = blueprint, Object = instance

```java
class Book {
    String title;
    int pages;

    void describe() {
        System.out.println(title + " (" + pages + " pages)");
    }
}

Book a = new Book();   // create object on heap
a.title = "Effective Java";
a.pages = 416;
a.describe();
```

| Term | Meaning |
|------|---------|
| **Class** | Defines fields (state) and methods (behavior) |
| **Object** | A concrete instance created with `new` |
| **Reference** | Variable pointing to an object (`Book a`) |

---

## Memory picture

```
Book a ──────► [ Book object on heap ]
                  title = "Effective Java"
                  pages = 416

Book b ──────► [ different Book object ]
                  title = "Clean Code"
                  pages = 464
```

`a == b` is `false` — different objects, even if fields match.

---

## Fields vs local variables

| | Field (instance) | Local variable |
|---|------------------|----------------|
| Lives | Inside object | Inside method |
| Default | `0`, `null`, `false` | Must assign before use |
| Scope | Whole class (via `this`) | Block only |

---

## `this` keyword

```java
class Person {
    String name;
    Person(String name) {
        this.name = name;   // disambiguate field vs parameter
    }
}
```

---

## Practice

1. Run `core25ClassesAndObjectsDemo`.
2. Create a `Student` class with `name`, `grade`, and `printReport()` method.
3. Create two students and call methods on each.

**Next →** [11 Constructors & Encapsulation](11-ConstructorsAndEncapsulation.md)
**Related →** [01 Core Java](../03-interview/01-CoreJava.md)

