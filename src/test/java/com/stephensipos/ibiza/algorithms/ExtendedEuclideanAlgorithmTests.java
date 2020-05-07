package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ExtendedEuclideanAlgorithmTests {
    @Test
    public void gcdOfEqualNumbersEqualsTheOriginalNumbers() {
        assertArrayEquals(new int[] {5, 0, 1}, ExtendedEuclideanAlgorithm.gcdWithCoefficients(5,5));
    }


    @Test
    public void exampleFromTheWorkbook() {
        assertArrayEquals(new int[] {1, -1, 10}, ExtendedEuclideanAlgorithm.gcdWithCoefficients(139, 14));
    }

    @Test
    public void exampleFromTheInternet() {
        assertArrayEquals(new int[] {1, 2, -5}, ExtendedEuclideanAlgorithm.gcdWithCoefficients(43, 17));
    }

    @Test
    public void randomTests() {
        Arrays.stream(ExtendedEuclideanAlgorithm.gcdWithCoefficients(43, 170)).forEach(System.out::println);
    }
}
