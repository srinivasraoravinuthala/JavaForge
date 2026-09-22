package pkg5leetcode.official75;

/*
 * Nearest Exit from Entrance in Maze | LC 1926
 * APPROACH: BFS from entrance to border empty cell.
 * COMPLEXITY: Time O(mn), Space O(mn)
 */
import java.util.*;

public class official75_LC1926NearestExitFromEntranceInMaze {
    static int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+';
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int r = cur[0] + d[0], c = cur[1] + d[1], steps = cur[2] + 1;
                if (r < 0 || c < 0 || r >= m || c >= n || maze[r][c] == '+') continue;
                if ((r == 0 || c == 0 || r == m - 1 || c == n - 1) && steps > 0) return steps;
                maze[r][c] = '+';
                q.add(new int[]{r, c, steps});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] m1 = {{'+','+','.','+'},{'+','.','.','+'},{'+','+','+','.'}};
        check(nearestExit(m1, new int[]{1,2}) == 1, "case1");
        char[][] m2 = {{'.','+'}};
        check(nearestExit(m2, new int[]{0,0}) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
