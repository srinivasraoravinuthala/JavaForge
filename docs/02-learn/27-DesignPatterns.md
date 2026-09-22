# 27 — Design Patterns

**Previous:** [26 Concurrency](26-Concurrency.md) · **Next:** [28 I/O & NIO](28-IOAndNIO.md)

▶️ `pkg8patterns/patterns1SingletonPattern.java` → `patterns23VisitorPattern.java`

---

## GoF categories

| Category | Purpose | Examples |
|----------|---------|----------|
| **Creational** | Object creation | Singleton, Factory, Builder, Prototype |
| **Structural** | Composition | Adapter, Decorator, Facade, Proxy |
| **Behavioral** | Communication | Strategy, Observer, Command, Template Method |

---

## Must-know patterns (interview)

| Pattern | One-line | When |
|---------|----------|------|
| **Singleton** | One instance | Config, connection pool |
| **Factory** | Delegate creation | Hide concrete types |
| **Builder** | Step-by-step construction | Many optional params |
| **Strategy** | Swappable algorithms | Payment methods, sorting |
| **Observer** | Publish/subscribe | Event listeners |
| **Decorator** | Add behavior dynamically | Java I/O streams |
| **Proxy** | Control access | Lazy load, security, caching |
| **Template Method** | Fixed skeleton, variable steps | Abstract `process()` |

---

## Study method

For each of the 23 patterns in `pkg8patterns`:
1. Run the demo
2. Draw a class diagram on paper
3. Name a real framework use (Spring AOP = Proxy, `InputStreamReader` = Adapter)
4. State trade-offs

**References →** [04-reference/03-DesignPatterns.md](../04-reference/03-DesignPatterns.md) · [03-interview/02-OopAndSolid.md](../03-interview/02-OopAndSolid.md) · [03-interview/13-DesignPatterns.md](../03-interview/13-DesignPatterns.md)

**Next →** [28 I/O & NIO](28-IOAndNIO.md)
