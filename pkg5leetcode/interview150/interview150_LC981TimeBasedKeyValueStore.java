package pkg5leetcode.interview150;

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
}
