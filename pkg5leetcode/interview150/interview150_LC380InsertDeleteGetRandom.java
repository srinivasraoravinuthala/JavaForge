package pkg5leetcode.interview150;

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
}
