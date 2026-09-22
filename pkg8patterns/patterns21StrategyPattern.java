package pkg8patterns;

/*
 * Strategy (Behavioral)
 * ---------------------
 * INTENT: define a family of algorithms, encapsulate each, and make them
 *         interchangeable at runtime.
 * UML: Context --> Strategy ; concrete strategies implement the algorithm.
 * PROS: swap algorithms at runtime; removes conditionals; open/closed.
 * CONS: clients must know the strategies; many small classes (lambdas help).
 * REAL-WORLD: Comparator, payment methods, compression/encryption choices.
 */
import java.util.*;
import java.util.function.*;

public class patterns21StrategyPattern {

    // Strategy is just a function here (lambdas make this elegant)
    static int[] sortWith(int[] data, Comparator<Integer> strategy) {
        Integer[] boxed = Arrays.stream(data).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed, strategy);
        return Arrays.stream(boxed).mapToInt(Integer::intValue).toArray();
    }

    interface PaymentStrategy { String pay(double amount); }

    public static void main(String[] args) {
        int[] data = {5, 2, 8, 1, 9};
        System.out.println("asc:  " + Arrays.toString(sortWith(data, Comparator.naturalOrder())));
        System.out.println("desc: " + Arrays.toString(sortWith(data, Comparator.reverseOrder())));

        // Interchangeable payment strategies
        Map<String, PaymentStrategy> strategies = Map.of(
                "card", amt -> "Paid $" + amt + " by credit card",
                "upi",  amt -> "Paid $" + amt + " via UPI",
                "cash", amt -> "Paid $" + amt + " in cash");
        for (String method : List.of("card", "upi", "cash"))
            System.out.println(strategies.get(method).pay(42.0));
    }
}
