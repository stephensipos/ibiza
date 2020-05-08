package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public class FastModularExponentiation {

    public static BigInteger power(BigInteger base, BigInteger exponent, BigInteger modulo) {
        return base.modPow(exponent, modulo);
        // return workbookPower(base, exponent, modulo);
    }

    public static BigInteger workbookPower(BigInteger base, BigInteger exponent, BigInteger modulo) {
        var result = ONE;
        base = base.mod(modulo);

        if (base.equals(ZERO)) return ZERO;

        while (exponent.compareTo(ZERO) == 1) {
            if (exponent.testBit(0)) result = result.multiply(base).mod(modulo);
            exponent = exponent.shiftRight(1);
            base = base.multiply(base).mod(modulo);
        }

        return result;
    }
}
