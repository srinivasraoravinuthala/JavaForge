# Java Cheat Sheet (Java 21)

## Primitives
| Type | Size | Range / Note | Default |
|------|------|--------------|---------|
| `byte` | 8-bit | -128..127 | 0 |
| `short` | 16-bit | -32,768..32,767 | 0 |
| `int` | 32-bit | ~±2.1B | 0 |
| `long` | 64-bit | ~±9.2e18 (`L` suffix) | 0L |
| `float` | 32-bit | ~7 digits (`f` suffix) | 0.0f |
| `double` | 64-bit | ~15 digits | 0.0d |
| `char` | 16-bit | UTF-16 code unit | '\u0000' |
| `boolean` | JVM-dependent | true/false | false |

## Variables
```java
int x = 10;            // explicit
var y = 10;            // inferred (local only, Java 10+)
final int Z = 5;       // constant
```

## Strings
```java
String s = "hi";
s.length(); s.charAt(0); s.substring(1); s.indexOf("i");
s.toUpperCase(); s.trim(); s.strip(); s.isBlank();
s.split(","); String.join("-", "a","b");
s.repeat(3); s.contains("h"); s.replace("h","H");
String t = """
   text block (Java 15+)
   """;
String f = "x=%d".formatted(10);  // or String.format
StringBuilder sb = new StringBuilder().append("a").append("b");
```

## Control flow
```java
if (c) {...} else if (d) {...} else {...}
switch (n) { case 1 -> "one"; default -> "other"; }      // switch expr
var r = switch (obj) {                                     // pattern match
    case Integer i -> "int " + i;
    case String str when str.length() > 3 -> "long str";
    default -> "other";
};
for (int i=0;i<n;i++){} 
for (var e : list){}
while (c){}  do {} while(c);
```

## Collections
```java
List<Integer> list = new ArrayList<>();   // add/get/remove/size
Set<Integer> set = new HashSet<>();       // unique; TreeSet=sorted; LinkedHashSet=insertion order
Map<String,Integer> map = new HashMap<>();// put/get/getOrDefault/computeIfAbsent/merge
Deque<Integer> dq = new ArrayDeque<>();   // stack: push/pop; queue: offer/poll
Queue<Integer> pq = new PriorityQueue<>();// min-heap by default
List<Integer> imm = List.of(1,2,3);       // immutable factory
```

## Streams
```java
list.stream()
    .filter(x -> x > 0)
    .map(x -> x * 2)
    .sorted()
    .distinct()
    .limit(10)
    .collect(Collectors.toList());      // or .toList()
list.stream().mapToInt(Integer::intValue).sum();
list.stream().collect(Collectors.groupingBy(fn));
list.stream().reduce(0, Integer::sum);
IntStream.range(0, n).forEach(System.out::println);
```

## Optional
```java
Optional<String> o = Optional.ofNullable(x);
o.map(String::length).orElse(0);
o.ifPresent(System.out::println);
o.orElseThrow();
```

## Functional interfaces
| Interface | Method | Use |
|-----------|--------|-----|
| `Supplier<T>` | `get()` | produce |
| `Consumer<T>` | `accept(t)` | consume |
| `Function<T,R>` | `apply(t)` | transform |
| `Predicate<T>` | `test(t)` | boolean test |
| `BiFunction<T,U,R>` | `apply(t,u)` | two args |
| `UnaryOperator<T>` | `apply(t)` | T→T |

## Records, sealed, enums
```java
record Point(int x, int y) {}
sealed interface Shape permits Circle, Square {}
enum Day { MON, TUE; }
```

## Exceptions
```java
try (var r = open()) { ... }              // try-with-resources
catch (IOException | SQLException e) {...} // multi-catch
finally { ... }
throw new IllegalArgumentException("msg");
```

## Concurrency
```java
Thread.ofVirtual().start(() -> {});                 // virtual thread (Java 21)
ExecutorService ex = Executors.newFixedThreadPool(4);
Future<Integer> f = ex.submit(() -> 42);
CompletableFuture.supplyAsync(() -> 1).thenApply(x -> x+1);
synchronized(lock) {}  // mutual exclusion
AtomicInteger ai = new AtomicInteger();
```

## Big-O quick reference
| Structure | Access | Search | Insert | Delete |
|-----------|--------|--------|--------|--------|
| Array | O(1) | O(n) | O(n) | O(n) |
| ArrayList | O(1) | O(n) | O(1)* | O(n) |
| LinkedList | O(n) | O(n) | O(1) | O(1) |
| HashMap | — | O(1)* | O(1)* | O(1)* |
| TreeMap | — | O(log n) | O(log n) | O(log n) |
| Heap | — | O(n) | O(log n) | O(log n) |

\* amortized / average.

## Compile & run
```bash
javac --release 21 -d out File.java && java -cp out File
java File.java          # single-file source launch
```
