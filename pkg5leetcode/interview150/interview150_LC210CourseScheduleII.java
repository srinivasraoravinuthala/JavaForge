package pkg5leetcode.interview150;

/** LC 210 Course Schedule II */
import java.util.*;

public class interview150_LC210CourseScheduleII {
  static int[] findOrder(int n, int[][] prereq) {
    List<List<Integer>> g = new ArrayList<>();
    int[] indeg = new int[n];
    for (int i = 0; i < n; i++) g.add(new ArrayList<>());
    for (int[] e : prereq) { g.get(e[1]).add(e[0]); indeg[e[0]]++; }
    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
    int[] order = new int[n]; int idx = 0;
    while (!q.isEmpty()) {
      int u = q.poll(); order[idx++] = u;
      for (int v : g.get(u)) if (--indeg[v] == 0) q.add(v);
    }
    return idx == n ? order : new int[0];
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(findOrder(2, new int[][]{{1,0}})));
  }
}
