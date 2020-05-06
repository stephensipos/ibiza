package com.stephensipos.ibiza;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTests {
    @Test
    public void callingMainWithoutArgumentsDisplaysUsage() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        Main.main(new String[]{});

        assertTrue(myOut.toString().startsWith("Usage:"));
    }
}
