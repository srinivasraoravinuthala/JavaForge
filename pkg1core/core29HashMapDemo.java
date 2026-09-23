package pkg1core;

/*
 * core29HashMapDemo.java
 * ---------------------
 * java.util.HashMap as a teaching focus: put/get/update, and why map keys
 * need stable equals() and hashCode().
 *
 * EXPLANATION:
 *  - Keys are unique; put with an existing key replaces the value.
 *  - Lookup uses hashCode (bucket) then equals (match within the bucket).
 *  - Mutating a key after put can make the entry unfindable.
 *
 * See also: core19CollectionsDemo (Map among other collections),
 *           pkg3datastructures/datastructures6HashTableImpl (under the hood).
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class core29HashMapDemo {

    /** Mutable on purpose — shows why map keys must stay stable. */
    static class MutableId {
        int id;
        MutableId(int id) { this.id = id; }

        @Override
        public boolean equals(Object o) {
            return o instanceof MutableId other && id == other.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "id=" + id;
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ana", 20);
        ages.put("Bob", 19);
        ages.put("Ana", 21);                                    // update existing key
        System.out.println("ages=" + ages + " get(Ana)=" + ages.get("Ana"));
        System.out.println("getOrDefault(Zed)=" + ages.getOrDefault("Zed", 0));
        ages.merge("Bob", 1, Integer::sum);
        System.out.println("after merge Bob=" + ages.get("Bob"));

        // One null key is allowed (special case — prefer clear keys in new code)
        Map<String, String> labels = new HashMap<>();
        labels.put(null, "missing-key");
        System.out.println("null key value=" + labels.get(null));

        // Key contract: equal keys share a slot; mutating hash fields breaks lookup
        Map<MutableId, String> byId = new HashMap<>();
        MutableId key = new MutableId(7);
        byId.put(key, "task-7");
        System.out.println("before mutate: get=" + byId.get(key) + " containsKey=" + byId.containsKey(key));

        key.id = 99;                                            // changes equals/hashCode
        System.out.println("after mutate:  get=" + byId.get(key) + " containsKey=" + byId.containsKey(key));
        System.out.println("map still holds one entry: " + byId); // entry is effectively lost to lookup

        MutableId sameAsOriginal = new MutableId(7);
        System.out.println("lookup with id=7 again: " + byId.get(sameAsOriginal)); // usually null now
    }
}
