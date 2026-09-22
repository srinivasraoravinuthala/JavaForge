package pkg4algorithms;

/*
 * algorithms3GreedyAlgorithms.java
 * ---------------------
 * Greedy = make the locally optimal choice at each step, hoping for a global
 * optimum. Works when the problem has the "greedy-choice property".
 *
 * Examples: activity selection, fractional knapsack, coin change (canonical coins).
 */
import java.util.*;

public class algorithms3GreedyAlgorithms {

    // Activity selection: max non-overlapping activities. Greedy: pick earliest finish.
    static List<int[]> activitySelection(int[][] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a[1]));   // by finish time
        List<int[]> chosen = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (int[] act : activities) {
            if (act[0] >= lastEnd) { chosen.add(act); lastEnd = act[1]; }
        }
        return chosen;
    }

    // Fractional knapsack: take highest value/weight ratio first (fractions allowed).
    static double fractionalKnapsack(int capacity, int[][] items) {
        Arrays.sort(items, (a, b) -> Double.compare((double) b[0] / b[1], (double) a[0] / a[1]));
        double total = 0;
        for (int[] it : items) {
            int value = it[0], weight = it[1];
            if (capacity >= weight) { total += value; capacity -= weight; }
            else { total += value * ((double) capacity / weight); break; }
        }
        return total;
    }

    // Coin change (greedy works for canonical systems like {1,5,10,25}).
    static List<Integer> coinChangeGreedy(int amount, int[] coins) {
        Arrays.sort(coins);
        List<Integer> used = new ArrayList<>();
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) { used.add(coins[i]); amount -= coins[i]; }
        }
        return used;
    }

    public static void main(String[] args) {
        int[][] acts = {{1, 3}, {2, 5}, {4, 7}, {1, 8}, {5, 9}, {8, 10}};
        System.out.println("activities chosen:");
        activitySelection(acts).forEach(a -> System.out.println("  [" + a[0] + "," + a[1] + "]"));

        int[][] items = {{60, 10}, {100, 20}, {120, 30}};   // {value, weight}
        System.out.println("fractional knapsack (cap 50) max value: " + fractionalKnapsack(50, items));

        System.out.println("coin change 87 with {1,5,10,25}: " + coinChangeGreedy(87, new int[]{1, 5, 10, 25}));
    }
}
