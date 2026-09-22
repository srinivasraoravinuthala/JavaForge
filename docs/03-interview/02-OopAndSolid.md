# OOP & SOLID Design — Interview Questions (95+)

See runnable OOP examples in [`pkg1core`](../../pkg1core) (`core10`–`core14`, `core25`–`core28`).

---

## Detailed Questions

### 1. What are the four pillars of OOP?
- **Short:** Encapsulation, inheritance, polymorphism, abstraction.
- **Detailed:** Encapsulation hides state behind methods. Inheritance reuses and specializes behavior. Polymorphism lets one interface invoke different implementations at runtime. Abstraction exposes essentials and hides complexity (abstract classes/interfaces).
- **Example:** `List` interface (abstraction) with `ArrayList`/`LinkedList` (polymorphism).

### 2. Explain SOLID in one sentence each.
- **Short:** SRP, OCP, LSP, ISP, DIP — one reason to change; open for extension; substitutable subtypes; small interfaces; depend on abstractions.
- **Detailed:** **SRP:** a class should have one responsibility. **OCP:** extend via new code, not modifying existing. **LSP:** subtypes must honor the parent's contract. **ISP:** clients shouldn't depend on methods they don't use. **DIP:** high-level modules depend on abstractions, not concretions.
- **Example:** Inject `PaymentGateway` interface (DIP) instead of `StripeClient`.

### 3. SRP violation — how to spot and fix?
- **Short:** Class changes for unrelated reasons (e.g. `User` saves to DB and sends email).
- **Detailed:** God classes mix persistence, validation, UI, and messaging. Split into `User`, `UserRepository`, `EmailService`. Each has one reason to change.
- **Example:** `OrderService` orchestrates; `OrderValidator`, `OrderRepository`, `PaymentClient` do one job each.

### 4. OCP in practice — strategy vs if-else?
- **Short:** Replace growing if/switch chains with polymorphism or strategy.
- **Detailed:** Every new discount type shouldn't require editing a central `calculate()` method. Register strategies (`DiscountPolicy`) and select at runtime. New types = new class, zero edits to core logic.
- **Example:** `Map<String, DiscountPolicy>` or Spring beans keyed by type.

### 5. LSP violation example?
- **Short:** Subclass breaks expectations of the parent (e.g. `Square` extends `Rectangle` with coupled width/height).
- **Detailed:** If code expects `setWidth` and `setHeight` independently but `Square` forces equality, callers break. Prefer composition or separate types over inheritance that narrows behavior.
- **Example:** Classic `Rectangle`/`Square` problem; `Penguin extends Bird` with `fly()`.

### 6. ISP — fat interface smell?
- **Short:** One interface with many methods forces empty/stub implementations.
- **Detailed:** Split `Worker` with `work/eat/sleep` into `Workable`, `Eatable` so `Robot` only implements `Workable`. Java 8+ default methods can help but don't replace thoughtful segregation.
- **Example:** `Servlet`-style fat interfaces vs role-specific ones.

### 7. DIP — how does Spring embody it?
- **Short:** Constructor injection of interfaces; framework wires implementations.
- **Detailed:** Application code depends on `UserRepository`; `@Autowired` or constructor provides `JpaUserRepository`. Tests swap in `InMemoryUserRepository`. Inversion: framework injects dependencies you don't construct.
- **Example:** `@Service` depends on `Repository` interface, not `EntityManager` directly.

### 8. Composition over inheritance — when and why?
- **Short:** Favor has-a over is-a to avoid fragile base classes and deep hierarchies.
- **Detailed:** Inheritance exposes subclass to parent implementation changes. Composition delegates behavior (`Engine` inside `Car`) and allows runtime swapping (`new ElectricEngine()`).
- **Example:** `Stack` wrapping `Deque` instead of extending `Vector`.

### 9. Coupling vs cohesion?
- **Short:** Low coupling (minimal dependencies); high cohesion (related work grouped).
- **Detailed:** Tight coupling to concrete classes, static singletons, and shared mutable state makes change risky. Cohesive classes do one domain concept well.
- **Example:** Service calling repository interface = loose coupling; `DateUtils` with only date helpers = high cohesion.

### 10. Law of Demeter (principle of least knowledge)?
- **Short:** Don't call methods on objects returned by other methods — talk to immediate friends only.
- **Detailed:** `order.getCustomer().getAddress().getZip()` chains knowledge across layers. Expose `order.getShippingZip()` instead. Reduces ripple effects when inner objects change.
- **Example:** Facade methods on aggregate roots.

### 11. DDD building blocks — entity vs value object?
- **Short:** Entity has identity; value object is defined by its attributes (immutable).
- **Detailed:** `OrderId` distinguishes orders; `Money` with amount+currency is interchangeable if values match. Value objects should be immutable and compared by value.
- **Example:** Java `record` for value objects; JPA `@Embedded` for `Address`.

### 12. Tell, Don't Ask?
- **Short:** Tell objects to do work; don't pull data out and decide externally.
- **Detailed:** `if (account.getBalance() >= amount) account.withdraw(amount)` leaks logic. Prefer `account.withdraw(amount)` which enforces rules internally.
- **Example:** Rich domain model vs anemic model with service doing all logic.

### 13. Immutability as a design choice?
- **Short:** Immutable objects are thread-safe and easier to reason about.
- **Detailed:** No setters; defensive copies on getters; `final` fields; factory methods. Trade-off: creating new instances vs synchronizing mutations.
- **Example:** `String`, `Integer`, `record` with no mutable components.

### 14. Package-private and module boundaries?
- **Short:** Use visibility to enforce encapsulation at package/module level.
- **Detailed:** Public API surface should be minimal. JPMS `exports`/`opens` control what other modules see. Don't expose internals "for tests" via public setters.
- **Example:** `com.app.api` exports interfaces; `com.app.internal` does not.

### 15. Anti-corruption layer?
- **Short:** Translate external model to your domain at the boundary.
- **Detailed:** When integrating legacy or third-party APIs, don't let their types leak everywhere. Adapter/facade maps DTOs to domain objects once at the edge.
- **Example:** `LegacyBillingAdapter` implements your `BillingPort`.

---

## Rapid-Fire (Q → A)

1. Four OOP pillars? → Encapsulation, inheritance, polymorphism, abstraction.
2. SRP? → One reason to change.
3. OCP? → Open extension, closed modification.
4. LSP? → Subtypes substitutable for base.
5. ISP? → Small, focused interfaces.
6. DIP? → Depend on abstractions.
7. God class? → Too many responsibilities.
8. Anemic domain model? → Data classes + logic in services (anti-pattern for DDD).
9. Rich domain model? → Behavior on domain objects.
10. Composition over inheritance? → Has-a preferred over is-a.
11. Fragile base class? → Parent change breaks children.
12. Favor delegation? → Wrap and forward calls.
13. Encapsulation benefit? → Protect invariants.
14. Polymorphism mechanism? → Dynamic dispatch on overridden methods.
15. Overloading vs overriding? → Compile-time vs runtime.
16. Upcasting safe? → Yes, implicit to supertype.
17. Downcasting risk? → ClassCastException.
18. instanceof before cast? → Safe pattern.
19. Abstract class vs interface for shared state? → Abstract class.
20. Multiple inheritance of type? → Interfaces only.
21. Diamond problem in Java? → Resolved by most specific default method override.
22. Default method purpose? → Evolve interfaces without breaking impls.
23. Static methods in interfaces? → Utility, not inherited.
24. Private methods in interfaces? → Shared code for defaults (Java 9+).
25. Sealed classes purpose? → Controlled inheritance hierarchy.
26. Non-sealed permits? → Allow unknown subclasses outside module.
27. Record purpose? → Immutable data carrier.
28. Record vs class for DTO? → Record when data-only, no identity.
29. Enum for type-safe constants? → Yes; can have behavior.
30. Tell Don't Ask example? → `order.ship()` not `if (order.canShip())`.
31. Law of Demeter violation? → Long getter chains.
32. Facade pattern role? → Simplify subsystem API.
33. Adapter at boundary? → Convert foreign interface.
34. Low coupling benefit? → Easier change/test.
35. High cohesion benefit? → Clear purpose per class.
36. Dependency injection types? → Constructor (preferred), setter, field.
37. Constructor injection why preferred? → Immutable, explicit, testable.
38. Service locator vs DI? → Locator hides dependencies (anti-pattern).
39. Interface segregation example? → `Printer` vs `Scanner` not one `Machine`.
40. Open-closed with Strategy? → New strategy class, no edit to context.
41. Template Method in OOP? → Base defines skeleton, subclasses fill steps.
42. Hollywood Principle? → Don't call us, we'll call you (IoC).
43. Inversion of Control? → Framework controls flow/callbacks.
44. Aggregate root? → DDD entry point for consistency boundary.
45. Value object equality? → By all fields (record auto).
46. Entity equality? → Usually by ID.
47. Side-effect-free functions? → Easier to test and compose.
48. Pure function? → Same input → same output, no side effects.
49. YAGNI? → You aren't gonna need it.
50. KISS? → Keep it simple.
51. DRY? → Don't repeat yourself (but not at cost of coupling).
52. Premature abstraction cost? → Wrong abstraction harder than duplication.
53. Leaky abstraction? → Exposes implementation details.
54. Design by contract? → Preconditions, postconditions, invariants.
55. Defensive copying? → Return copies of internal mutable state.
56. Fail-fast validation? → Reject invalid state at construction.
57. Null object pattern? → No-op impl instead of null checks.
58. Specification pattern? → Composable business rules.
59. Factory when? → Hide construction complexity/variants.
60. Builder when? → Many optional constructor params.
61. Prototype when? → Clone expensive-to-build objects.
62. Bridge pattern? → Decouple abstraction from implementation.
63. Decorator vs subclass? → Runtime stacking vs static hierarchy.
64. Proxy for cross-cutting? → Security, lazy load, logging.
65. Observer decoupling? → Subject doesn't know concrete observers.
66. MVC layering? → Model-View-Controller separation.
67. Hexagonal architecture? → Ports and adapters.
68. Clean architecture layers? → Entities → use cases → adapters.
69. Bounded context? → DDD module with own ubiquitous language.
70. Ubiquitous language? → Shared terms dev + domain experts.
71. Anti-corruption layer? → Translate legacy at boundary.
72. CQRS? → Separate read/write models.
73. Event sourcing? → State as event log.
74. Idempotent operations? → Same call safe to repeat.
75. Backward compatibility? → Don't break clients on API change.
76. Semantic versioning? → MAJOR.MINOR.PATCH.
77. Breaking change example? → Remove public method.
78. Deprecation strategy? → `@Deprecated(forRemoval=true)` + migration path.
79. Testability and DIP? → Mock interfaces in unit tests.
80. SOLID + patterns relation? → Patterns often implement SOLID goals.
