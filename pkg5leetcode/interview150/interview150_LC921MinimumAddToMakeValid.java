package pkg5leetcode.interview150;

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
}
