package pkg2versions;

/*
 * versions10Java25FlexibleConstructors.java
 * Flexible constructor bodies (JEP 513), final in Java 25.
 * Preview in Java 22, Java 23, and Java 24. Statements before super(...)
 * cannot use the object under construction.
 */
public class versions10Java25FlexibleConstructors {
    static class Named {
        final String name;

        Named(String name) {
            this.name = name;
        }
    }

    static class User extends Named {
        final int age;

        User(String name, int age) {
            if (age < 0) {
                throw new IllegalArgumentException("age");
            }
            super(name);
            this.age = age;
        }
    }

    public static void main(String[] args) {
        System.out.println(new User("ada", 36).name);
    }
}
