package com.stephensipos.ibiza.rsa.controllers;

import com.stephensipos.ibiza.algorithms.RSA;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.math.BigInteger;

public class Main {
    @FXML private ComboBox keySize;

    @FXML private Button generateKey;

    @FXML private TextArea modulus;
    @FXML private TextArea publicKey;
    @FXML private TextArea secretKey;

    @FXML private TextArea encryptMessage;
    @FXML private Button encrypt;
    @FXML private TextArea cipher;

    @FXML private Button decrypt;
    @FXML private TextArea decryptMessage;

    @FXML private void initialize() {
        initializeKeySize();
        initializeGenerateKey();
        initializeEncrypt();
        initializeDecrypt();
    }

    private void initializeDecrypt() {
        decrypt.setOnAction(event -> decryptHandler());
    }

    private void decryptHandler() {
        try {
            var m = new BigInteger(modulus.getText());
            var sk = new BigInteger(secretKey.getText());
            var pk = new BigInteger(publicKey.getText());
            var cipherText = cipher.getText();
            var message = RSA.decryptString(new BigInteger(cipherText), sk, m);

            if (!cipherText.equals(RSA.encryptString(message, pk, m).toString())) {
                throw new IllegalArgumentException("Encrypted message does not match the original cipher.");
            }
            decryptMessage.setText(message);

        } catch (Exception e) {
            alert("Hiba történt!", e.getMessage());
            // throw e;
        }
    }

    private void initializeEncrypt() {
        encrypt.setOnAction(event -> encryptHandler());
    }

    private void encryptHandler() {
        try {
            var m = new BigInteger(modulus.getText());
            var sk = new BigInteger(secretKey.getText());
            var pk = new BigInteger(publicKey.getText());
            var message = encryptMessage.getText();
            var c = RSA.encryptString(message, pk, m).toString();

            if (!RSA.decryptString(new BigInteger(c), sk, m).equals(message)) {
                throw new IllegalArgumentException("Decrypted cipher does not match the original message.");
            }
            cipher.setText(c);

        } catch (Exception e) {
            alert("Hiba történt!", e.getMessage());
            // throw e;
        }
    }

    private void alert(String title, String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle(title);
        dialog.setHeaderText(message);

        dialog.showAndWait();
    }

    private void initializeKeySize() {
        keySize.getItems().setAll(new String[]{"128", "256", "512", "1024", "2048"});
        keySize.setValue("512");
    }

    private void initializeGenerateKey() {
        generateKey.setOnAction(event -> generateKeyHandler());
    }

    private void generateKeyHandler() {
        var keys = RSA.generateKeys(Integer.parseInt((String) keySize.getValue()));

        publicKey.setText(keys[0].toString());
        secretKey.setText(keys[1].toString());
        modulus.setText(keys[2].toString());
    }
}
