package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;
import java.util.Arrays;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.ONE;

public class ExtendedEuclideanAlgorithm {
    private static class Step {
        private static final BigInteger NEGATIVE_ONE = BigInteger.valueOf(-1);
        private final Step precedingStep;
        private final BigInteger remainder;

        public Step(Step precedingStep, BigInteger remainder) {
            this.precedingStep = precedingStep;
            this.remainder = remainder;
        }

        public BigInteger[] getGreatestCommonDivisorAndParameters() {
            BigInteger[] result = new BigInteger[3];
            if (precedingStep == null) {
                result[0] = result[1] = result[2]= ZERO;
            } else {
                BigInteger[] divisionResult = precedingStep.getRemainder().divideAndRemainder(getRemainder());

                if (divisionResult[1].equals(ZERO)) {
                    result[0] = getRemainder();
                    result[1] = getX().multiply(NEGATIVE_ONE.pow(getIteration()));
                    result[2] = getY().multiply(NEGATIVE_ONE.pow(getIteration() + 1));
                } else {
                    Step next = new Step(this, divisionResult[1]);
                    result = next.getGreatestCommonDivisorAndParameters();
                }
            }
            return result;
        }

        private BigInteger getRemainder() {
            return remainder;
        }

        private BigInteger getQuotient() {
            if (precedingStep == null) {
                return null;
            } else {
                return precedingStep.getRemainder().divideAndRemainder(getRemainder())[0];
            }
        }

        private int getIteration() {
            if (precedingStep == null) {
                return 0;
            } else {
                return precedingStep.getIteration() + 1;
            }
        }

        private BigInteger getX() {
            switch (getIteration()) {
                case 0: return ONE;
                case 1: return ZERO;
                default: return precedingStep.getX().multiply(precedingStep.getQuotient()).add(precedingStep.precedingStep.getX());
            }
        }

        private BigInteger getY() {
            switch (getIteration()) {
                case 0: return ZERO;
                case 1: return ONE;
                default: return precedingStep.getY().multiply(precedingStep.getQuotient()).add(precedingStep.precedingStep.getY());
            }
        }
    }

    public static int[] getGreatestCommonDivisor(int firstNumber, int secondNumber) {
        BigInteger[] results;
        results = getGreatestCommonDivisorAndParameters(BigInteger.valueOf(firstNumber), BigInteger.valueOf(secondNumber));
        return Arrays.stream(results).mapToInt(r -> r.intValue()).toArray();
    }

    public static BigInteger[] getGreatestCommonDivisorAndParameters(BigInteger a, BigInteger b) {
        Step step0, step1;
        if (a.compareTo(b) == -1) {
            step0 = new Step(null, b);
            step1 = new Step(step0, a);
        } else {
            step0 = new Step(null, a);
            step1 = new Step(step0, b);
        }

        return step1.getGreatestCommonDivisorAndParameters();
    }
}
