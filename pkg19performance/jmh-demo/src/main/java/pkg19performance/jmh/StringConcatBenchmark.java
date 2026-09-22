package pkg19performance.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/*
 * Run: mvn -q package -f pkg19performance/jmh-demo/pom.xml
 *      java -jar pkg19performance/jmh-demo/target/benchmarks.jar -wi 1 -i 3 -f 1
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
@Fork(1)
public class StringConcatBenchmark {

    @Benchmark
    public void stringConcat(Blackhole bh) {
        String s = "";
        for (int i = 0; i < 10; i++) s += i;
        bh.consume(s);
    }

    @Benchmark
    public void stringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(i);
        bh.consume(sb.toString());
    }
}
