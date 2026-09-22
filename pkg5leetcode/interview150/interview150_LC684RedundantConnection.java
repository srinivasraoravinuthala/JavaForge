package pkg5leetcode.interview150;

/** LC 684 Redundant Connection */
public class interview150_LC684RedundantConnection {
  static int find(int[] p, int x) { return p[x] == x ? x : (p[x] = find(p, p[x])); }

  static int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    int[] p = new int[n + 1];
    for (int i = 0; i <= n; i++) p[i] = i;
    for (int[] e : edges) {
      int a = find(p, e[0]), b = find(p, e[1]);
      if (a == b) return e;
      p[a] = b;
    }
    return new int[0];
  }

  public static void main(String[] args) {
    System.out.println(java.util.Arrays.toString(findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}})));
  }
}
