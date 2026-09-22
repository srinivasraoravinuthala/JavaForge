# 33 → Testing

**Previous:** [32 Standard Libraries](32-StandardLibraries.md) → **Next:** [34 Modules](34-Modules.md)

▶️ `mvn test -f pkg14testing/pom.xml`

---

## Why test?

| Type | Purpose |
|------|---------|
| **Unit** | One class/method in isolation |
| **Integration** | Components together (DB, HTTP) |
| **Property-based** | Random inputs find edge cases |

---

## Stack in this project

| Tool | Role |
|------|------|
| **JUnit 5** | Test runner, assertions |
| **Mockito** | Mock dependencies |
| **AssertJ** | Fluent assertions |
| **jqwik** | Property-based testing |

```java
@Test
void add_twoPlusThree_isFive() {
    assertEquals(5, calculator.add(2, 3));
}

@Test
void findUser_notFound_returnsEmpty() {
    when(repo.findById(99)).thenReturn(Optional.empty());
    assertThat(service.find(99)).isEmpty();
}
```

**Related →** [Testing.md](../04-reference/09-Testing.md)

**Related →** [14 Effective Java](../03-interview/14-EffectiveJava.md)


**Next →** [34 Modules](34-Modules.md)
