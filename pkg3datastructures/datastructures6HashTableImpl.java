package pkg3datastructures;

/*
 * datastructures6HashTableImpl.java
 * ------------------
 * A hash map using separate chaining (buckets of linked entries) with resizing.
 * Demonstrates how HashMap works under the hood.
 *
 * COMPLEXITY: get/put/remove O(1) average, O(n) worst (all collisions).
 * KEY IDEAS: hashCode -> bucket index; load factor triggers resize/rehash.
 */
public class datastructures6HashTableImpl {

    static class Entry<K, V> {
        final K key; V value; Entry<K, V> next;
        Entry(K key, V value) { this.key = key; this.value = value; }
    }

    static class MyHashMap<K, V> {
        private Entry<K, V>[] buckets;
        private int size;
        private static final double LOAD_FACTOR = 0.75;

        @SuppressWarnings("unchecked")
        MyHashMap() { buckets = new Entry[8]; }

        private int indexFor(K key) {
            int h = (key == null) ? 0 : key.hashCode();
            h ^= (h >>> 16);                       // spread bits (like HashMap)
            return (buckets.length - 1) & h;       // fast modulo for power-of-two size
        }

        void put(K key, V value) {
            int i = indexFor(key);
            for (Entry<K, V> e = buckets[i]; e != null; e = e.next) {
                if (java.util.Objects.equals(e.key, key)) { e.value = value; return; }
            }
            Entry<K, V> head = new Entry<>(key, value);
            head.next = buckets[i];
            buckets[i] = head;
            if (++size > buckets.length * LOAD_FACTOR) resize();
        }

        V get(K key) {
            for (Entry<K, V> e = buckets[indexFor(key)]; e != null; e = e.next) {
                if (java.util.Objects.equals(e.key, key)) return e.value;
            }
            return null;
        }

        boolean remove(K key) {
            int i = indexFor(key);
            Entry<K, V> prev = null, e = buckets[i];
            while (e != null) {
                if (java.util.Objects.equals(e.key, key)) {
                    if (prev == null) buckets[i] = e.next; else prev.next = e.next;
                    size--; return true;
                }
                prev = e; e = e.next;
            }
            return false;
        }

        int size() { return size; }

        @SuppressWarnings("unchecked")
        private void resize() {
            Entry<K, V>[] old = buckets;
            buckets = new Entry[old.length * 2];
            size = 0;
            for (Entry<K, V> head : old)
                for (Entry<K, V> e = head; e != null; e = e.next) put(e.key, e.value);
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1); map.put("two", 2); map.put("three", 3);
        map.put("two", 22);                            // update existing
        System.out.println("get(two)=" + map.get("two") + " get(missing)=" + map.get("x"));
        System.out.println("size=" + map.size());
        System.out.println("remove(one)=" + map.remove("one") + " size=" + map.size());

        // Force several inserts to trigger a resize
        for (int i = 0; i < 20; i++) map.put("k" + i, i);
        System.out.println("after many puts size=" + map.size() + " get(k15)=" + map.get("k15"));
    }
}
