package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class MillerRabin {
    private static class SDFormat {
        public final int s;
        public final BigInteger d;

        public static SDFormat compute(BigInteger n) {
            var d = n.subtract(ONE);
            int s = 0;

            while (d.mod(TWO) == ZERO) {
                BigInteger[] divisionResult = d.divideAndRemainder(TWO);
                d = divisionResult[0];
                s += 1;
            }

            return new SDFormat(s, d);
        }

        private SDFormat(int s, BigInteger d) {
            this.s = s;
            this.d = d;
        }
    }

    public static boolean maybePrime(int n, int base) {
        return maybePrime(
                BigInteger.valueOf(n),
                BigInteger.valueOf(base));
    }

    public static boolean maybePrime(BigInteger n, BigInteger base) {
        if (n.compareTo(TWO) == -1) return false;
        if (n.equals(TWO))  return true;
        if (n.mod(BigInteger.valueOf(2)) == ZERO)  return false;


        return millerTest(n, base);
    }

    private static boolean millerTest(BigInteger n, BigInteger base) {
        var sd = SDFormat.compute(n);
        var s = sd.s;
        var d = sd.d;

        BigInteger x = base.modPow(d, n);

        if (x.equals(ONE) || x.equals(n.subtract(ONE))) {
            return true;
        } else {
            for (var r = 1; r < s; r += 1) {
                if (base.modPow(d.multiply(TWO.pow(r)), n).equals(n.subtract(ONE))) {
                    return true;
                }
            }
            return false;
        }
    }

}
