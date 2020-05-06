package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;

public class GreatestCommonDivisor {
    public static int getGreatestCommonDivisor(int firstNumber, int secondNumber) {
        return getGreatestCommonDivisor(BigInteger.valueOf(firstNumber), BigInteger.valueOf(secondNumber)).intValue();
    }

    public static BigInteger getGreatestCommonDivisor(BigInteger firstNumber, BigInteger secondNumber) {
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
