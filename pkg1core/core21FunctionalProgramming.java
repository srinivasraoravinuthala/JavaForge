package pkg1core;

/*
 * core21FunctionalProgramming.java
 * --------------------------
 * Lambdas, method references, and the core java.util.function interfaces.
 *
 * EXPLANATION:
 *  - A lambda implements a functional interface (exactly one abstract method).
 *  - Method references (Class::method) are shorthand for simple lambdas.
 *  - Core interfaces: Supplier, Consumer, Function, Predicate, BiFunction, etc.
 */
import java.util.*;
import java.util.function.*;

public class core21FunctionalProgramming {

    @FunctionalInterface
    interface Calculator { int op(int a, int b); }   // custom functional interface

    public static void main(String[] args) {
        // Custom functional interface via lambda
        Calculator add = (a, b) -> a + b;
        Calculator mul = (a, b) -> a * b;
        System.out.println("add=" + add.op(3, 4) + " mul=" + mul.op(3, 4));

        // Supplier: produces a value
        Supplier<String> greet = () -> "hello";
        System.out.println("Supplier: " + greet.get());

        // Consumer: accepts and acts
        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        printer.accept("consume me");

        // Function: transforms T -> R, with composition
        Function<Integer, Integer> doubler = x -> x * 2;
        Function<Integer, Integer> inc = x -> x + 1;
        System.out.println("compose (double then inc)(5) = " + doubler.andThen(inc).apply(5));
        System.out.println("compose (inc then double)(5) = " + doubler.compose(inc).apply(5));

        // Predicate: boolean test, with combinators
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isPositive = x -> x > 0;
        System.out.println("isEven.and(isPositive)(4) = " + isEven.and(isPositive).test(4));
        System.out.println("isEven.negate()(3) = " + isEven.negate().test(3));

        // BiFunction
        BiFunction<Integer, Integer, Integer> power = (b, e) -> (int) Math.pow(b, e);
        System.out.println("2^10 = " + power.apply(2, 10));

        // Method references (4 kinds)
        Function<String, Integer> len = String::length;             // instance method of arg
        Supplier<ArrayList<String>> factory = ArrayList::new;       // constructor ref
        Function<String, String> upper = String::toUpperCase;       // instance method
        BiFunction<String, String, Boolean> eq = String::equals;    // bound style
        System.out.println("len(hello)=" + len.apply("hello") + " upper=" + upper.apply("hi"));
        System.out.println("new list empty? " + factory.get().isEmpty() + " eq=" + eq.apply("a", "a"));

        // Using lambdas with collections
        List<Integer> nums = new ArrayList<>(List.of(5, 3, 1, 4, 2));
        nums.sort(Comparator.reverseOrder());
        nums.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
