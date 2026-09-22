package pkg13libs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/*
 * libs6Reflection.java
 * --------------------
 * Reflection: inspecting and invoking classes/methods/fields at runtime.
 *
 * DEFINITION:
 *   Reflection (java.lang.reflect) lets code examine and manipulate types it did
 *   not know about at compile time. It powers frameworks (Spring, JUnit, Jackson,
 *   ORMs) that wire up your classes generically.
 *
 * KEY POINTS:
 *   - Class<?> is the entry point: getDeclaredFields/Methods/Constructors.
 *   - setAccessible(true) bypasses access checks (use sparingly).
 *   - Invoke methods/build objects dynamically with invoke()/newInstance().
 *   - Trade-offs: slower, breaks encapsulation, no compile-time safety.
 */
public class libs6Reflection {

    static class Person {
        private String name;
        private int age;
        public Person() {}
        public Person(String name, int age) { this.name = name; this.age = age; }
        public String greet() { return "Hi, I'm " + name + " (" + age + ")"; }
        @Override public String toString() { return "Person{" + name + ", " + age + "}"; }
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Person.class;
        System.out.println("Class       : " + clazz.getName());
        System.out.println("Simple name : " + clazz.getSimpleName());

        // Inspect fields
        System.out.println("\nFields:");
        for (Field f : clazz.getDeclaredFields())
            System.out.printf("  %s %s%n", f.getType().getSimpleName(), f.getName());

        // Inspect methods (declared in this class)
        System.out.println("\nMethods:");
        for (Method m : clazz.getDeclaredMethods())
            System.out.printf("  %s %s(%s)%n", m.getReturnType().getSimpleName(),
                    m.getName(),
                    Arrays.stream(m.getParameterTypes()).map(Class::getSimpleName).reduce((x, y) -> x + ", " + y).orElse(""));

        // Build an object dynamically
        Constructor<?> ctor = clazz.getConstructor(String.class, int.class);
        Object person = ctor.newInstance("Ada", 36);
        System.out.println("\nCreated     : " + person);

        // Invoke a method dynamically
        Method greet = clazz.getMethod("greet");
        System.out.println("Invoked     : " + greet.invoke(person));

        // Read/modify a private field (bypassing access control)
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Grace");
        System.out.println("After set   : " + person);
    }
}
