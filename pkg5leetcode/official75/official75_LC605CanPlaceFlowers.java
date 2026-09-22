package pkg5leetcode.official75;

/*
 * Can Place Flowers | LC 605
 * APPROACH: Greedy plant when plot and neighbors empty.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC605CanPlaceFlowers {
    static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0
                    && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {
        check(canPlaceFlowers(new int[]{1,0,0,0,1}, 1), "case1");
        check(!canPlaceFlowers(new int[]{1,0,0,0,1}, 2), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
