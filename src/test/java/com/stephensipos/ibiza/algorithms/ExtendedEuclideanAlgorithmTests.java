package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import static com.stephensipos.ibiza.algorithms.ExtendedEuclideanAlgorithm.getGreatestCommonDivisor;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ExtendedEuclideanAlgorithmTests {
    @Test
    public void gcdOfEqualNumbersEqualsTheOriginalNumbers() {
        assertArrayEquals(new int[] {5, 0, 1}, getGreatestCommonDivisor(5,5));
    }

    @Test
    public void exampleFromTheWorkbook() {
        assertArrayEquals(new int[] {1, -1, 10}, getGreatestCommonDivisor(139, 14));
    }
}
