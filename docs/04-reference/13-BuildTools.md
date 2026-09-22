# Build Tools — Maven & Gradle (`build/`)

Runnable examples in [`build/`](../../build/).

## Maven lifecycle (phases)

`validate` → `compile` → `test` → `package` → `verify` → `install` → `deploy`

Common commands:
```bash
mvn compile          # compile main sources
mvn test             # run unit tests
mvn package          # build JAR/WAR
mvn install          # install to local ~/.m2
mvn dependency:tree  # show dependency tree (conflict debugging)
```

## Key concepts

| Concept | Purpose |
|---------|---------|
| `groupId` | org namespace (com.mycompany) |
| `artifactId` | project name (my-service) |
| `version` | semver (1.2.3) |
| `scope` | compile, test, provided, runtime |
| `dependencyManagement` | centralize versions (BOM) |
| `parent POM` | inherit plugin/config |
| `multi-module` | monorepo with shared parent |

## Semantic versioning

`MAJOR.MINOR.PATCH` — breaking / feature / fix. Pre-release: `1.0.0-SNAPSHOT`.

## Conflict resolution

- `mvn dependency:tree -Dverbose` shows evicted versions.
- Use `<dependencyManagement>` or BOM to align versions.
- Prefer one logging facade (SLF4J) across the tree.

## Gradle (in `build/gradle/`)

- `build.gradle` / `build.gradle.kts` — project build script
- `settings.gradle` — multi-project root
- `gradle run`, `gradle test`, `gradle build`
- Gradle wrapper (`gradlew`) pins Gradle version for CI

## Examples in this repo

| Path | Demonstrates |
|------|--------------|
| `build/maven/simple-app` | minimal POM + exec |
| `build/maven/multi-module/parent` | parent + api + app modules |
| `build/maven/bom` | Bill of Materials |
| `build/gradle/simple-app` | Gradle application plugin |
