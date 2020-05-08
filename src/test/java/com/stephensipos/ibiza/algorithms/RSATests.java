package com.stephensipos.ibiza.algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static org.junit.jupiter.api.Assertions.*;

public class RSATests {

    @Test
    public void generateKeysReturnsPKModulusAndSK() {
        assertEquals(3, RSA.generateKeys(1024).length);
    }

    @Test
    public void encryptDecrypt() {
        var keys = RSA.generateKeys(512);
        var m = new BigInteger("1234923849348579234857");
        var c = RSA.encrypt(m, keys[0], keys[2]);
        var m2 = RSA.decrypt(c, keys[1], keys[2]);

        assertFalse(m.equals(c));
        assertTrue(m.equals(m2));
    }

    @Test
    public void encryptDecryptString() {
        var keys = RSA.generateKeys(512);
        var m = "Mily szó szökkent ki fogad kerítésin?";
        var c = RSA.encryptString(m, keys[0], keys[2]);
        var m2 = RSA.decryptString(c, keys[1], keys[2]);

        assertTrue(m.equals(m2));
    }

    @Test
    public void givenThatMessageEqualsWithModulusEncryptionResultsInException() {
        var keys = RSA.generateKeys(512);
        var modulus = keys[2];
        var message = modulus;
        try {
            var c = RSA.encrypt(message, keys[0], keys[2]);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true, "This is the expected behaviour.");
        }
    }

    @Test
    public void givenThatMessageIsLargerThanModulusEncryptionResultsInException() {
        var keys = RSA.generateKeys(512);
        var modulus = keys[2];
        var message = modulus.add(ONE);
        try {
            RSA.encrypt(message, keys[0], keys[2]);
            fail();
        } catch (IllegalArgumentException e) {
            // This is the expected behaviour
            return;
        }
        fail("Unexpected exception!");
    }

    @Test
    public void crtDecryptWorkbookTests () {
        assertTrue(RSA.crtDecrypt(valueOf(15), valueOf(11), valueOf(5), valueOf(23)).equals(valueOf(20)));
        assertTrue(RSA.crtDecrypt(valueOf(9), valueOf(13), valueOf(7), valueOf(70)).equals(valueOf(9)));

        // a jegyzetben m = 51, ami (szerintem) hibás
        assertTrue(RSA.crtDecrypt(valueOf(5), valueOf(7), valueOf(13), valueOf(70)).equals(valueOf(59)));
    }
}
