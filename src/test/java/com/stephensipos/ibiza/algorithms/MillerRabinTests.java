package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MillerRabinTests {
    @Test
    public void examplesFromTheWorkbook() {
        assertFalse(MillerRabin.maybePrime(561, 2));
        assertFalse(MillerRabin.maybePrime(561, 13));
        assertTrue(MillerRabin.maybePrime(197, 7));
        assertTrue(MillerRabin.maybePrime(197, 12));
        assertFalse(MillerRabin.maybePrime(243, 11));
        assertFalse(MillerRabin.maybePrime(243, 15));
    }

}
