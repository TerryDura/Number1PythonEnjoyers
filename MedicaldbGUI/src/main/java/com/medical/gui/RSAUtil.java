package com.medical.gui;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtil {

    private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";

    // Generate a 2048-bit RSA key pair
    public static void generateKeyPair(String pubFile, String privFile) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        try (FileOutputStream fos = new FileOutputStream(pubFile)) {
            fos.write(keyPair.getPublic().getEncoded());
        }
        try (FileOutputStream fos = new FileOutputStream(privFile)) {
            fos.write(keyPair.getPrivate().getEncoded());
        }
    }

    public static PublicKey loadPublicKey(String filename) throws Exception {
        byte[] bytes = readAllBytes(filename);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    public static PrivateKey loadPrivateKey(String filename) throws Exception {
        byte[] bytes = readAllBytes(filename);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public static String encryptRSA(String plaintext, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptRSA(String ciphertext, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(ciphertext);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }

    private static byte[] readAllBytes(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return fis.readAllBytes();
        }
    }
}
