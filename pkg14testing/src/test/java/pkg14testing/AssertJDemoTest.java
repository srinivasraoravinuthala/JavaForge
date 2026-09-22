package pkg14testing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AssertJDemoTest {

    @Test
    void fluentAssertions() {
        Calculator calc = new Calculator();
        assertThat(calc.add(2, 3)).isEqualTo(5).isGreaterThan(4);
        assertThat("hello").containsIgnoringCase("HE");
        assertThat(List.of(1, 2, 3)).hasSize(3).contains(2);
    }

    @Test
    void exceptionAssertion() {
        assertThatThrownBy(() -> new Calculator().divide(1, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("zero");
    }
}
