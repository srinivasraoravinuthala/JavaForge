# 02 — Learning Path (Runnable Code Order)

**Part of:** [docs/00-INDEX.md](../00-INDEX.md) · **Tutorials:** [02-learn/00-INDEX.md](../02-learn/00-INDEX.md)

Follow the numbers. Run each file with `java <package>/<class>.java`.

> **Prerequisite:** Complete [03 Environment Setup](03-EnvironmentSetup.md) (JDK, project on disk, IDE optional) before running code.

---

## Step 0 — Orientation

1. [03 Environment Setup](03-EnvironmentSetup.md) — install JDK, download project, IDE, first run
2. [01 Tutorial & History](01-TutorialAndHistory.md)
3. [02-learn/01 Getting Started](../02-learn/01-GettingStarted.md)
4. `pkg0intro/intro1AboutJava` → `intro2HistoryOfJava` → `intro3Objectives`

## Level 1 — Beginner (`pkg1core` 1–14, 24–25)

`core1HelloWorld` → `core2Variables` → `core3DataTypes` → `core4Operators` →
`core5ControlStatements` → `core6Loops` → `core7Methods` → `core8ArraysDemo` →
`core9StringsDemo` → `core24UserInput` → `core25ClassesAndObjectsDemo` →
`core10Encapsulation` → `core11Inheritance` → `core12Polymorphism` →
`core13AbstractionDemo` → `core14InterfacesDemo`

**Docs:** [02-learn/02](../02-learn/02-VariablesAndTypes.md) – [02-learn/13](../02-learn/13-AbstractionAndInterfaces.md)

## Level 2 — Intermediate (`pkg1core` 15–28, `pkg2versions`)

`core15EnumsDemo` → `core16RecordsDemo` → `core17SealedClassesDemo` →
`core18ExceptionsDemo` → `core19CollectionsDemo` → `core20GenericsDemo` →
`core21FunctionalProgramming` → `core22StreamsDemo` → `core23OptionalDemo` →
`core26ConstructorsDemo` → `core27StaticMembersDemo` → `core28ComparatorDemo` →
`pkg2versions/versions1Java5Features` → `versions6Java21Features`

**Docs:** [02-learn/14](../02-learn/14-StaticAndEnums.md) – [02-learn/21](../02-learn/21-JavaVersions.md)

## Level 3 — Senior (`pkg3`–`pkg6`)

- `pkg3datastructures/datastructures0DynamicArray` → `…12UnionFind`
- `pkg4algorithms/algorithms1SortingAlgorithms` → `…7DivideAndConquer`
- **LeetCode:** `leetcode1` → `leetcode12`; then `blind75/`, `official75/`, `interview150/`, `top100/` — see [04-reference/14-LeetCode.md](../04-reference/14-LeetCode.md)
- `pkg6jvm/jvm1ClassLoadingDemo` → `jvm3GarbageCollectionDemo`

**Docs:** [02-learn/22](../02-learn/22-DataStructures.md) – [02-learn/25](../02-learn/25-JVMAndMemory.md)

## Level 4 — Tech Lead (`pkg7`, `pkg8`)

- `pkg7concurrency/concurrency1ThreadBasics` → `concurrency8LocksAndCoordination`
- `pkg8patterns/patterns1SingletonPattern` → `patterns23VisitorPattern`
- [03-interview/18-SystemDesign.md](../03-interview/18-SystemDesign.md)
- [04-reference/02-JVMInternals.md](../04-reference/02-JVMInternals.md)

**Docs:** [02-learn/26](../02-learn/26-Concurrency.md) – [02-learn/27](../02-learn/27-DesignPatterns.md)

## Level 5 — Applied Java (`pkg9`–`pkg13`)

| Topic | Package | Reference |
|-------|---------|-----------|
| File I/O | `pkg9io` | [04-reference/04-IO.md](../04-reference/04-IO.md) |
| Networking | `pkg10networking` | [04-reference/05-Networking.md](../04-reference/05-Networking.md) |
| JDBC | `pkg11jdbc` | [04-reference/06-JDBC.md](../04-reference/06-JDBC.md) |
| REST | `pkg12restapi` | [04-reference/07-RestApis.md](../04-reference/07-RestApis.md) |
| Std libs | `pkg13libs` | [04-reference/08-Libraries.md](../04-reference/08-Libraries.md) |

**Docs:** [02-learn/28](../02-learn/28-IOAndNIO.md) – [02-learn/32](../02-learn/32-StandardLibraries.md)

## Level 6 — Ecosystem (`pkg14`–`pkg20`, `build/`)

| Topic | Reference |
|-------|-----------|
| Testing | [04-reference/09-Testing.md](../04-reference/09-Testing.md) |
| Modules | [04-reference/10-Modules.md](../04-reference/10-Modules.md) |
| Memory model | [04-reference/11-MemoryModel.md](../04-reference/11-MemoryModel.md) |
| Serialization | [04-reference/12-Serialization.md](../04-reference/12-Serialization.md) |
| Build | [04-reference/13-BuildTools.md](../04-reference/13-BuildTools.md) |

**Docs:** [02-learn/33](../02-learn/33-Testing.md) – [02-learn/37](../02-learn/37-InterviewPrep.md)

## Interview-season daily routine

- 2 problems from `pkg5leetcode`
- 20 Q&A from [03-interview/00-INDEX.md](../03-interview/00-INDEX.md)
- 1 pattern from `pkg8patterns` re-implemented
- 1 JVM or concurrency topic reviewed
