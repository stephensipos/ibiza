package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.*;

public class FastModularExponentiation {

    public static BigInteger power(BigInteger base, BigInteger exponent, BigInteger modulo) {
        return base.modPow(exponent, modulo);
        // return powerGeeks(base, exponent, modulo);
        // return powerWorkbook(base, exponent, modulo);
    }

    // https://www.geeksforgeeks.org/modular-exponentiation-power-in-modular-arithmetic/
    public static BigInteger powerGeeks(BigInteger base, BigInteger exponent, BigInteger modulo) {
        var result = ONE;
        base = base.mod(modulo);

        if (base.equals(ZERO)) return ZERO;

        while (exponent.compareTo(ZERO) == 1) {
            if (exponent.mod(TWO).equals(ONE))
                result = result.multiply(base).mod(modulo);

            exponent = exponent.divide(TWO);
            base = base.multiply(base).mod(modulo);
        }
        return result;
    }

    public static BigInteger powerWorkbook(BigInteger base, BigInteger exponent, BigInteger modulo) {
        if (modulo.compareTo(ONE) == -1)  throw new ArithmeticException("Modulo is less than one!");

        var bs = computeBs(exponent);
        BigInteger product = bs.stream()
                .map(b -> (int) Math.pow(2, b))
                .map(bk -> base.pow(bk))
                .map(a -> a.mod(modulo))
                .reduce(ONE, (a, b) -> a.multiply(b));

        var result = product.mod(modulo);
        return result;
    }

    private static List<Integer> computeBs(BigInteger n) {
        List<Integer> result = new ArrayList<Integer>();
        Integer b = 0;
        while (n.compareTo(ZERO) != 0) {
            BigInteger[] r = n.divideAndRemainder(TWO);
            n = r[0];
            if (r[1].equals(ONE)) {
                result.add(b);
            }
            b += 1;
        }

        return result;
    }
}
