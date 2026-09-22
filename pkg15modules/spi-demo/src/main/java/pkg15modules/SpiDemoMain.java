package pkg15modules;

import pkg15modules.spi.Encoder;

import java.util.ServiceLoader;

public class SpiDemoMain {
    public static void main(String[] args) {
        String text = "hello spi";
        System.out.println("ServiceLoader discovery for Encoder:");
        for (Encoder enc : ServiceLoader.load(Encoder.class))
            System.out.printf("  [%s] %s -> %s%n", enc.name(), text, enc.encode(text));
    }
}
