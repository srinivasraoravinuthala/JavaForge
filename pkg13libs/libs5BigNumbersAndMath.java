package pkg13libs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/*
 * libs5BigNumbersAndMath.java
 * ---------------------------
 * Arbitrary-precision numbers (BigInteger/BigDecimal) and the Math utilities.
 *
 * DEFINITION:
 *   BigInteger holds integers of unlimited size; BigDecimal holds exact decimal
 *   numbers (no binary floating-point error). Use them for cryptography, money,
 *   and anywhere precision matters.
 *
 * KEY POINTS:
 *   - double/float are binary and inexact (0.1 + 0.2 != 0.3) — never use for money.
 *   - BigDecimal is exact; control scale + RoundingMode explicitly.
 *   - BigInteger supports modPow/gcd/isProbablePrime — crypto building blocks.
 *   - These types are immutable; operations return new instances.
 */
public class libs5BigNumbersAndMath {

    public static void main(String[] args) {
        // The classic floating-point trap
        System.out.println("double 0.1 + 0.2 = " + (0.1 + 0.2) + "  <- not 0.3!");
        System.out.println("BigDecimal       = " + new BigDecimal("0.1").add(new BigDecimal("0.2")));

        // BigInteger: factorial of 50 (overflows long instantly)
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= 50; i++) fact = fact.multiply(BigInteger.valueOf(i));
        System.out.println("\n50! = " + fact);

        // BigInteger crypto helpers
        BigInteger a = BigInteger.valueOf(1071), b = BigInteger.valueOf(462);
        System.out.println("\ngcd(1071,462)    = " + a.gcd(b));
        System.out.println("2^10 mod 1000    = " + BigInteger.TWO.modPow(BigInteger.TEN, BigInteger.valueOf(1000)));
        System.out.println("is 7919 prime?   = " + BigInteger.valueOf(7919).isProbablePrime(20));

        // BigDecimal: money math with explicit rounding
        BigDecimal price = new BigDecimal("19.99");
        BigDecimal qty   = new BigDecimal("3");
        BigDecimal total = price.multiply(qty).setScale(2, RoundingMode.HALF_UP);
        System.out.println("\n3 x $19.99       = $" + total);
        BigDecimal third = BigDecimal.ONE.divide(new BigDecimal(3), new MathContext(10));
        System.out.println("1/3 (10 digits)  = " + third);

        // Math utilities
        System.out.println("\nMath.sqrt(2)     = " + Math.sqrt(2));
        System.out.println("Math.pow(2,10)   = " + Math.pow(2, 10));
        System.out.println("Math.floorMod(-7,3) = " + Math.floorMod(-7, 3));
        System.out.println("Math.addExact OK    = " + Math.addExact(2, 3));
        try { Math.addExact(Integer.MAX_VALUE, 1); }
        catch (ArithmeticException e) { System.out.println("addExact overflow caught: " + e.getMessage()); }
    }
}
