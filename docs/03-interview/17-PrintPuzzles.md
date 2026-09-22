# "What Does This Print?" — Java Puzzles (35)

Work each puzzle on paper first, then check the answer.  
Focus: **operator precedence**, **autoboxing**, **short-circuit**, **String pool**, **generics erasure**.

Runnable checks: compile snippets in `jshell` or small `main` methods.

---

## Puzzles

### Puzzle 1
```java
System.out.println(1 + 2 + "3");
System.out.println("1" + 2 + 3);
```
**Answer:** `33` then `123`.  
**Why:** Left-to-right: `1+2=3`, then string concat `"3"`. Second line all string concat after first `"1"`.

---

### Puzzle 2
```java
Integer a = 127;
Integer b = 127;
Integer c = 128;
Integer d = 128;
System.out.println(a == b);
System.out.println(c == d);
```
**Answer:** `true` then `false`.  
**Why:** Integer cache -128..127; 128 is new objects.

---

### Puzzle 3
```java
String s = "hello";
s += " world";
String t = "hello world";
System.out.println(s == t);
```
**Answer:** `false` (usually).  
**Why:** `+=` creates new String on heap; `t` is literal pool `"hello world"` unless interned.

---

### Puzzle 4
```java
System.out.println(Math.round(2.5));
System.out.println(Math.round(-2.5));
```
**Answer:** `2` and `-2`.  
**Why:** `round` uses half-up toward positive infinity for .5 cases on doubles (not bankers rounding).

---

### Puzzle 5
```java
int i = 0;
System.out.println(i++ + ++i);
```
**Answer:** `2`.  
**Why:** Postfix `0` + prefix `2` (i becomes 2 before second use).

---

### Puzzle 6
```java
Boolean b1 = true;
Boolean b2 = true;
System.out.println(b1 == b2);
```
**Answer:** `true`.  
**Why:** Boolean.TRUE cached instances for autoboxed `true`.

---

### Puzzle 7
```java
try {
    System.out.println("try");
    return;
} finally {
    System.out.println("finally");
}
```
**Answer:** Prints `try` then `finally`.  
**Why:** `finally` runs before method actually returns.

---

### Puzzle 8
```java
List<String> list = Arrays.asList("a", "b");
list.add("c");
```
**Answer:** `UnsupportedOperationException` at runtime.  
**Why:** `Arrays.asList` returns fixed-size list.

---

### Puzzle 9
```java
Map<String, Integer> m = new HashMap<>();
m.put("a", 1);
m.put("a", 2);
System.out.println(m.size());
System.out.println(m.get("a"));
```
**Answer:** `1` and `2`.  
**Why:** Same key replaces value; size unchanged.

---

### Puzzle 10
```java
System.out.println(null + true);
```
**Answer:** **Compile error.**  
**Why:** `null + boolean` — string concatenation only if one operand is String; `true` is boolean.

---

### Puzzle 11
```java
Object o = true ? Integer.valueOf(1) : "x";
System.out.println(o.getClass().getName());
```
**Answer:** `java.lang.Integer`.  
**Why:** Ternary requires compatible types; both branches become `Object`; first branch is Integer.

---

### Puzzle 12
```java
int[] a = {1, 2};
int[] b = a;
b[0] = 9;
System.out.println(a[0]);
```
**Answer:** `9`.  
**Why:** Arrays are objects; reference copy shares same array.

---

### Puzzle 13
```java
StringBuilder sb = new StringBuilder("ab");
sb.append("c").delete(0, 1);
System.out.println(sb.toString());
```
**Answer:** `bc`.  
**Why:** delete removes index 0 char `a`.

---

### Puzzle 14
```java
System.out.println(0.1 + 0.2 == 0.3);
```
**Answer:** `false`.  
**Why:** Floating-point representation error.

---

### Puzzle 15
```java
List<Integer> list = List.of(1, 2, 3);
list.set(0, 9);
```
**Answer:** `UnsupportedOperationException`.  
**Why:** `List.of` is immutable.

---

### Puzzle 16
```java
Stream.of(1, 2, 3).peek(System.out::println).count();
```
**Answer:** Prints 1, 2, 3 then returns 3.  
**Why:** `count` is terminal; `peek` runs on pipeline execution.

---

### Puzzle 17
```java
Optional<String> o = Optional.ofNullable(null);
System.out.println(o.orElse("x"));
```
**Answer:** `x`.  
**Why:** Empty optional uses orElse default.

---

### Puzzle 18
```java
class Parent { String greet() { return "P"; } }
class Child extends Parent { String greet() { return "C"; } }
Parent p = new Child();
System.out.println(p.greet());
```
**Answer:** `C`.  
**Why:** Virtual method dispatch on runtime type Child.

---

### Puzzle 19
```java
System.out.println("A" + 1 + 2);
System.out.println(1 + 2 + "A");
```
**Answer:** `A12` then `3A`.  
**Why:** String promotion left-to-right.

---

### Puzzle 20
```java
int x = 5;
System.out.println(x > 2 ? x < 4 ? "a" : "b" : "c");
```
**Answer:** `b`.  
**Why:** Ternary right-associative: `x>2 ? (x<4?"a":"b") : "c"` → true branch, x<4 false → `b`.

---

### Puzzle 21
```java
System.out.println(new String("hi") == "hi");
System.out.println(new String("hi").intern() == "hi");
```
**Answer:** `false` then `true`.  
**Why:** `new String` new object; `intern()` enters pool.

---

### Puzzle 22
```java
List raw = new ArrayList<>();
raw.add(1);
raw.add("two");
System.out.println(raw.size());
```
**Answer:** `2`.  
**Why:** Raw list no compile-time type check; both added.

---

### Puzzle 23
```java
List<?> list = List.of(1, 2);
list.add(3);
```
**Answer:** **Compile error.**  
**Why:** `?` unbounded wildcard — add not allowed (except null).

---

### Puzzle 24
```java
int i = 1;
switch (i) {
    case 1: System.out.print("1 ");
    case 2: System.out.print("2 ");
    default: System.out.print("d ");
}
```
**Answer:** `1 2 d ` (with classic fall-through).  
**Why:** No `break` — falls through cases.

---

### Puzzle 25
```java
record R(int x) {}
R r1 = new R(1);
R r2 = new R(1);
System.out.println(r1.equals(r2));
System.out.println(r1 == r2);
```
**Answer:** `true` then `false`.  
**Why:** Record value equality; distinct objects.

---

### Puzzle 26
```java
Thread t = new Thread(() -> System.out.println("run"));
t.start();
t.start();
```
**Answer:** Second `start()` throws `IllegalThreadException`.  
**Why:** Thread already started.

---

### Puzzle 27
```java
String s = null;
System.out.println(s instanceof String);
```
**Answer:** `false`.  
**Why:** `instanceof` false for null; no NPE.

---

### Puzzle 28
```java
System.out.println(10 / 0.0);
System.out.println(10.0 / 0.0);
```
**Answer:** `Infinity` then `Infinity`.  
**Why:** Floating divide by zero → Infinity (not exception).

---

### Puzzle 29
```java
char c = 'A';
System.out.println(c + 1);
System.out.println(c + 1 + "");
```
**Answer:** `66` then `66` as string? Wait: `c+1` int 66; `c+1+""` → `66` string because `66+""` string concat.  
**Answer:** `66` then `"66"`.

---

### Puzzle 30
```java
Map map = new HashMap();
map.put(1, "one");
map.put(1, "ONE");
System.out.println(map.get(1));
```
**Answer:** `ONE`.  
**Why:** Integer key 1; second put replaces.

---

### Puzzle 31
```java
System.out.println(~1);
```
**Answer:** `-2`.  
**Why:** Bitwise NOT of 1 (`...0001`) → `...1110` = -2 in two's complement.

---

### Puzzle 32
```java
boolean flag = false;
if (flag = true) {
    System.out.println("yes");
}
```
**Answer:** Prints `yes` (compiles — assignment in condition).  
**Why:** `flag = true` assigns and evaluates true. Style smell, not puzzle error.

---

### Puzzle 33
```java
Deque<Integer> d = new ArrayDeque<>();
d.push(1);
d.push(2);
System.out.println(d.pop());
```
**Answer:** `2`.  
**Why:** Stack LIFO — push 1 then 2, pop returns 2.

---

### Puzzle 34
```java
int[][] m = {{1, 2}, {3}};
System.out.println(m[1][0]);
```
**Answer:** `3`.

---

### Puzzle 35
```java
System.out.println("Java".substring(1, 3));
```
**Answer:** `av`.  
**Why:** `substring(begin, end)` end exclusive; indices 1..2.

---

## Study Tips

1. Trace types on paper — promotion rules bite often.
2. Distinguish `==` (reference/primitive) vs `equals` (value).
3. Know which APIs return **views** vs **copies** vs **immutable** instances.
4. Classic switch **falls through** unless `->` or `break`.
5. Re-run puzzling snippets in `jshell` — muscle memory beats memorizing answers.
