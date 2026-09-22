package pkg14testing;

import net.jqwik.api.*;

class PropertyBasedDemoTest {

    @Property
    void addIsCommutative(@ForAll int a, @ForAll int b) {
        Calculator calc = new Calculator();
        assert calc.add(a, b) == calc.add(b, a);
    }

    @Property
    void addWithZeroIsIdentity(@ForAll int a) {
        assert new Calculator().add(a, 0) == a;
    }
}
