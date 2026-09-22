package pkg5leetcode.official75;

/*
 * Asteroid Collision | LC 735
 * APPROACH: Stack simulate collisions by direction and size.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC735AsteroidCollision {
    static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> st = new ArrayDeque<>();
        for (int a : asteroids) {
            boolean alive = true;
            while (alive && a < 0 && !st.isEmpty() && st.peekLast() > 0) {
                int top = st.peekLast();
                if (top < -a) st.pollLast();
                else if (top == -a) { st.pollLast(); alive = false; }
                else alive = false;
            }
            if (alive) st.addLast(a);
        }
        return st.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        check(Arrays.equals(asteroidCollision(new int[]{5,10,-5}), new int[]{5,10}), "case1");
        check(Arrays.equals(asteroidCollision(new int[]{8,-8}), new int[]{}), "case2");
        check(Arrays.equals(asteroidCollision(new int[]{10,2,-5}), new int[]{10}), "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
