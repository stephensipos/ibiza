package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.stephensipos.ibiza.algorithms.GreatestCommonDivisor.gcd;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatestCommonDivisorTests {
    @Test
    public void gcdOfEqualNumbersEqualsTheOriginalNumbers() {
        assertEquals(5, gcd(5,5));
    }

    @Test
    public void gcdOfTwoZeroesAreZeroByDefinition() {
        assertEquals(0, gcd(0, 0));
    }

    @Test
    public void gcdIsCommutative() {
        int[][] pairs = {{10, 20}, {17,17}, {70,65}, {81, 9}};
        Arrays.stream(pairs).forEach(pair -> {
            assertEquals(gcd(pair[0], pair[1]), gcd(pair[1], pair[0]));
        });
    }

    @Test
    public void ifSecondNUmberIsMultipleOfFirstNumberReturnsFirstNumber() {
        int[][] pairs = {{10, 20}, {17,34}, {70,140}, {81, 324}};
        Arrays.stream(pairs).forEach(pair -> {
            assertEquals(pair[0], gcd(pair[1], pair[0]));
        });
    }
}
