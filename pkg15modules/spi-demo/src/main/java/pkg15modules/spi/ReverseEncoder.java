package pkg15modules.spi;

public class ReverseEncoder implements Encoder {
    public String encode(String s) { return new StringBuilder(s).reverse().toString(); }
    public String name() { return "reverse"; }
}
