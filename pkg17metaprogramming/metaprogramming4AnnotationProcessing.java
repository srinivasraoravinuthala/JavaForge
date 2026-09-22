package pkg17metaprogramming;

/*
 * metaprogramming4AnnotationProcessing.java
 * -----------------------------------------
 * Compile-time annotation processing: generating code from annotations.
 *
 * DEFINITION:
 *   Annotation processors run during compilation (javac) and can validate
 *   annotations or generate new source files. Lombok, MapStruct, and Dagger
 *   use this mechanism.
 *
 * KEY POINTS:
 *   - Processor extends AbstractProcessor, registered via @AutoService or META-INF.
 *   - @SupportedAnnotationTypes, @SupportedSourceVersion on the processor class.
 *   - RoundEnvironment gives annotated elements each compilation round.
 *   - Runtime retention (@Retention RUNTIME) is for reflection; SOURCE/CLASS for compile-only.
 *
 * This file is conceptual. A real processor lives in pkg17metaprogramming/processor-demo/.
 */
public class metaprogramming4AnnotationProcessing {

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
    @java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE)
    @interface GenerateBuilder {}

    @GenerateBuilder
    static class Person {
        String name;
        int age;
    }

    public static void main(String[] args) {
        System.out.println("Annotation retention policies:");
        System.out.println("  SOURCE  — compile-time only (e.g. @Override, Lombok)");
        System.out.println("  CLASS   — in bytecode, not visible at runtime");
        System.out.println("  RUNTIME — readable via reflection at runtime");

        System.out.println("\nCompile-time processing flow:");
        System.out.println("  1. javac finds @GenerateBuilder on Person");
        System.out.println("  2. Processor generates PersonBuilder.java");
        System.out.println("  3. javac compiles generated sources in same round");

        System.out.println("\nPerson has @GenerateBuilder: " +
                Person.class.isAnnotationPresent(GenerateBuilder.class));
        System.out.println("(SOURCE retention => false at runtime — expected)");

        System.out.println("\nRun processor demo: cd pkg17metaprogramming/processor-demo && mvn -q compile");
    }
}
