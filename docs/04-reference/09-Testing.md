# Testing (`pkg14testing`)

Maven module demonstrating **framework-agnostic** Java testing.

## Run

```bash
mvn test -f pkg14testing/pom.xml
mvn -q exec:java -f pkg14testing/pom.xml   # overview
```

## What's covered

| Test class | Topic |
|------------|-------|
| `JUnit5BasicsTest` | assertions, lifecycle, `@DisplayName`, `@Disabled` |
| `MockitoDemoTest` | mocks, stubs, `verify` |
| `AssertJDemoTest` | fluent assertions |
| `ParameterizedTestDemo` | `@ParameterizedTest`, `@CsvSource` |
| `TddCalculatorTest` | red-green-refactor TDD |
| `PropertyBasedDemoTest` | jqwik properties |

## Pyramid

- **Unit** (many): fast, isolated — this module
- **Integration**: DB/API with Testcontainers → SpringMastery
- **E2E** (few): full system paths

## Best practices

- One logical assertion per test when possible; name tests as behavior.
- Use `@BeforeEach` for fresh fixtures; avoid shared mutable state.
- Mock **boundaries** (DB, HTTP), not domain logic.
- Prefer AssertJ over raw `assertEquals` for readability.
- Property-based tests catch edge cases unit tests miss.
