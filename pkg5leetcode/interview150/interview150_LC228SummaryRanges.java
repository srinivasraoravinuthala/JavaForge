package pkg5leetcode.interview150;

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
}
