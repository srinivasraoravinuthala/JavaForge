# 38 — Metaprogramming

**Previous:** [37 Interview Prep](37-InterviewPrep.md) · **Next:** [39 Resilience Patterns](39-ResiliencePatterns.md)

▶️ `java pkg17metaprogramming/metaprogramming1DynamicProxy.java` · `metaprogramming5AnnotationProcessor.java`

---

## What is metaprogramming?

Code that inspects or generates other code at **compile time** or **runtime** — the foundation of frameworks like Spring, Hibernate, and Mockito.

| Technique | When | Use case |
|-----------|------|----------|
| Reflection | Runtime | Inspect classes, invoke methods by name |
| Dynamic proxy | Runtime | Wrap interfaces (logging, transactions) |
| MethodHandles | Runtime | Faster than raw reflection (Java 7+) |
| VarHandles | Runtime | Atomic field access (Java 9+) |
| Annotation processing | Compile time | Generate boilerplate (Lombok-style) |

---

## Dynamic proxies

`java.lang.reflect.Proxy` creates an object implementing your interface; an `InvocationHandler` receives every method call — used by Spring AOP and mock frameworks.

Run: `java pkg17metaprogramming/metaprogramming1DynamicProxy.java`

---

## MethodHandles & VarHandles

Prefer **MethodHandles** over classic reflection for better performance and type safety. **VarHandles** replace some `sun.misc.Unsafe` patterns for atomic operations on fields.

---

## Annotation processing

`javax.annotation.processing` runs at compile time to generate source files from annotations — how `@Entity`, `@Autowired`, and Lombok `@Getter` work under the hood.

**Full guide →** [Reflection & Annotations](../03-interview/12-ReflectionAndAnnotations.md)

**Interview drill →** [12 Reflection & Annotations](../03-interview/12-ReflectionAndAnnotations.md)

**Next →** [39 Resilience Patterns](39-ResiliencePatterns.md)
