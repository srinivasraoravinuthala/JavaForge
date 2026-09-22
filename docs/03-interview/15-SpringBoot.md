# Spring & Spring Boot — Interview Questions (92+)

Conceptual coverage aligned with Spring Framework 6 / Spring Boot 3 (Jakarta EE namespace).

---

## Detailed Questions

### 1. What is the Spring IoC container?
- **Short:** Inversion of Control container that creates, wires, and manages beans.
- **Detailed:** `ApplicationContext` loads bean definitions (annotations, Java config, XML), resolves dependencies via DI, manages lifecycle (`@PostConstruct`, `@PreDestroy`), and provides AOP, events, resource loading.
- **Example:** `@SpringBootApplication` bootstraps `SpringApplication.run()`.

### 2. Dependency Injection types?
- **Short:** Constructor (preferred), setter, field injection.
- **Detailed:** Constructor injection makes dependencies explicit, enables `final` fields, easy unit tests without Spring. Field `@Autowired` is convenient but hides deps and complicates testing. Spring 4.3+ auto-wires single-constructor beans.
- **Example:** `@Service public class OrderService(OrderRepository repo) {}`

### 3. `@Component` vs `@Service` vs `@Repository` vs `@Controller`?
- **Short:** Stereotypes — all `@Component` specializations for semantics and tooling.
- **Detailed:** `@Repository` adds persistence exception translation. `@Controller`/`@RestController` for web. `@Service` marks business layer. Component scan picks all up equally otherwise.
- **Example:** `@RestController` = `@Controller` + `@ResponseBody`.

### 4. Bean scopes?
- **Short:** singleton (default), prototype, request, session, application (web).
- **Detailed:** Singleton one per container; prototype new per injection/getBean. Request/session need web context. Misuse of prototype inside singleton — client gets one prototype instance unless `ObjectProvider` or `@Lookup`.
- **Example:** `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`.

### 5. `@Configuration` and `@Bean`?
- **Short:** Java-based config; `@Bean` methods register objects in context.
- **Detailed:** Full `@Configuration` classes CGLIB-enhanced so `@Bean` method calls return same singleton bean. `@Bean` lite mode without full config may create new instances per call.
- **Example:** `@Bean DataSource dataSource() { return HikariDataSource... }`

### 6. `@Autowired` resolution?
- **Short:** By type; `@Qualifier` or `@Primary` if multiple candidates.
- **Detailed:** `Optional` dependency if zero-or-one. `ObjectProvider<T>` for lazy/optional multiple. Constructor injection fails fast at startup if missing bean (fail-fast wiring).
- **Example:** `@Qualifier("stripe") PaymentGateway gateway`.

### 7. Spring Boot auto-configuration?
- **Short:** Conditional beans based on classpath, properties, existing beans.
- **Detailed:** `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`. `@ConditionalOnClass`, `@ConditionalOnMissingBean`. Starters bundle dependencies + auto-config.
- **Example:** `spring-boot-starter-web` adds Tomcat, Jackson, MVC.

### 8. `application.properties` vs `application.yml`?
- **Short:** Externalized config; YAML hierarchical; profiles `application-dev.yml`.
- **Detailed:** `@ConfigurationProperties` binds prefix to POJO. `@Value` for single keys. Secrets via env vars / vault, not committed files.
- **Example:** `@ConfigurationProperties(prefix="app.mail") record MailProps(String host, int port) {}`

### 9. Spring MVC request flow?
- **Short:** DispatcherServlet → HandlerMapping → Controller → View/ResponseBody → ExceptionHandler.
- **Detailed:** Filters (security) run first. `HandlerAdapter` invokes controller. `HttpMessageConverter` serializes JSON. `@ControllerAdvice` global exception mapping.
- **Example:** `@GetMapping("/users/{id}") User get(@PathVariable Long id)`.

### 10. `@Transactional` how it works?
- **Short:** AOP proxy wraps bean; begins/commits/rolls back transaction per method.
- **Detailed:** Self-invocation bypasses proxy (call `this.save()` — no tx). Rollback on unchecked by default; checked need `rollbackFor`. Propagation `REQUIRED`, `REQUIRES_NEW`, `NESTED` etc. Read-only optimization hint.
- **Example:** `@Transactional(readOnly=true)` on query service.

### 11. Spring Data JPA repository magic?
- **Short:** Interface extends `JpaRepository`; Spring generates implementation at runtime.
- **Detailed:** Query methods parsed from method names (`findByEmailAndActive`). `@Query` for JPQL/native. Pagination via `Pageable`. Custom fragments with `Impl` suffix.
- **Example:** `Optional<User> findByEmail(String email);`

### 12. Spring Security filter chain?
- **Short:** Servlet filters authenticate and authorize before reaching controller.
- **Detailed:** `SecurityFilterChain` bean configures HTTP rules, form/login, JWT resource server, CSRF. Method security `@PreAuthorize` uses AOP after authentication.
- **Example:** `authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll())`.

---

## Rapid-Fire (Q → A)

1. Core Spring modules? → Core, Context, AOP, Data, MVC, Security.
2. Bean lifecycle init? → @PostConstruct, InitializingBean, custom init.
3. Bean destroy? → @PreDestroy, DisposableBean.
4. ApplicationContext vs BeanFactory? → Context superset (events, i18n).
5. Component scan base? → @SpringBootApplication scan attribute.
6. @Import? → Pull config classes into context.
7. @Profile? → Conditional beans on profile.
8. active profile property? → spring.profiles.active.
9. Actuator endpoints? → health, metrics, env (secure them).
10. Starter parent? → Dependency version management.
11. spring.factories legacy? → AutoConfiguration.imports in Boot 3.
12. DevTools? → Restart, livereload dev only.
13. @RestControllerAdvice? → REST exception handler.
14. ResponseEntity? → Status + headers + body control.
15. @Valid? → Bean Validation trigger.
16. @RequestBody? → Deserialize JSON body.
17. @ResponseStatus? → HTTP status on method/exception.
18. Content negotiation? → Accept header / produces.
19. RestTemplate status? → RestClient / WebClient preferred.
20. WebClient reactive? → Non-blocking HTTP client.
21. @Async? → Separate thread pool execution.
22. @Scheduled? → Cron/fixed delay tasks.
23. @Cacheable? → Method result caching.
24. Cache abstraction? → Redis, Caffeine backends.
25. @EventListener? → ApplicationEvent handling.
26. ApplicationEventPublisher? → Publish domain events.
27. Transaction propagation REQUIRED? → Join or create.
28. REQUIRES_NEW? → Suspend and new transaction.
29. NESTED? → Savepoint nested (JDBC).
30. readOnly true benefit? → Flush optimization, routing replica.
31. LazyInitializationException? → Access lazy assoc outside session.
32. Open Session In View? → Controversial; keeps session for view.
33. EntityManager vs Session? → JPA vs Hibernate native.
34. PersistenceContext? → Managed entities scope.
35. Detached entity? → No longer tracked.
36. merge()? → Reattach detached state.
37. flush()? → Sync persistence context to DB.
38. clear()? → Detach all entities.
39. N+1 problem? → Join fetch or @EntityGraph.
40. @EntityGraph? → Specify fetch plan.
41. DTO projection? → Interface/class-based Spring Data.
42. Specification pattern? → Dynamic JPA queries.
43. Criteria API? → Type-safe programmatic queries.
44. Querydsl? → Alternative type-safe queries.
45. Flyway/Liquibase? → Schema migration with Boot.
46. HikariCP default pool? → Spring Boot 2+ default.
47. DataSource auto-config? → If JDBC on classpath.
48. JdbcTemplate? → Simplified JDBC without ORM.
49. RowMapper? → Map ResultSet row to object.
50. NamedParameterJdbcTemplate? → Named params :id.
51. @Mapper MyBatis? → SQL mapper alternative.
52. Spring Test @SpringBootTest? → Full context integration test.
53. @WebMvcTest slice? → MVC layer only mock.
54. @DataJpaTest slice? → JPA + in-memory DB.
55. @MockBean? → Replace bean in test context.
56. Testcontainers? → Real DB in Docker tests.
57. AOP proxy JDK vs CGLIB? → Interface JDK; class CGLIB.
58. @Aspect @Around? → Wrap method proceed().
59. Pointcut expression? → execution, @annotation, etc.
60. Self-invocation AOP fix? → Inject self or separate bean.
61. @Order on filters? → Filter chain ordering.
62. CORS config? → WebMvcConfigurer addCorsMappings.
63. CSRF REST? → Often disabled for stateless JWT APIs.
64. JWT resource server? → oauth2ResourceServer JWT.
65. OAuth2 client? → oauth2Client() for login.
66. Bean Validation @NotBlank? → String not empty trim.
67. @Validated on class? → Method-level validation groups.
68. Configuration metadata JSON? → IDE property hints.
69. ConditionalOnProperty? → Feature flags.
70. Spring Native/AOT? → GraalVM native images constraints.
71. Virtual threads Boot 3.2+? → spring.threads.virtual.enabled.
72. Observability Micrometer? → Metrics tracing logs.
73. @Timed custom metric? → Method timing.
74. Structured logging? → JSON log format.
75. Graceful shutdown? → server.shutdown=graceful.
76. Health groups? → liveness/readiness k8s.
77. Property placeholder? → ${app.name} in config.
78. @PropertySource? → Additional property files.
79. Environment abstraction? → Profiles + property sources.
80. FactoryBean? → Complex bean creation.
