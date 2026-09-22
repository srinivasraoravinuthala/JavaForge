import java.util.Scanner;

/** Console gradebook — Project 01 (after learn ch.09). */
public class Gradebook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many students? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        if (n <= 0) {
            System.out.println("Need at least 1 student.");
            return;
        }

        String[] names = new String[n];
        double[] scores = new double[n];
        double sum = 0;
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n; i++) {
            System.out.print("Name " + (i + 1) + ": ");
            names[i] = sc.nextLine().trim();
            System.out.print("Score 0-100: ");
            scores[i] = Double.parseDouble(sc.nextLine().trim());
            if (scores[i] < 0 || scores[i] > 100) {
                System.out.println("Invalid score; using 0.");
                scores[i] = 0;
            }
            sum += scores[i];
            max = Math.max(max, scores[i]);
            min = Math.min(min, scores[i]);
        }

        System.out.printf("Average: %.2f%n", sum / n);
        System.out.printf("Highest: %.2f%n", max);
        System.out.printf("Lowest: %.2f%n", min);
        for (int i = 0; i < n; i++) {
            System.out.printf("%s → %.1f%n", names[i], scores[i]);
        }
    }
}
