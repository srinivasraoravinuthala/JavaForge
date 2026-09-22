package pkg5leetcode.interview150;

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
}
