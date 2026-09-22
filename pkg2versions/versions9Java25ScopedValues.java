package pkg2versions;

/*
 * versions9Java25ScopedValues.java
 * Scoped values (JEP 506), final in Java 25.
 * Incubated in Java 20. Preview in Java 21 through Java 24.
 */
public class versions9Java25ScopedValues {
    private static final ScopedValue<String> USER = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(USER, "ada").run(() -> System.out.println(USER.get()));
    }
}
