package com.stephensipos.ibiza;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static java.util.stream.IntStream.range;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThrowAwayTests {
    @Test
    public void testSeries() {
        assertArrayEquals(new int[] {1}, seriesUp(1));
        assertArrayEquals(new int[] {1, 1, 2}, seriesUp(2));
        assertArrayEquals(new int[] {1, 1, 2, 1, 2, 3}, seriesUp(3));
    }

    public int[] seriesUp(int n) {
        return range(0, n).flatMap(i -> {
            return range(1, i+2);
        }).toArray();
    }

    @Test
    public void fme() {
        System.out.println(BigInteger.valueOf(6).modPow(BigInteger.valueOf(73), BigInteger.valueOf(100)));
    }
}
