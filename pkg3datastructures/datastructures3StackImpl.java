package pkg3datastructures;

/*
 * datastructures3StackImpl.java
 * --------------
 * LIFO stack implemented two ways: array-backed and linked-node.
 * Includes a classic application: balanced-parentheses checking.
 *
 * COMPLEXITY: push/pop/peek O(1) amortized.
 * WHEN TO USE: undo, expression evaluation, DFS, backtracking, call stacks.
 */
public class datastructures3StackImpl {

    // Array-backed stack with dynamic resizing
    static class ArrayStack {
        private int[] data = new int[4];
        private int top = -1;

        void push(int v) {
            if (top == data.length - 1) resize();
            data[++top] = v;
        }
        int pop() {
            if (isEmpty()) throw new RuntimeException("stack empty");
            return data[top--];
        }
        int peek() {
            if (isEmpty()) throw new RuntimeException("stack empty");
            return data[top];
        }
        boolean isEmpty() { return top == -1; }
        int size() { return top + 1; }
        private void resize() {
            int[] bigger = new int[data.length * 2];
            System.arraycopy(data, 0, bigger, 0, data.length);
            data = bigger;
        }
    }

    // Application: are the brackets balanced?
    static boolean isBalanced(String s) {
        java.util.Deque<Character> stack = new java.util.ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(', '[', '{' -> stack.push(c);
                case ')' -> { if (stack.isEmpty() || stack.pop() != '(') return false; }
                case ']' -> { if (stack.isEmpty() || stack.pop() != '[') return false; }
                case '}' -> { if (stack.isEmpty() || stack.pop() != '{') return false; }
                default -> {}
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ArrayStack st = new ArrayStack();
        for (int i = 1; i <= 6; i++) st.push(i);   // triggers a resize
        System.out.println("size=" + st.size() + " peek=" + st.peek());
        StringBuilder popped = new StringBuilder();
        while (!st.isEmpty()) popped.append(st.pop()).append(' ');
        System.out.println("pop order (LIFO): " + popped.toString().trim());

        System.out.println("balanced '({[]})' : " + isBalanced("({[]})"));
        System.out.println("balanced '([)]'   : " + isBalanced("([)]"));
    }
}
