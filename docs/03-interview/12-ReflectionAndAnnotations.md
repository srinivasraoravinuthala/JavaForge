# Reflection & Annotations — Interview Questions (72+)

See [`pkg17metaprogramming`](../../pkg17metaprogramming).

---

## Detailed Questions

### 1. What is reflection?
- **Short:** Inspect and invoke classes/methods/fields at runtime via `java.lang.reflect`.
- **Detailed:** `Class.forName`, `getDeclaredMethods`, `setAccessible(true)`, `Method.invoke`. Used by frameworks (Spring, Hibernate, Jackson) for dependency injection, ORM mapping, JSON binding. Bypasses compile-time checks.
- **Example:** `obj.getClass().getMethod("foo", int.class).invoke(obj, 42);`

### 2. How do you obtain a `Class` object?
- **Short:** `.class`, `obj.getClass()`, `Class.forName(name)`.
- **Detailed:** Primitives have `int.class`. `forName` loads class (runs static init). Class objects are singletons per loader.
- **Example:** `String.class == "hi".getClass()`.

### 3. getMethod vs getDeclaredMethod?
- **Short:** `getMethod` includes inherited public; `getDeclared` all declared in class (any visibility).
- **Detailed:** For private methods use `getDeclaredMethod` + `setAccessible(true)`. `getMethods` returns only public including inherited.
- **Example:** Access private field in test via reflection (careful in production).

### 4. setAccessible and module system?
- **Short:** Java 9+ strong encapsulation; `--add-opens` may be required for JDK internals.
- **Detailed:** Deep reflection on non-exported packages illegal by default. Frameworks use `opens` in module-info or JVM flags. Prefer public API / MethodHandles over breaking encapsulation.
- **Example:** Hibernate opens entity packages for bytecode access.

### 5. Annotations — retention and target?
- **Short:** `@Retention(RUNTIME|CLASS|SOURCE)` and `@Target` (TYPE, METHOD, FIELD…).
- **Detailed:** SOURCE = compile-time only (e.g. `@Override`). CLASS = bytecode, not runtime. RUNTIME = visible to reflection (`@Entity`, `@Autowired`). `@Inherited` on class annotations propagates to subclasses.
- **Example:** `@Retention(RetentionPolicy.RUNTIME) @interface MyTag {}`

### 6. Annotation processing — compile-time vs runtime?
- **Short:** APT generates code at compile (Lombok, MapStruct); runtime reflection reads annotations.
- **Detailed:** JSR 269 annotation processors inspect AST during `javac`. Cleaner than runtime reflection for codegen. MapStruct generates mapper impls at compile time.
- **Example:** `@Mapper` interface → generated `UserMapperImpl`.

### 7. MethodHandles vs reflection?
- **Short:** MethodHandles are JVM-aware, faster, type-safe; preferred for repeated invocation.
- **Detailed:** `MethodHandles.lookup().findVirtual(...)`. `LambdaMetafactory` uses handles for lambdas. Reflection easier for ad-hoc tools; handles for performance-critical paths.
- **Example:** `metaprogramming5ReflectionVsHandles.java`.

### 8. Dynamic proxies?
- **Short:** `Proxy.newProxyInstance` creates interface impl at runtime invoking `InvocationHandler`.
- **Detailed:** Only interfaces, not classes (use ByteBuddy/CGLIB for classes). Used for mocking, transactions (`@Transactional`), logging.
- **Example:** `metaprogramming1DynamicProxy.java`.

### 9. VarHandles (Java 9+)?
- **Short:** Typed, atomic field/array access without boxing.
- **Detailed:** Replacement for some `sun.misc.Unsafe` uses. Support compare-and-set on fields. Used in concurrent collections internals.
- **Example:** Low-level lock-free structures.

### 10. Common annotation examples in enterprise?
- **Short:** `@Override`, `@Deprecated`, `@SuppressWarnings`, `@FunctionalInterface`, JPA `@Entity`, Spring `@Component`.
- **Detailed:** JPA maps object model to tables. Spring stereotypes (`@Service`, `@Repository`) enable component scan. Jackson `@JsonProperty` controls JSON.
- **Example:** Stereotype meta-annotated with `@Component`.

### 11. Repeatable annotations?
- **Short:** `@Repeatable` wraps multiple same annotation in container.
- **Detailed:** `@Schedules({@Schedule(...), @Schedule(...)})` or repeated form Java 8+. Container annotation holds array.
- **Example:** Multiple `@AttributeOverride` in JPA.

### 12. Reflection performance cost?
- **Short:** First invoke slow (security checks); cache `Method`/`Field` objects; prefer handles or codegen.
- **Detailed:** `setAccessible` reduces checks. Frameworks cache metadata at startup. Don't reflect in tight loops without caching.
- **Example:** Spring caches bean metadata at context refresh.

---

## Rapid-Fire (Q → A)

1. Reflect private field? → getDeclaredField + setAccessible.
2. Field.get object arg? → Instance field needs target; static null.
3. invoke static method? → null as first arg to invoke.
4. Constructor.newInstance? → Creates instance; exception wrapper.
5. getConstructors vs declared? → Public only vs all.
6. isAssignableFrom? → Subtype check.
7. cast with Class? → class.cast(obj).
8. instanceof with Class? → class.isInstance(obj).
9. Array reflection? → Array.newInstance(component, len).
10. Generic type erasure reflection? → Type interface for ParameterizedType.
11. getGenericSuperclass? → For type tokens.
12. Annotation on parameter? → getParameterAnnotations.
13. Default annotation values? → annotation.defaultMember().
14. Marker annotation? → No methods (@Deprecated style).
15. Single-value annotation? → value() shorthand.
16. @Documented? → Appears in javadoc.
17. @Inherited class only? → Yes, not methods.
18. @Repeatable container? → Holds annotation array.
19. @Native annotation? → Native method marker.
20. @SafeVarargs on? → Private/static/final varargs only.
21. FunctionalInterface check? → Compiler + runtime optional.
22. Proxy requires? → Interfaces only.
23. InvocationHandler? → invoke(proxy, method, args).
24. CGLIB proxy? → Subclass-based (Spring).
25. ByteBuddy? → Modern bytecode generation.
26. ASM library? → Low-level bytecode.
27. javassist? → Bytecode editing.
28. ClassLoader loadClass? → vs Class.forName static init.
29. defineClass? → Custom class loaders.
30. SPI ServiceLoader? → META-INF/services.
31. double-checked reflection? → Cache lookup + Method object.
32. SecurityManager reflection? → Deprecated/removed modern JDK.
33. InaccessibleObjectException? → Module blocks access.
34. --add-opens syntax? → --add-opens module/pkg=target-module.
35. records reflection? → isRecord, getRecordComponents.
36. sealed permits reflection? → getPermittedSubclasses.
37. enum reflection? → getEnumConstants.
38. AnnotationMirror compile? → Processor API.
39. Element vs TypeElement? → AST model.
40. Filer in processor? → Generate source files.
41. Messager printMessage? → Compiler errors/warnings.
42. Lombok how works? → Annotation processor codegen.
43. MapStruct how? → Processor generates mappers.
44. Hibernate bytecode? → ByteBuddy enhancement.
45. Jackson annotations runtime? → RUNTIME retention.
46. JAX-RS annotations? → Runtime for REST mapping.
47. Validation @NotNull? → Bean Validation runtime.
48. ConstraintValidator? → Custom validation logic.
49. CDI @Inject? → Jakarta EE DI.
50. Qualifier annotation? → Disambiguate beans.
51. @Primary Spring? → Default bean choice.
52. @ConditionalOnProperty? → Boot auto-config condition.
53. Meta-annotation? → Annotation on annotation.
54. @AliasFor? → Attribute alias in Spring.
55. Kotlin reflection separate? → kotlin.reflect.
56. GraalVM native reflection? → Registration config needed.
57. reachability metadata? → Native image reflect config.
58. serialization reflect? → Reflective access to private ctor.
59. Unsafe status? → Internal; use VarHandle/Foreign API.
60. Foreign Function API? → Panama native interop.
