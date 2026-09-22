package pkg7concurrency;

/*
 * concurrency5ForkJoinDemo.java
 * -----------------
 * The Fork/Join framework (Java 7) for divide-and-conquer parallelism using a
 * work-stealing pool. RecursiveTask returns a result; RecursiveAction doesn't.
 *
 * IDEA: split the problem (fork), solve subparts in parallel, combine (join).
 * Threshold avoids over-splitting tiny chunks (overhead > benefit).
 */
import java.util.concurrent.*;

public class concurrency5ForkJoinDemo {

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10_000;
        private final long[] arr;
        private final int lo, hi;

        SumTask(long[] arr, int lo, int hi) { this.arr = arr; this.lo = lo; this.hi = hi; }

        @Override protected Long compute() {
            if (hi - lo <= THRESHOLD) {            // small enough: compute directly
                long sum = 0;
                for (int i = lo; i < hi; i++) sum += arr[i];
                return sum;
            }
            int mid = (lo + hi) >>> 1;
            SumTask left = new SumTask(arr, lo, mid);
            SumTask right = new SumTask(arr, mid, hi);
            left.fork();                            // run left asynchronously
            long rightResult = right.compute();     // compute right on this thread
            long leftResult = left.join();          // wait for left
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        long[] data = new long[1_000_000];
        for (int i = 0; i < data.length; i++) data[i] = i + 1;     // 1..1,000,000

        ForkJoinPool pool = ForkJoinPool.commonPool();
        long parallelSum = pool.invoke(new SumTask(data, 0, data.length));

        long expected = (long) data.length * (data.length + 1) / 2;  // n(n+1)/2
        System.out.println("parallelism: " + pool.getParallelism());
        System.out.println("fork/join sum = " + parallelSum);
        System.out.println("expected      = " + expected);
        System.out.println("match: " + (parallelSum == expected));
    }
}
