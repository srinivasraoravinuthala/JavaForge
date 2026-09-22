package pkg5leetcode.official75;

/*
 * Keys and Rooms | LC 841
 * APPROACH: DFS from room 0 through key graph.
 * COMPLEXITY: Time O(n+E), Space O(n)
 */
import java.util.*;

public class official75_LC841KeysAndRooms {
    static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        dfs(0, rooms, seen);
        for (boolean v : seen) if (!v) return false;
        return true;
    }

    static void dfs(int room, List<List<Integer>> rooms, boolean[] seen) {
        seen[room] = true;
        for (int key : rooms.get(room))
            if (!seen[key]) dfs(key, rooms, seen);
    }

    public static void main(String[] args) {
        check(canVisitAllRooms(Arrays.asList(
                Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList())), "case1");
        check(!canVisitAllRooms(Arrays.asList(
                Arrays.asList(1,3), Arrays.asList(3,0,1), Arrays.asList(2), Arrays.asList(0))), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
