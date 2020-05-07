package com.stephensipos.ibiza.algorithms;

import java.math.BigInteger;

import java.security.SecureRandom;

import static com.stephensipos.ibiza.algorithms.FastModularExponentiation.power;
import static java.math.BigInteger.*;

public class RSA {
    private static final int DEFAULT_KEY_SIZE = 512; // Recommended: 2048
    private static final int precision = 3;
    private static final BigInteger MIN_E = BigInteger.valueOf(3);
    private static final SecureRandom secureRandom = new SecureRandom();

    public static BigInteger[] generateKeys() {
        return generateKeys(DEFAULT_KEY_SIZE);
    }

    public static BigInteger[] generateKeys(int size) {
        var p = getRandom(size);
        var q = getRandom(size);
        var modulus = p.multiply(q);
        var phi = p.subtract(ONE).multiply(q.subtract(ONE));
        var publicKey = generatePublicKey(phi);

        BigInteger[] eea = ExtendedEuclideanAlgorithm.gcdWithCoefficients(phi, publicKey);

        // var secretKey = publicKey.modInverse(phi);
        var secretKey = eea[2];
        if (secretKey.compareTo(TWO) == -1) {
            secretKey = secretKey.add(phi);
        }

        // Public Key, Secure Key, Modulus
        return new BigInteger[] {publicKey, secretKey, modulus};
    }

    private static BigInteger generatePublicKey(BigInteger phi) {
        for (var e = MIN_E; e.compareTo(phi) == -1; e = e.add(ONE)) {
            if (isCoprime(e, phi)) return e;
        }
        throw new RuntimeException();
    }

    private static boolean isCoprime(BigInteger a, BigInteger b) {
        return EuclideanAlgorithm.getGreatestCommonDivisor(a, b).equals(ONE);
    }

    private static boolean probablyPrime(BigInteger n) {
        BigInteger base = n.subtract(ONE).divide(BigInteger.valueOf(precision));
        if (base.equals(ZERO)) return false;

        for (var i = 1; i <= precision; i += 1) {
            if (!MillerRabin.maybePrime(n, base.multiply(BigInteger.valueOf(i)))) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger getRandom(int size) {
        BigInteger random;

        do {
            random = new BigInteger(size, secureRandom);
        } while(!probablyPrime(random));

        return random;
    }

    public static BigInteger encrypt(BigInteger message, BigInteger publicKey, BigInteger modulus) {
        if (message.compareTo(modulus) != -1) {
            throw new IllegalArgumentException("Message is larger or equal than modulus.");
        }
        return power(message, publicKey, modulus);
    }

    public static BigInteger decrypt(BigInteger cipher, BigInteger secureKey, BigInteger modulus) {
        return power(cipher, secureKey, modulus);
    }

    public static BigInteger encryptString(String message, BigInteger publicKey, BigInteger modulus) {
        BigInteger m = new BigInteger(message.getBytes());
        return encrypt(m, publicKey, modulus);
    }

    public static String decryptString(BigInteger cipher, BigInteger secureKey, BigInteger modulus) {
        BigInteger c = decrypt(cipher, secureKey, modulus);
        return new String(c.toByteArray());
    }

}
