#!/usr/bin/env node
import { writeFileSync, existsSync } from 'fs'
import { join, dirname } from 'path'
import { fileURLToPath } from 'url'

const __dirname = dirname(fileURLToPath(import.meta.url))
const dir = join(__dirname, '..', 'pkg5leetcode', 'interview150')

const problems = [
  { lc: 71, name: 'SimplifyPath', file: 'interview150_LC71SimplifyPath.java' },
  { lc: 210, name: 'CourseScheduleII', file: 'interview150_LC210CourseScheduleII.java' },
  { lc: 227, name: 'BasicCalculatorII', file: 'interview150_LC227BasicCalculatorII.java' },
  { lc: 228, name: 'SummaryRanges', file: 'interview150_LC228SummaryRanges.java' },
  { lc: 229, name: 'MajorityElementII', file: 'interview150_LC229MajorityElementII.java' },
  { lc: 380, name: 'InsertDeleteGetRandom', file: 'interview150_LC380InsertDeleteGetRandom.java' },
  { lc: 684, name: 'RedundantConnection', file: 'interview150_LC684RedundantConnection.java' },
  { lc: 721, name: 'AccountsMerge', file: 'interview150_LC721AccountsMerge.java' },
  { lc: 791, name: 'CustomSortString', file: 'interview150_LC791CustomSortString.java' },
  { lc: 909, name: 'SnakesAndLadders', file: 'interview150_LC909SnakesAndLadders.java' },
  { lc: 921, name: 'MinimumAddToMakeValid', file: 'interview150_LC921MinimumAddToMakeValid.java' },
  { lc: 931, name: 'MinimumFallingPathSum', file: 'interview150_LC931MinimumFallingPathSum.java' },
  { lc: 981, name: 'TimeBasedKeyValueStore', file: 'interview150_LC981TimeBasedKeyValueStore.java' },
]

const templates = {
  71: `package pkg5leetcode.interview150;
/** LC 71 Simplify Path */
import java.util.*;
public class interview150_LC71SimplifyPath {
  static String simplifyPath(String path) {
    Deque<String> st = new ArrayDeque<>();
    for (String p : path.split("/")) {
      if (p.isEmpty() || p.equals(".")) continue;
      if (p.equals("..")) { if (!st.isEmpty()) st.pollLast(); }
      else st.addLast(p);
    }
    return "/" + String.join("/", st);
  }
  public static void main(String[] args) {
    System.out.println(simplifyPath("/home/"));
    System.out.println(simplifyPath("/../"));
    System.out.println(simplifyPath("/home//foo/"));
  }
}`,
  210: `package pkg5leetcode.interview150;
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
}`,
  227: `package pkg5leetcode.interview150;
/** LC 227 Basic Calculator II */
public class interview150_LC227BasicCalculatorII {
  static int calculate(String s) {
    int n = s.length(), num = 0, sign = 1, stack = 0, i = 0;
    while (i < n) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) { num = num * 10 + (c - '0'); i++; continue; }
      if (c == '+') { stack += sign * num; num = 0; sign = 1; i++; }
      else if (c == '-') { stack += sign * num; num = 0; sign = -1; i++; }
      else if (c == '*') {
        i++; int b = 0; while (i < n && Character.isDigit(s.charAt(i))) b = b * 10 + (s.charAt(i++) - '0');
        num = sign * num * b; sign = 1;
      } else if (c == '/') {
        i++; int b = 0; while (i < n && Character.isDigit(s.charAt(i))) b = b * 10 + (s.charAt(i++) - '0');
        num = sign * num / b; sign = 1;
      } else i++;
    }
    return stack + sign * num;
  }
  public static void main(String[] args) {
    System.out.println(calculate("3+2*2"));
    System.out.println(calculate(" 3/2 "));
  }
}`,
  228: `package pkg5leetcode.interview150;
/** LC 228 Summary Ranges */
import java.util.*;
public class interview150_LC228SummaryRanges {
  static List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < nums.length; ) {
      int start = nums[i], j = i;
      while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) j++;
      res.add(start == nums[j] ? String.valueOf(start) : start + "->" + nums[j]);
      i = j + 1;
    }
    return res;
  }
  public static void main(String[] args) {
    System.out.println(summaryRanges(new int[]{0,1,2,4,5,7}));
  }
}`,
  229: `package pkg5leetcode.interview150;
/** LC 229 Majority Element II */
import java.util.*;
public class interview150_LC229MajorityElementII {
  static List<Integer> majorityElement(int[] nums) {
    int c1 = 0, c2 = 1, cnt1 = 0, cnt2 = 0;
    for (int x : nums) {
      if (x == c1) cnt1++; else if (x == c2) cnt2++;
      else if (cnt1 == 0) { c1 = x; cnt1 = 1; }
      else if (cnt2 == 0) { c2 = x; cnt2 = 1; }
      else { cnt1--; cnt2--; }
    }
    cnt1 = cnt2 = 0;
    for (int x : nums) { if (x == c1) cnt1++; else if (x == c2) cnt2++; }
    List<Integer> res = new ArrayList<>();
    int n = nums.length;
    if (cnt1 > n / 3) res.add(c1);
    if (c2 != c1 && cnt2 > n / 3) res.add(c2);
    return res;
  }
  public static void main(String[] args) {
    System.out.println(majorityElement(new int[]{3,2,3}));
  }
}`,
  380: `package pkg5leetcode.interview150;
/** LC 380 Insert Delete GetRandom O(1) */
import java.util.*;
public class interview150_LC380InsertDeleteGetRandom {
  static class RandomizedSet {
    List<Integer> list = new ArrayList<>();
    Map<Integer,Integer> idx = new HashMap<>();
    boolean insert(int val) { if (idx.containsKey(val)) return false; idx.put(val, list.size()); list.add(val); return true; }
    boolean remove(int val) {
      if (!idx.containsKey(val)) return false;
      int i = idx.remove(val), last = list.remove(list.size()-1);
      if (val != last) { idx.put(last, i); list.set(i, last); }
      return true;
    }
    int getRandom() { return list.get(new Random().nextInt(list.size())); }
  }
  public static void main(String[] args) {
    RandomizedSet set = new RandomizedSet();
    System.out.println(set.insert(1));
    System.out.println(set.remove(2));
    System.out.println(set.insert(2));
    System.out.println(set.getRandom());
  }
}`,
  684: `package pkg5leetcode.interview150;
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
}`,
  721: `package pkg5leetcode.interview150;
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
}`,
  791: `package pkg5leetcode.interview150;
/** LC 791 Custom Sort String */
public class interview150_LC791CustomSortString {
  static String customSortString(String order, String s) {
    int[] cnt = new int[26];
    for (char c : s.toCharArray()) cnt[c - 'a']++;
    StringBuilder sb = new StringBuilder();
    for (char c : order.toCharArray()) while (cnt[c-'a']-- > 0) sb.append(c);
    for (int i = 0; i < 26; i++) while (cnt[i]-- > 0) sb.append((char)('a'+i));
    return sb.toString();
  }
  public static void main(String[] args) {
    System.out.println(customSortString("cba","abcd"));
  }
}`,
  909: `package pkg5leetcode.interview150;
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
}`,
  921: `package pkg5leetcode.interview150;
/** LC 921 Minimum Add to Make Parentheses Valid */
public class interview150_LC921MinimumAddToMakeValid {
  static int minAddToMakeValid(String s) {
    int open = 0, add = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') open++;
      else if (open > 0) open--;
      else add++;
    }
    return add + open;
  }
  public static void main(String[] args) {
    System.out.println(minAddToMakeValid("()))(("));
  }
}`,
  931: `package pkg5leetcode.interview150;
/** LC 931 Minimum Falling Path Sum */
public class interview150_LC931MinimumFallingPathSum {
  static int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    for (int r = 1; r < n; r++)
      for (int c = 0; c < n; c++) {
        int best = matrix[r-1][c];
        if (c > 0) best = Math.min(best, matrix[r-1][c-1]);
        if (c + 1 < n) best = Math.min(best, matrix[r-1][c+1]);
        matrix[r][c] += best;
      }
    int ans = matrix[n-1][0];
    for (int c = 1; c < n; c++) ans = Math.min(ans, matrix[n-1][c]);
    return ans;
  }
  public static void main(String[] args) {
    System.out.println(minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
  }
}`,
  981: `package pkg5leetcode.interview150;
/** LC 981 Time Based Key-Value Store */
import java.util.*;
public class interview150_LC981TimeBasedKeyValueStore {
  static class TimeMap {
    Map<String, TreeMap<Integer,String>> map = new HashMap<>();
    void set(String key, String value, int ts) {
      map.computeIfAbsent(key, k -> new TreeMap<>()).put(ts, value);
    }
    String get(String key, int ts) {
      var tm = map.get(key);
      if (tm == null) return "";
      var e = tm.floorEntry(ts);
      return e == null ? "" : e.getValue();
    }
  }
  public static void main(String[] args) {
    TimeMap tm = new TimeMap();
    tm.set("foo","bar",1);
    System.out.println(tm.get("foo",1));
    System.out.println(tm.get("foo",3));
  }
}`,
}

for (const p of problems) {
  const fp = join(dir, p.file)
  if (existsSync(fp)) {
    console.log('skip', p.file)
    continue
  }
  writeFileSync(fp, templates[p.lc])
  console.log('wrote', p.file)
}
