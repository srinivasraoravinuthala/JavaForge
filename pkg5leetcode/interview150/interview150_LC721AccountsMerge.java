package pkg5leetcode.interview150;

/** LC 721 Accounts Merge */
import java.util.*;

public class interview150_LC721AccountsMerge {
  static int find(int[] p, int x) { return p[x] == x ? x : (p[x] = find(p, p[x])); }

  static List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String,Integer> id = new HashMap<>();
    Map<String,String> name = new HashMap<>();
    int n = 0;
    for (List<String> acc : accounts) {
      for (int i = 1; i < acc.size(); i++) {
        id.putIfAbsent(acc.get(i), n++);
        name.putIfAbsent(acc.get(i), acc.get(0));
      }
    }
    int[] p = new int[n]; for (int i = 0; i < n; i++) p[i] = i;
    for (List<String> acc : accounts) {
      int first = id.get(acc.get(1));
      for (int i = 2; i < acc.size(); i++) { int j = id.get(acc.get(i)); p[find(p, first)] = find(p, j); }
    }
    Map<Integer, TreeSet<String>> groups = new HashMap<>();
    for (var e : id.entrySet()) groups.computeIfAbsent(find(p, e.getValue()), k -> new TreeSet<>()).add(e.getKey());
    List<List<String>> res = new ArrayList<>();
    for (var g : groups.values()) {
      List<String> row = new ArrayList<>(g);
      row.add(0, name.get(row.get(0)));
      res.add(row);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(accountsMerge(List.of(
      List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
      List.of("John","johnsmith@mail.com","john00@mail.com"),
      List.of("Mary","mary@mail.com"))));
  }
}
