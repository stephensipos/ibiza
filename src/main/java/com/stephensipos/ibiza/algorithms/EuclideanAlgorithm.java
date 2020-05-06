package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;
import static java.math.BigInteger.ZERO;

public class EuclideanAlgorithm {
    private static class Step {
        private final Step precedingStep;
        private final BigInteger remainder;

        public Step(Step precedingStep, BigInteger remainder) {
            this.precedingStep = precedingStep;
            this.remainder = remainder;
        }

        public BigInteger getGreatestCommonDivisor() {
            BigInteger result;
            if (precedingStep == null) {
                result = ZERO;
            } else {
                if (remainder.equals(ZERO)) {
                    result = precedingStep.getRemainder();
                } else {
                    BigInteger[] divisionResult = precedingStep.getRemainder().divideAndRemainder(getRemainder());
                    Step next = new Step(this, divisionResult[1]);
                    result = next.getGreatestCommonDivisor();
                }
            }

            return result;
        }

        private BigInteger getRemainder() {
            return remainder;
        }

        private BigInteger getQuotient() {
            return precedingStep.getRemainder().divideAndRemainder(getRemainder())[0];
        }
    }

    public static int getGreatestCommonDivisor(int firstNumber, int secondNumber) {
        return getGreatestCommonDivisor(BigInteger.valueOf(firstNumber), BigInteger.valueOf(secondNumber)).intValue();
    }

    public static BigInteger getGreatestCommonDivisor(BigInteger a, BigInteger b) {
        Step step0, step1;
        if (a.compareTo(b) == -1) {
            step0 = new Step(null, b);
            step1 = new Step(step0, a);
        } else {
            step0 = new Step(null, a);
            step1 = new Step(step0, b);
        }

        return step1.getGreatestCommonDivisor();
    }
}
