package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;

public class GreatestCommonDivisor {
    public static int gcd(int firstNumber, int secondNumber) {
        return gcd(BigInteger.valueOf(firstNumber), BigInteger.valueOf(secondNumber)).intValue();
    }

    public static BigInteger gcd(BigInteger firstNumber, BigInteger secondNumber) {
        while (!firstNumber.equals(secondNumber)) {
            if(firstNumber.compareTo(secondNumber) == 1) {
                firstNumber = firstNumber.subtract(secondNumber);
            } else {
                secondNumber = secondNumber.subtract(firstNumber);
            }
        }
        return secondNumber;
    }
}
