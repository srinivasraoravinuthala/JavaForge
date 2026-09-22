package pkg5leetcode.interview150;

/** LC 909 Snakes and Ladders */
import java.util.*;

public class interview150_LC909SnakesAndLadders {
  static int snakesAndLadders(int[][] board) {
    int n = board.length, target = n * n;
    int[] dist = new int[target + 1];
    Arrays.fill(dist, -1);
    Queue<Integer> q = new ArrayDeque<>();
    dist[1] = 0; q.add(1);
    while (!q.isEmpty()) {
      int cur = q.poll();
      if (cur == target) return dist[cur];
      for (int next = cur + 1; next <= Math.min(cur + 6, target); next++) {
        int[] rc = idx(next, n);
        int dest = board[rc[0]][rc[1]] > 0 ? board[rc[0]][rc[1]] : next;
        if (dist[dest] == -1) { dist[dest] = dist[cur] + 1; q.add(dest); }
      }
    }
    return -1;
  }

  static int[] idx(int sq, int n) {
    int r = (sq - 1) / n, c = (sq - 1) % n;
    if (r % 2 == 1) c = n - 1 - c;
    return new int[]{n - 1 - r, c};
  }

  public static void main(String[] args) {
    int[][] b = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
    System.out.println(snakesAndLadders(b));
  }
}
