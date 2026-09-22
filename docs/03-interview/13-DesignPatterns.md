# Design Patterns (GoF) — Interview Questions (80+)

See runnable examples in [`pkg8patterns`](../../pkg8patterns).  
For **SOLID and OOP design principles**, see [02-OopAndSolid.md](02-OopAndSolid.md).

## Detailed Questions

### 1. What are the categories of GoF patterns?
- **Short:** Creational, Structural, Behavioral.
- **Detailed:** Creational (object creation: Singleton, Factory Method, Abstract Factory, Builder, Prototype), Structural (composition: Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy), Behavioral (interaction: Chain, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor).
- **Example:** Builder (creational), Decorator (structural), Strategy (behavioral).

### 2. Where does SOLID fit vs GoF patterns?
- **Short:** SOLID are principles; GoF patterns are reusable solutions to recurring design problems.
- **Detailed:** SOLID guides *whether* a design is maintainable; patterns show *how* to structure code (e.g. Strategy supports OCP). See [02-OopAndSolid.md](02-OopAndSolid.md) for SRP/OCP/LSP/ISP/DIP depth.
- **Example:** Extract payment algorithms into Strategy beans instead of growing if-else (OCP + Strategy).

### 3. Singleton: how to implement correctly?
- **Short:** Enum or static holder idiom.
- **Detailed:** Enum singletons are thread-safe and serialization/reflection-safe. The holder idiom gives lazy thread-safe init without locking. Double-checked locking needs a `volatile` field.
- **Example:** `enum Config { INSTANCE; }`.

### 4. Factory Method vs Abstract Factory?
- **Short:** One product via subclass vs families of products.
- **Detailed:** Factory Method defers instantiation of ONE product to subclasses. Abstract Factory creates FAMILIES of related products through one interface (e.g., a whole UI toolkit).
- **Example:** `createButton()` (factory method) vs `GuiFactory{button(); checkbox();}`.

### 5. Strategy vs State?
- **Short:** Interchangeable algorithms vs behavior tied to internal state with transitions.
- **Detailed:** Strategy is chosen by the client and usually stateless/independent. State changes the object's behavior as it transitions between states (states often trigger transitions).
- **Example:** Strategy: sorting order; State: traffic light cycle.

### 6. Decorator vs Inheritance?
- **Short:** Add behavior dynamically vs statically.
- **Detailed:** Decorator composes wrappers at runtime, avoiding a combinatorial explosion of subclasses and allowing feature stacking in any order.
- **Example:** `new Sugar(new Milk(new Espresso()))`.

### 7. Adapter vs Facade vs Proxy?
- **Short:** Convert interface vs simplify subsystem vs control access.
- **Detailed:** Adapter changes an interface to one the client expects. Facade provides a simpler unified API over a complex subsystem. Proxy keeps the same interface but controls access (lazy/security/caching/remoting).
- **Example:** `InputStreamReader` (adapter), `Computer.start()` (facade), lazy-loading proxy.

### 8. Observer pattern and its pitfalls?
- **Short:** One-to-many notifications; watch leaks and ordering.
- **Detailed:** Subjects notify subscribers on change. Pitfalls: memory leaks from forgotten unsubscribes, undefined notification order, and cascading updates. Modern: listeners, reactive streams.
- **Example:** UI event listeners.

---

## Rapid-Fire (Q → A)

1. Singleton intent? → One instance, global access.
2. Best singleton? → Enum.
3. Lazy singleton idiom? → Static holder.
4. DCL needs? → volatile.
5. Factory method intent? → Subclass picks class.
6. Abstract factory intent? → Families of objects.
7. Builder intent? → Step-by-step complex objects.
8. Builder real-world? → StringBuilder, HttpRequest.Builder.
9. Prototype intent? → Clone existing.
10. Prototype concern? → Deep vs shallow copy.
11. Adapter intent? → Convert interface.
12. Adapter real-world? → InputStreamReader.
13. Bridge intent? → Decouple abstraction/impl.
14. Bridge real-world? → JDBC.
15. Composite intent? → Tree of uniform objects.
16. Composite real-world? → File system.
17. Decorator intent? → Add behavior dynamically.
18. Decorator real-world? → java.io streams.
19. Facade intent? → Simplify subsystem.
20. Facade real-world? → Service layer.
21. Flyweight intent? → Share to save memory.
22. Flyweight real-world? → Integer cache, String pool.
23. Proxy intent? → Control access.
24. Proxy types? → Virtual, protection, remote, caching.
25. Proxy real-world? → Spring AOP, Hibernate lazy.
26. Chain intent? → Pass request along handlers.
27. Chain real-world? → Servlet filters.
28. Command intent? → Encapsulate request.
29. Command real-world? → Undo, Runnable.
30. Interpreter intent? → Evaluate grammar.
31. Interpreter real-world? → Regex, SQL.
32. Iterator intent? → Sequential access.
33. Iterator real-world? → java.util.Iterator.
34. Mediator intent? → Centralize comms.
35. Mediator real-world? → Chat room, ATC.
36. Memento intent? → Capture/restore state.
37. Memento real-world? → Editor undo.
38. Observer intent? → One-to-many notify.
39. Observer real-world? → Listeners, pub/sub.
40. State intent? → Behavior by state.
41. State real-world? → Order/workflow status.
42. Strategy intent? → Interchangeable algorithms.
43. Strategy real-world? → Comparator.
44. Template method intent? → Algorithm skeleton.
45. Template real-world? → AbstractList, HttpServlet.
46. Visitor intent? → Add ops without changing elements.
47. Visitor real-world? → AST/compiler.
48. Visitor downside? → New element type edits all visitors.
49. DI pattern? → Provide dependencies externally.
50. DI benefit? → Testability, loose coupling.
51. IoC? → Framework controls flow/creation.
52. Service locator vs DI? → Pull vs push dependencies.
53. Repository pattern? → Abstract data access.
54. DAO pattern? → Data access object.
55. DTO pattern? → Data transfer object.
56. MVC? → Model-View-Controller separation.
57. Null Object pattern? → Default no-op instance.
58. Object pool? → Reuse expensive objects.
59. Lazy init? → Defer creation.
60. Fluent interface? → Chained methods.
61. Immutable object pattern? → Final fields, no setters.
62. Double dispatch? → Visitor mechanism.
63. SRP smell? → God class.
64. OCP technique? → Polymorphism/strategy.
65. LSP violation? → Subtype breaks expectations.
66. ISP technique? → Split fat interfaces.
67. DIP technique? → Program to interfaces.
68. DRY? → Don't Repeat Yourself.
69. KISS? → Keep It Simple.
70. YAGNI? → You Aren't Gonna Need It.
71. Composition over inheritance? → More flexible reuse.
72. Law of Demeter? → Talk to friends, not strangers.
73. Anti-pattern: God object? → Too many responsibilities.
74. Anti-pattern: singleton abuse? → Hidden global state.
75. Anti-pattern: premature optimization? → Complexity without need.
76. Pattern for caching? → Proxy/Decorator/Flyweight.
77. Pattern for undo? → Command/Memento.
78. Pattern for plugins? → Strategy/Factory.
79. Pattern for event handling? → Observer.
80. Modern replacement for Visitor? → Sealed types + pattern matching.
81. Lambdas replace which patterns? → Strategy/Command/Observer SAMs.
82. Choosing a pattern rule? → Match the problem; prefer the simplest solution that communicates intent.
