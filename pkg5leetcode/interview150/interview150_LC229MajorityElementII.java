package pkg5leetcode.interview150;

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
}
