package pkg15modules.spi;

public class UpperEncoder implements Encoder {
    public String encode(String s) { return s.toUpperCase(); }
    public String name() { return "upper"; }
}
