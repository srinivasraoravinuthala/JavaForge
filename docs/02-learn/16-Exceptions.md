# 16 — Exceptions

**Previous:** [15 Records & Sealed](15-RecordsAndSealed.md) · **Next:** [17 Collections](17-Collections.md)

▶️ `java pkg1core/core18ExceptionsDemo.java`

---

## Hierarchy

```
Throwable
├── Error          (OutOfMemoryError — don't catch)
└── Exception
    ├── RuntimeException   (unchecked: NPE, IAE)
    └── IOException etc.   (checked: must handle)
```

---

## try / catch / finally

```java
try {
    int[] a = new int[2];
    System.out.println(a[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Caught: " + e.getMessage());
} finally {
    System.out.println("Always runs");
}
```

---

## try-with-resources (Java 7+)

```java
try (BufferedReader br = Files.newBufferedReader(path)) {
    return br.readLine();
}   // br.close() called automatically
```

Always use for files, streams, connections.

---

## throw and throws

```java
void withdraw(double balance, double amt) throws InsufficientFundsException {
    if (amt > balance) throw new InsufficientFundsException("short " + amt);
}
```

- `throw` — raise exception now
- `throws` — declare checked exceptions on method

---

## Custom exceptions

```java
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String msg) { super(msg); }
}
```

Extend `Exception` for checked, `RuntimeException` for unchecked.

---

## Best practices

| Do | Don't |
|----|-------|
| Catch specific types | `catch (Exception e) {}` empty |
| Chain cause: `new X("msg", cause)` | Swallow exceptions |
| Use try-with-resources | Forget to close files |
| Fail fast with clear messages | Use exceptions for normal flow |

**Interview drill →** [07 Exceptions](../03-interview/07-Exceptions.md)

**Next →** [17 Collections](17-Collections.md)
