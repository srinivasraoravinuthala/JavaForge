package pkg14testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedTestDemo {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 100})
    void addZeroIsIdentity(int n) {
        assertEquals(n, new Calculator().add(n, 0));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "0, 0, 0",
            "-1, 1, 0"
    })
    void addCsv(int a, int b, int expected) {
        assertEquals(expected, new Calculator().add(a, b));
    }
}
