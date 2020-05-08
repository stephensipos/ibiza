package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FastModularExponentiationTests {
    @Test
    public void compareResultsWithLibraryImplementation() {
        var base = ONE;

        for (var i = 1; i <= 100; i += 1) {
            // Skip some values (any product with 2*5 will end with zeroes)
            if (i%2 == 0) continue;
            if (i%5 == 0) continue;
            base = base.multiply(BigInteger.valueOf(i));
            for (var j = 2; j < 100; j += 1) {
                var exponent = BigInteger.valueOf(j);
                for (var k = 1; k <= 100; k += 1) {
                    var modulo = BigInteger.valueOf(k);
                    assertTrue(base.modPow(exponent, modulo).equals(FastModularExponentiation.power(base, exponent, modulo)));
                }
            }
        }
    }
}
