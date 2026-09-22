package pkg5leetcode.interview150;

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
}
