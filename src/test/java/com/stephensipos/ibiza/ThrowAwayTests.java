package com.stephensipos.ibiza;

import com.stephensipos.ibiza.algorithms.RSA;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static java.util.stream.IntStream.range;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThrowAwayTests {

    @Test
    public void genKeys() {
        Integer[] sizes = new Integer[] {16,32,64,128,256,521,1024,2048};

        for (var size: sizes) {
            var keys = RSA.generateKeys(size);
            System.out.println(String.format("new KeySet(\"%d\", \"%d\", \"%d\"), /* %d */", keys[0], keys[1], keys[2], size));
        }

    }
}
