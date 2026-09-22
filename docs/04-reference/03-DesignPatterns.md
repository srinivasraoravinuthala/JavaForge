# Design Patterns (GoF)

Runnable examples: [`pkg8patterns`](../../pkg8patterns). Each file documents **intent, UML, pros, cons, and a real-world use**.

> Tutorial chapter: [02-learn/27-DesignPatterns.md](../02-learn/27-DesignPatterns.md) · SOLID depth: [03-interview/02-OopAndSolid.md](../03-interview/02-OopAndSolid.md)

## SOLID (the foundation patterns build on)
- **S**ingle Responsibility — one reason to change.
- **O**pen/Closed — open for extension, closed for modification.
- **L**iskov Substitution — subtypes must be usable as their base type.
- **I**nterface Segregation — many small interfaces > one fat interface.
- **D**ependency Inversion — depend on abstractions, not concretions.

## Creational (object creation)
| Pattern | Intent | File |
|---------|--------|------|
| Singleton | exactly one instance, global access | `creational/SingletonPattern.java` |
| Factory Method | subclass decides which class to instantiate | `creational/FactoryMethodPattern.java` |
| Abstract Factory | create families of related objects | `creational/AbstractFactoryPattern.java` |
| Builder | step-by-step construction of complex objects | `creational/BuilderPattern.java` |
| Prototype | clone existing instances | `creational/PrototypePattern.java` |

## Structural (object composition)
| Pattern | Intent | File |
|---------|--------|------|
| Adapter | convert one interface to another | `structural/AdapterPattern.java` |
| Bridge | decouple abstraction from implementation | `structural/BridgePattern.java` |
| Composite | tree of objects treated uniformly | `structural/CompositePattern.java` |
| Decorator | add behavior dynamically | `structural/DecoratorPattern.java` |
| Facade | simple interface to a complex subsystem | `structural/FacadePattern.java` |
| Flyweight | share objects to save memory | `structural/FlyweightPattern.java` |
| Proxy | control access to an object | `structural/ProxyPattern.java` |

## Behavioral (object interaction)
| Pattern | Intent | File |
|---------|--------|------|
| Chain of Responsibility | pass request along handlers | `behavioral/ChainOfResponsibilityPattern.java` |
| Command | encapsulate a request (undo/redo) | `behavioral/CommandPattern.java` |
| Interpreter | evaluate sentences in a language | `behavioral/InterpreterPattern.java` |
| Iterator | sequential access without exposing internals | `behavioral/IteratorPattern.java` |
| Mediator | centralize complex communication | `behavioral/MediatorPattern.java` |
| Memento | capture/restore state (undo) | `behavioral/MementoPattern.java` |
| Observer | one-to-many change notification | `behavioral/ObserverPattern.java` |
| State | behavior changes with internal state | `behavioral/StatePattern.java` |
| Strategy | interchangeable algorithms | `behavioral/StrategyPattern.java` |
| Template Method | algorithm skeleton, variable steps | `behavioral/TemplateMethodPattern.java` |
| Visitor | add operations without changing elements | `behavioral/VisitorPattern.java` |

## How to choose
- Need one instance? **Singleton**. Many optional params? **Builder**.
- Swap algorithms at runtime? **Strategy**. React to changes? **Observer**.
- Add behavior without subclassing? **Decorator**. Simplify a subsystem? **Facade**.
- Control access/lazy load? **Proxy**. Tree structures? **Composite**.
- Undo/redo? **Command** or **Memento**. State machine? **State**.

## Modern Java note
Records + sealed types + pattern matching (Java 17–21) often replace classic
**Visitor** and some **Strategy/State** boilerplate. Lambdas replace single-method
Strategy/Command/Observer implementations. Prefer the simplest tool that communicates intent.

## Anti-patterns to avoid
God object, singleton overuse (hidden global state), premature abstraction,
deep inheritance, and "pattern for pattern's sake". Patterns are vocabulary, not goals.
