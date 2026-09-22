# Java Modules & SPI (`pkg15modules`)

## JPMS (Java Platform Module System)

Introduced in Java 9. Strong encapsulation: only `exports` packages are accessible to other modules.

### module-info.java keywords

| Keyword | Meaning |
|---------|---------|
| `requires` | dependency on another module |
| `requires transitive` | re-export dependency to consumers |
| `exports` | public API package |
| `opens` | allow deep reflection (frameworks) |
| `provides` / `uses` | SPI registration / consumption |

### Classpath vs module path

- **Classpath** (legacy): unnamed module, all jars visible.
- **Module path**: named modules, explicit dependencies.

```bash
java --module-path mods --module com.myapp/com.myapp.Main
```

## ServiceLoader (SPI)

Discover implementations at runtime without hard-coding classes.

1. Define interface `Encoder`
2. Implement `UpperEncoder`, `ReverseEncoder`
3. Register in `META-INF/services/pkg15modules.spi.Encoder`:
   ```
   pkg15modules.spi.UpperEncoder
   pkg15modules.spi.ReverseEncoder
   ```
4. `ServiceLoader.load(Encoder.class)` returns all providers

**Full demo:** `cd pkg15modules/spi-demo && mvn -q exec:java`

## Files

| File | Topic |
|------|-------|
| `modules1JpmsConcepts` | JPMS vocabulary, module inspection |
| `modules2ServiceLoaderSpi` | SPI pattern (single-file + notes) |
| `spi-demo/` | real META-INF/services demo |
