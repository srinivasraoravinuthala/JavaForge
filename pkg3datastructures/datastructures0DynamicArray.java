package pkg3datastructures;

/*
 * datastructures0DynamicArray.java
 * --------------------------------
 * Resizable array (like ArrayList internals): append, get, set, insert, remove.
 *
 * COMPLEXITY:
 *  - get/set at index: O(1)
 *  - append: O(1) amortized (doubling resize)
 *  - insert/remove at index: O(n)
 *
 * WHEN TO USE: need indexed random access with unknown/growing size.
 */
import java.util.Arrays;

public class datastructures0DynamicArray {

    private int[] data;
    private int size;

    datastructures0DynamicArray() {
        data = new int[4];
        size = 0;
    }

    int size() { return size; }

    int get(int index) {
        checkIndex(index);
        return data[index];
    }

    void set(int index, int value) {
        checkIndex(index);
        data[index] = value;
    }

    void append(int value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    void insert(int index, int value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    int removeAt(int index) {
        checkIndex(index);
        int removed = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removed;
    }

    private void ensureCapacity(int min) {
        if (min <= data.length) return;
        int newCap = Math.max(data.length * 2, min);
        data = Arrays.copyOf(data, newCap);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }

    public static void main(String[] args) {
        datastructures0DynamicArray arr = new datastructures0DynamicArray();
        for (int i = 1; i <= 6; i++) arr.append(i * 10);
        System.out.println("after append: " + arr);

        arr.insert(2, 25);
        System.out.println("after insert(2,25): " + arr);

        System.out.println("removeAt(3)=" + arr.removeAt(3) + " -> " + arr);
        arr.set(0, 99);
        System.out.println("after set(0,99): " + arr + " get(0)=" + arr.get(0));
    }
}
