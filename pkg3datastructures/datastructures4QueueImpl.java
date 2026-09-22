package pkg3datastructures;

/*
 * datastructures4QueueImpl.java
 * --------------
 * FIFO queue as a circular buffer (ring buffer) and a quick demo of a deque.
 *
 * COMPLEXITY: enqueue/dequeue O(1); circular buffer avoids shifting elements.
 * WHEN TO USE: BFS, scheduling, producer/consumer buffers, streaming windows.
 */
public class datastructures4QueueImpl {

    // Fixed-capacity circular queue
    static class CircularQueue {
        private final int[] data;
        private int head = 0, tail = 0, count = 0;

        CircularQueue(int capacity) { data = new int[capacity]; }

        boolean enqueue(int v) {
            if (isFull()) return false;
            data[tail] = v;
            tail = (tail + 1) % data.length;   // wrap around
            count++;
            return true;
        }
        Integer dequeue() {
            if (isEmpty()) return null;
            int v = data[head];
            head = (head + 1) % data.length;
            count--;
            return v;
        }
        Integer peek() { return isEmpty() ? null : data[head]; }
        boolean isEmpty() { return count == 0; }
        boolean isFull() { return count == data.length; }
        int size() { return count; }
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(3);
        System.out.println("enqueue 1,2,3: " + q.enqueue(1) + "," + q.enqueue(2) + "," + q.enqueue(3));
        System.out.println("enqueue 4 (full): " + q.enqueue(4));
        System.out.println("dequeue: " + q.dequeue() + " peek: " + q.peek());
        System.out.println("enqueue 4 now fits: " + q.enqueue(4));   // wraps to freed slot
        StringBuilder out = new StringBuilder();
        while (!q.isEmpty()) out.append(q.dequeue()).append(' ');
        System.out.println("drain order (FIFO): " + out.toString().trim());

        // java.util.Deque can act as both queue and stack
        java.util.Deque<Integer> dq = new java.util.ArrayDeque<>();
        dq.offerFirst(1); dq.offerLast(2); dq.offerFirst(0);
        System.out.println("deque front-to-back: " + dq);
    }
}
